<template>
    <nav class="navbar navbar-expand-lg">
        <router-link :to="'/'" class="navbar-brand logo">Auction Site</router-link>
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
                        <div v-for="(category, index) in categories" :key="index">
                            <router-link :to="'/category/' + category.categoryname" class="dropdown-item">{{ category.categoryname }}</router-link>
                        </div>
                    </div>
                </li>

                <li class="nav-item" v-if="this.loggedIn">
                    <router-link :to="'/createauction'" class="nav-link">Create new auction</router-link>
                </li>
            </ul>

            <Register />

            <Login />

            <ChatMenu />

            <i class="fas fa-bell"></i>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                searchAuctions: "",
            }
        },
        computed: {
            loggedIn(){
                return this.$store.state.me != null;
            },
            categories(){
                return this.$store.state.categories;
            }
        },
        components: {
            Login: () => import('@/components/Login.vue'),
            Register: () => import('@/components/Register.vue'),
            ChatMenu: () => import('@/components/ChatMenu.vue')
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
        box-shadow: 2px 2px 5px gray;
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
