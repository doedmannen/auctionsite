<template lang="html">
    <div class="main" v-if="!this.loggedIn">
        <div class="contentMenu">
            <b-dropdown variant="link" id="dropdown-register" dropleft ref="dropdownreg" class="m-2" no-caret>

                <template slot="button-content"><span class="linkText">Register</span>
                </template>
                <div>
                    <b-dropdown-form>
                        <p class="logo">Create new account</p>
                        <b-form-group  @submit.stop.prevent>
                            <b-form-input
                            id="registration-firstname"
                            size="md"
                            placeholder="Firstname"
                            v-on:keyup.enter="createNewUser"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group>
                            <b-form-input
                            id="registration-lastname"
                            size="md"
                            placeholder="Lastname"
                            v-on:keyup.enter="createNewUser"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group>
                            <b-form-input
                            id="registration-email"
                            size="md"
                            placeholder="Email Address"
                            v-on:keyup.enter="createNewUser"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group>
                            <b-form-input
                            id="registration-password"
                            type="password"
                            size="md"
                            placeholder="Password"
                            v-on:keyup.enter="createNewUser"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group>
                            <b-form-input
                            id="registration-password-repeat"
                            type="password"
                            size="md"
                            placeholder="Password"
                            v-on:keyup.enter="createNewUser"
                            ></b-form-input>
                        </b-form-group>

                        <button @click="createNewUser"
                        type="button"
                        class="btn btn-center">Submit</button>

                        <div><p class="registrationError">{{formError}}</p></div>
                    </b-dropdown-form>
                </div>
            </b-dropdown>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Register',
    data(){
        return{
            formError: ""
        }
    },
    computed: {
        loggedIn() {
            return this.$store.state.me != null;
        }
    },
    methods: {
        async createNewUser() {
            this.formError = "";

            let data = {}
            let confirm_pass = document.getElementById('registration-password-repeat').value;
            data.firstname = document.getElementById('registration-firstname').value;
            data.lastname = document.getElementById('registration-lastname').value;
            data.email = document.getElementById('registration-email').value;
            data.password = document.getElementById('registration-password').value;

            if(!data.firstname || !data.lastname || !data.email || !data.password){
                this.formError = "All fields need to be filled out!";
            }else if(!data.email.match(/^[a-z]{3,}@[a-z]{3,}\.[a-z]{2,10}$/)){
                this.formError = "Invalid email";
            } else if (data.password != confirm_pass){
                this.formError = "Passwords do not match!";
            } else {
                let responseFromBackend = await fetch('/api/user', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                responseFromBackend = await responseFromBackend.text();
                if(responseFromBackend > 0){
                    this.autoLogin(data);
                } else {
                    this.formError = "An account is already registered with that email"
                }
            }
        },
        async autoLogin(data){
            this.$refs.dropdownreg.hide(true);
            data = `username=${data.email}&password=${data.password}`;
            let responseFromBackend = await fetch('/login', {
                method: "POST",
                body: data,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
            this.$store.dispatch("whoami");
        }
    }

}
</script>

<style lang="css" scoped>
span{
    cursor: pointer;
}
.linkText{
    color: #2c3e50;
}
.registrationError{
    color: red;
    text-align: center;
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
