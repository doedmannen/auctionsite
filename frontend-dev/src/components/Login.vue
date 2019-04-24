<template lang="html">
    <li class="dropleft">
        <a data-toggle="dropdown"><span v-if="this.loggedIn">{{me.firstname +" "+ me.lastname}}</span><i class="fas fa-user spacing"></i></a>
        <ul class="dropdown-menu dropdown-menu-lg-left" role="menu">
            <div class="col-lg-12">
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

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-5">
                                <button @click="loginUser" type="button" class="btn btn-center">Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </ul>
    </li>
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
            console.log(!responseFromBackend.url.includes("fail"));
            this.$store.dispatch("whoami");
        },
    }
}
</script>

<style lang="css" scoped>
i{
    cursor: pointer;
}
</style>
