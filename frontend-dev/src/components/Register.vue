<template lang="html">
    <li class="dropleft" v-if="!this.loggedIn">
        <!--Registration dropdown-->
        <a data-toggle="dropdown" class="spacing">Register</a>
        <ul class="dropdown-menu" role="menu">
            <div class="col-lg-12">
                <div class="text-center">
                    <p class="logo">Create an account</p></div>
                <form role="form" autocomplete="off">
                    <div class="form-group">
                        <input name="register_first" class="form-control"
                               placeholder="First Name">
                    </div>
                    <div class="form-group">
                        <input name="register_last" class="form-control" placeholder="Last Name">
                    </div>
                    <div class="form-group">
                        <input type="email" name="register_email" class="form-control"
                               placeholder="Email Address">
                    </div>
                    <div class="form-group">
                        <input type="password" name="register_pass"
                               class="form-control" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <input type="password" name="confirm_pass"
                               class="form-control" placeholder="Confirm Password">
                    </div>
                    <div><p id="passError">Password must match!</p></div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6 col-xs-offset-3">
                                <button v-on:click="createNewUser" type="button" class="btn btn-center">
                                    Register
                                </button>
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
    name: 'Register',
    computed: {
        loggedIn() {
            return this.$store.state.me != null;
        }
    },
    methods: {
        async createNewUser() {
            document.getElementById("passError").style.visibility = "hidden";

            let data = {}
            let password = document.getElementsByName('register_pass')[0].value;
            let confirm_pass = document.getElementsByName('confirm_pass')[0].value;

            if(password == confirm_pass){
                data.firstname = document.getElementsByName('register_first')[0].value;
                data.lastname = document.getElementsByName('register_last')[0].value;
                data.email = document.getElementsByName('register_email')[0].value;
                data.password = document.getElementsByName('register_pass')[0].value;;
            } else {
                document.getElementById("passError").style.visibility = "visible";
            }

            let responseFromBackend = await fetch('/api/user', {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "content-type": "application/json"
                }
            });
        }
    }

}
</script>

<style lang="css" scoped>
*{
    cursor: pointer;
}
</style>
