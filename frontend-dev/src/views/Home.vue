<template>
    <div class="home">
        <router-view/>
        <Header/>
        <h1>Logo or something</h1>
        <p>Wow, an auction site! Check out some auctions below. Information and etc and blah blah.</p>
        <div class="mainFlex" v-for="(auction, index) in auctions" :key="index">
            <a :href="auction.url" class="flexSection">
                <div>
                    <p class="title">{{ auction.title }}</p>
                    <summary>{{ auction.description }}</summary>
                    <div class="flex">
                        <p>Seller: {{ auction.seller }}</p>
                        <p class="currBid">{{ auction.currBid }}</p>
                    </div>
                </div>
                <img :src="auction.image" id="image">
            </a>
        </div>
        <Footer/>
    </div>
</template>

<script>
    import Header from "@/components/Header.vue";
    import Footer from "@/components/Footer.vue";

    export default {
        name: 'home',
        components: {
            Header,
            Footer
        },
        data() {
            return {
                auctions: [
                    {
                        title: "Jerboas",
                        currBid: "400 SEK",
                        seller: "Daniel Radcliffe",
                        image: "https://i.pinimg.com/originals/62/db/ee/62dbee7f3c8ae9c12dffa59f7ded1dc9.jpg",
                        description: "Here's this thing and it's just so cute and so interesting. Just look at those ears. I wonder if they make good pets.",
                        url: "/auctionId"
                    },
                    {
                        title: "Jerboas",
                        currBid: "400 SEK",
                        seller: "Daniel Radcliffe",
                        image: "https://i.pinimg.com/originals/62/db/ee/62dbee7f3c8ae9c12dffa59f7ded1dc9.jpg",
                        description: "Here's this thing and it's just so cute and so interesting. Just look at those ears. I wonder if they make good pets.",
                        url: "/auctionId"
                    },
                    {
                        title: "Jerboas",
                        currBid: "400 SEK",
                        seller: "Daniel Radcliffe",
                        image: "https://i.pinimg.com/originals/62/db/ee/62dbee7f3c8ae9c12dffa59f7ded1dc9.jpg",
                        description: "Here's this thing and it's just so cute and so interesting. Just look at those ears. I wonder if they make good pets.",
                        url: "/auctionId"
                    }]
            }
        }
    };
</script>

<style scoped>

    a:hover {
        text-decoration: none;
    }

    .home {
        margin-bottom: 75px;
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
        color: black;
        align-self: center;
    }

    #image {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: .5em;
        width: 15vw;
        margin: .2em;
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
    }

    .currBid {
        font-size: 1.5em;
    }

</style>