<template>
<div class="home">
    <router-view />
    <div class="body">
        <h1 class="logo">Sell your stuff!</h1>
        <p>Wow, an auction site! Check out some auctions below, select a category or search for exactly what you
            want. </p>
        <input class="searchBox" type="text" v-model="searchAuctions" placeholder="Search">
      
        <div class="flexActivComp">
           
                  <p class="filterby">Show:</p>
      
             <span @click="toggleActive" class="filterby" :style="this.showActive ? 'color:black':'color:red'" style="cursor: pointer;">Active</span>
                        <span @click="toggleComplete" class="filterby" :style="this.showCompleted? 'color:black':'color:red'" style="cursor: pointer;">Completed</span>
        </div>
        
        
        <div class="mainFlex" v-for="(auction, index) in filteredAuctions.slice(0,auctionsShown)" :key="index">
            <a :href="'/auction/' + auction.auctionid" class="flexSection">
             <img :src="'/assets/img/thumbnail/'+auction.images[0]" id="image">
                <div class="flex">
                    <p class="title">{{ auction.title }}</p>
                    <p>{{ auction.description }}</p>
                        <a :href="auction.sellerProfile">
                            <p>Seller: {{ auction.users.firstname }} {{ auction.users.lastname }}</p>
                        </a>
                        <div>
                        <p class="currBid" v-if="auction.bids.length > 0">Price: ${{ auction.bids[auction.bids.length-1].bidamount }} ~ Bids: {{ auction.bids.length  }}</p>
                        <p class="currBid" v-else>Price: ${{auction.startprice }} ~ Bids: {{ auction.bids.length }}</p>
                        <p> EndTime: {{auction.endtime.replace(/T/g, " ")}} </p>

                    </div>
                </div>

            </a>
        </div>

    <div class="showMoreButton">
        <b-button class="showMore mt-2" v-on:click="loadMore">Load more</b-button>
    </div>

    </div>
</div>
</template>

<script>
export default {
    name: "home",
    mounted(){
        console.log();
        console.log();
    },
    data() {
        return {
            searchAuctions: "",
            showActive: true,
            showCompleted: true,
            auctionsShown: 10
        };
    },
    computed: {
        auctionPosts() {
            let ap = this.$store.state.auctionPosts;
            let active = [];
            let complete = []
            let timeNow = new Date();
            let path = this.$route.path.split("/");
            if(path[1] != '' && path[2]){
                let cat = this.$store.state.categories.filter(c => c.categoryname == this.$route.params.cat);
                ap = this.$store.state.auctionPosts.filter(auction => auction.category == cat[0].categoryid);
            }
            if(this.showActive){
                active = ap.filter(auction => {
                    let dat = auction.endtime.replace(/T/g, " ");
                    return timeNow > new Date(dat);
                    })
            }
            if(this.showCompleted){
                complete = ap.filter(auction => {
                    let dat = auction.endtime.replace(/T/g, " ");
                    return timeNow <= new Date(dat);
                    })
            }
            
            return active.concat(complete);
        },

        filteredAuctions: function() {
            return this.auctionPosts.filter((auction) => {
                return auction.title.toLowerCase().match(this.searchAuctions) || auction.description.toLowerCase().match(this.searchAuctions);
            })

        }
    },
    methods: {
        /*auctionsByCategory(category) {
            this.category = category;
            return this.auctions.filter(el => el.category.match(category));
        },*/
        toggleActive(){
            this.showActive = !this.showActive;
        },
        toggleComplete(){
            this.showCompleted = !this.showCompleted;
        },
         loadMore() {
            this.auctionsShown += 5;
        },
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


.body {
    margin-top: 2em;
}

.mainFlex {
    display: flex;
    flex-direction: column;
}

.flexSection {
    display: flex;
    width: 70vw;
    margin: 1vh;
    border: 2px gray solid;
    align-self: center;
}

#image {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 0.5em;
    width: 200px;
    height: 160px;
    margin: 0.2em;
}

.flexActivComp{
    display: flex;
    width: 20vw;
    margin-left: 43vw;
    margin-bottom: 32px;
}

.filterby{
    padding-right: 0.5rem;
}

.title {
    font-size: 1.5em;
    font-weight: bold;
}

.currBid {
    font-size: 1.5em;
}

.searchBox {
    margin-bottom: 0;
}

.flex {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-left: 3em;
}
</style>
