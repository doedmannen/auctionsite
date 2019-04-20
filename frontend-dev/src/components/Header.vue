<template>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="/" id="logo">Auction Site</a>
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
            </ul>
            <a href=""><i class="fas fa-user"></i></a>
            <a href=""><i class="fas fa-envelope"></i></a>
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
    a {
        color: black;
    }

    .navbar {
        background-color: rgb(126, 199, 199);
    }

    #logo {
        font-family: 'Bungee Inline', cursive;
        font-size: 2em;
        color: black;
    }

    .fas {
        font-size: 1.75em;
        margin-right: 1.5em;
    }

</style>
