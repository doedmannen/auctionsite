<template>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand logo" href="/">Auction Site</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Find auctions!
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Active</a>
                        <a class="dropdown-item" href="#">Completed</a>
                        <div class="dropdown-divider"></div>
                        <div v-for="(category, index) in categories" :key="index">
                            <a :href="category.url" class="dropdown-item">{{ category.name }}</a>
                        </div>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/create">Create auction</a>
                </li>
            </ul>

            <!--Registration dropdown-->
            <li class="dropleft">
                <a data-toggle="dropdown" class="spacing">Register</a>
                <ul class="dropdown-menu" role="menu">
                    <div class="col-lg-12">
                        <div class="text-center">
                            <p class="logo">Create an account</p></div>
                        <form role="form" autocomplete="off">
                            <div class="form-group">
                                <input type="email" name="register_email" class="form-control"
                                       placeholder="Email Address" value="curran.kate@gmail.com">
                            </div>
                            <div class="form-group">
                                <input type="password" name="register_pass"
                                       class="form-control" placeholder="Password" value="test">
                            </div>
                            <div class="form-group">
                                <input type="password" name="confirm_pass"
                                       class="form-control" placeholder="Confirm Password" value="test">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6 col-xs-offset-3">
                                        <button v-on:click="createNewUser" type="button" class="btn btn-center">Register</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </ul>
            </li>

            <!--Login dropdown-->
            <li class="dropleft">
                <a data-toggle="dropdown"><i class="fas fa-user spacing"></i></a>
                <ul class="dropdown-menu dropdown-menu-lg-left" role="menu">
                    <div class="col-lg-12">
                        <div class="text-center">
                            <p class="logo">Log In</p></div>
                        <form role="form" autocomplete="off">
                            <div class="form-group">
                                <label for="userEmail">Username</label>
                                <input type="text" name="login_user" id="userEmail" tabindex="1"
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
                                        <button v-on:click="loginUser" type="button" class="btn btn-center">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </ul>
            </li>
            <a href=""><i class="fas fa-envelope spacing"></i></a>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                searchAuctions: "",
                categories: [
                    {
                        name: "Vehicles",
                        url: "/vehicles"
                    },
                    {
                        name: "Fashion",
                        url: "/fashion"
                    },
                    {
                        name: "Sporting Goods",
                        url: "/health"
                        //etc etc more categories from db
                    }]
            }
        },
        methods: {
            async createNewUser(){
                let data = {
                }
                    data.email = document.getElementsByName('register_email').value;
                    data.password = document.getElementsByName('register_pass').value;
                    data.confirm_pass = document.getElementsByName('confirm_pass').value;

                let responseFromBackend = await fetch('/api/user/login', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                responseFromBackend = await responseFromBackend.text();
                console.log(responseFromBackend);
            },

            async loginUser(){
                let data = {
                }
                data.email = document.getElementsByName('register_email').value;
                data.password = document.getElementsByName('register_pass').value;

                let responseFromBackend = await fetch('/api/user/login', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                responseFromBackend = await responseFromBackend.text();
                console.log(responseFromBackend);
            }
        }
    };
</script>

<style scoped>

    * {
        color: rgb(32, 64, 96);
    }

    a {
        color: rgb(32, 64, 96);
    }

    .navbar {
        background-color: rgb(126, 199, 199);
    }

    .logo {
        font-family: 'Bungee Inline', cursive;
        font-size: 2em;
    }

    .fas {
        font-size: 1.75em;
        color: rgb(32, 64, 96);
    }

    .spacing {
        margin: 0 1em;
    }

    li {
        list-style: none;
    }

    .dropdown-menu {
        width: 300px;
    }

    .btn-center {
        margin-left: 1em;
        background-color: rgb(32, 64, 96);
        color: rgb(126, 199, 199);
    }
</style>
