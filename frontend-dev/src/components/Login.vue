<template lang="html">
    <div class="main">
        <p v-if="loggedIn">{{me.firstname}} {{me.lastname}}</p>
        <div class="contentMenu">
            <b-dropdown variant="link" id="dropdown-offset" offset="-230" ref="dropdown" class="m-2" no-caret>

                <template slot="button-content"><i class="fas fa-user spacing"></i>
                </template>
                <div v-if="!loggedIn">
                    <b-dropdown-form>
                        <p class="logo">Log In</p>
                        <b-form-group @submit.stop.prevent>
                            <b-form-input
                            id="login-email"
                            size="md"
                            placeholder="Email Address"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group>
                            <b-form-input
                            id="login-pass"
                            type="password"
                            size="md"
                            placeholder="Password"
                            ></b-form-input>
                        </b-form-group>
                        <button @click="loginUser" type="button" class="btn btn-center">Submit</button>
                        <div><p id="loginError">Incorrect email and/or password!</p></div>
                    </b-dropdown-form>
                </div>
                <div v-else>
                    <div class="text-center">
                        <p class="logo">User menu</p>
                    </div>
                    <button @click="logoutUser" type="button" class="btn btn-center">Logout</button>
                </div>
            </b-dropdown>
        </div>
    </div>

</template>

<script>
export default {
    name: 'Login',
    computed: {
        loggedIn() {
            return this.$store.state.me != null;
        },
        me() {
            return this.$store.state.me;
        }
    },
    methods: {
        async loginUser() {
            document.getElementById("loginError").style.visibility = "hidden";

            let data, username, password;
            username = document.getElementById('login-email').value;
            password = document.getElementById('login-pass').value;
            data = `username=${username}&password=${password}`;
            if(username && password){
                let responseFromBackend = await fetch('/login', {
                    method: "POST",
                    body: data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                if (responseFromBackend.url.includes("fail")) {
                    document.getElementById("loginError").style.visibility = "visible";
                } else {
                    this.$refs.dropdown.hide(true)
                    this.$store.dispatch("whoami");
                }
            }
        },
        async logoutUser() {
            this.$refs.dropdown.hide(true)
            await fetch("/logout");
            this.$store.dispatch("whoami");
        }
    }
}
</script>

<style lang="css" scoped>
i{
    cursor: pointer;
}

#loginError {
    color:red;
    visibility: hidden;
}
.main{
    display: flex;
    flex-direction: row;
    align-items: baseline;
}
.loggedInUser{
    margin: 0;
    padding: 0;
}

.contentMenu * {
    text-align: center;
}
</style>
