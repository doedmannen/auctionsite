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
                                <input type="text" name="username" tabindex="1"
                                       class="form-control" placeholder="Username" value="">
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" tabindex="1" class="form-control"
                                       placeholder="Email Address" value="">
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" tabindex="2"
                                       class="form-control" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input type="password" name="confirm-password" id="confirm-password"
                                       tabindex="2" class="form-control" placeholder="Confirm Password">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6 col-xs-offset-3">
                                        <input type="submit" id="register-submit" class="btn btn-center"
                                               value="Register Now">
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
                                <label for="username">Username</label>
                                <input type="text" name="username" id="username" tabindex="1"
                                       class="form-control" placeholder="Username" value="" autocomplete="off">
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" tabindex="2"
                                       class="form-control" placeholder="Password" autocomplete="off">
                            </div>

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <input type="submit" id="login-submit" class="btn btn-center"
                                               value="Log In">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </ul>
            </li>


            <a href=""><i class="fas fa-envelope spacing"></i></a>
            </ul>
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
                        name: "Electronics",
                        url: "/health"
                    },
                    {
                        name: "Collectibles",
                        url: "/health"
                    },
                    {
                        name: "Home and Garden",
                        url: "/health"
                    },
                    {
                        name: "Sporting Goods",
                        url: "/health"
                        //etc etc more categories
                    }]
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
        color:rgb(126, 199, 199);
    }
</style>
