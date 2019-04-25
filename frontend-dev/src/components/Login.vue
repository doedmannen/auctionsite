<template lang="html">
    <div class="main">
        <p class="loggedInUser" v-if="this.loggedIn">{{me.firstname +" "+ me.lastname}}</p>
        <div class="dropleft">
            <a data-toggle="dropdown"><i class="fas fa-user spacing"></i></a>
            <div class="dropdown-menu dropdown-menu-lg-left contentMenu" role="menu">
                <div class="col-lg-12">
                    <div v-if="!loggedIn">
                        <div class="text-center">
                            <p class="logo">Log In</p>
                        </div>
                        <form role="form" autocomplete="off">
                            <div class="form-group">
                                <label for="userEmail">E-mail</label>
                                <input type="text" name="login_email" id="userEmail" tabindex="1"
                                class="form-control" placeholder="Email" value="" autocomplete="off">
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" name="login_pass" id="password" tabindex="2"
                                class="form-control" placeholder="Password" autocomplete="off">
                            </div>
                            <div><p id="loginError">Incorrect username or password!</p></div>
                            <div class="form-group">
                                <button @click="loginUser" type="button" class="btn btn-center">Submit</button>
                            </div>
                        </form>
                    </div>
                    <div v-else>
                        <div class="text-center">
                            <p class="logo">User menu</p>
                        </div>
                        <button @click="logoutUser" type="button" class="btn btn-center">Logout</button>
                    </div>
                </div>
            </div>
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
            username = document.getElementsByName('login_email')[0].value;
            password = document.getElementsByName('login_pass')[0].value;
            data = `username=${username}&password=${password}`;
            let responseFromBackend = await fetch('/login', {
                method: "POST",
                body: data,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
            if(responseFromBackend.url.includes("fail")) {
                document.getElementById("loginError").style.visibility = "visible";
            }
            this.$store.dispatch("whoami");
        },
        async logoutUser(){
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
