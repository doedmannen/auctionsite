<template lang="html">
    <div class="main">
        <p v-if="loggedIn">{{me.firstname}} {{me.lastname}}</p>
        <div class="contentMenu">
            <b-dropdown variant="link" id="dropdown-login" dropleft ref="dropdownlogin" class="m-2" no-caret>

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
                        <div><p style="color:red">{{this.formError}}</p></div>
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
    data(){
        return{
            formError: ""
        }
    },
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
            this.formError = "";
            let data, username, password;
            username = document.getElementById('login-email').value;
            password = document.getElementById('login-pass').value;
            data = `username=${username}&password=${password}`;

            if(!username.match(/^[a-z]{3,}@[a-z]{3,}\.[a-z]{2,10}$/)){
                this.formError = "Please enter an email";
            } else if(!password) {
                this.formError = "Please enter a password";
            } else {
                let responseFromBackend = await fetch('/login', {
                    method: "POST",
                    body: data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                if (responseFromBackend.url.includes("fail")) {
                    this.formError = "Incorrect email and/or password";
                } else {
                    this.$refs.dropdownlogin.hide(true)
                    this.$store.dispatch("whoami");
                }
            }
        },
        async logoutUser() {
            this.$refs.dropdownlogin.hide(true)
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
.loggedInUser{
    margin: 0;
    padding: 0;
}
.main{
    display: flex;
    flex-direction: row;
    align-items: baseline;
}
.contentMenu * {
    text-align: center;
}
.btn{
    margin: 0;
}
</style>
