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
                            <a :href="'/category/' + category.categoryname" class="dropdown-item">{{ category.categoryname }}</a>
                        </div>
                    </div>
                </li>

                <li class="nav-item" v-if="this.loggedIn">
                    <a class="nav-link" href="/createauction">Create auction</a>
                </li>
            </ul>

            <Register />

            <Login />

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
               /* categories: [
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
                    }]*/
            }
        },
        computed: {
            loggedIn(){
                return this.$store.state.me != null;
            }
        },
        components: {
            Login: () => import('@/components/Login.vue'),
            Register: () => import('@/components/Register.vue')
        }
    };
</script>

<style>

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

    #passError {
        color: red;
        visibility: hidden;
    }
</style>
