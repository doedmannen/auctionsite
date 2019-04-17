<template>
    <div class="home">
        <router-view/>
        <div class="body">
            <h1 class="logo">Sell your stuff!</h1>
            <p>Wow, an auction site! Check out some auctions below, select a category or search for exactly what you
                want. </p>
            <input class="searchBox" type="text" v-model="searchAuctions" placeholder="Search">
            <div class="mainFlex" v-for="(auction, index) in filteredAuctions" :key="index">
                <a :href="auction.url" class="flexSection">
                    <div>
                        <p class="title">{{ auction.title }}</p>
                        <summary>{{ auction.description }}</summary>
                        <div class="flex">
                            <a :href="auction.sellerProfile"><p>Seller: {{ auction.seller }}</p></a>
                            <p class="currBid">{{ auction.currBid }}</p>
                        </div>
                    </div>
                    <img :src="auction.image" id="image">
                </a>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "home",
        data() {
            return {
                searchAuctions: ""
            };
        },
        computed: {
            filteredAuctions: function () {
                return this.auctions.filter((auction) => {
                    return auction.title.toLowerCase().match(this.searchAuctions) || auction.description.toLowerCase().match(this.searchAuctions);
                }),
                    auctionPosts()
                {
                    return this.$store.state.auctionPosts;
                }
            }
        },
        methods: {
            auctionsByCategory(category) {
                this.category = category;
                return this.auctions.filter(el => el.category.match(category));
            }
        }
    };

</script>

<style scoped>
    .logo {
        font-family: "Bungee Inline", cursive;
    }

    a {
        color: black;
    }

    a:hover {
        text-decoration: none;
    }

    .home {
        padding-bottom: 75px;
    }

    .body {
        margin-top: 2em;
    }

    .mainFlex {
        display: flex;
        flex-direction: column;
    }

    .flexSection {
        display: flex;
        height: 30vh;
        width: 70vw;
        margin: 1vh;
        border: 2px gray solid;
        align-self: center;
    }

    #image {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0.5em;
        width: 15vw;
        margin: 0.2em;
    }

    .flex {
        display: flex;
        justify-content: space-between;
        margin: 1em;
    }

    summary {
        text-align: left;
        text-indent: 1em;
        margin: 1em;
    }

    .title {
        font-size: 1.5em;
        font-weight: bold;
    }

    .currBid {
        font-size: 1.5em;
    }

    .searchBox {
        margin-bottom: 2em;
    }
</style>