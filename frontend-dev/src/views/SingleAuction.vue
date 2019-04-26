<template>
    <div v-if="auctionPost">
        <b-container class="mainContainer" fluid>
            <b-row>
                <b-col cols="2">
                </b-col>
                <b-col class="contentContainer" cols="7">
                    <image-container/>
                </b-col>
                <b-col cols="3">
                    <bid-container/>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import ImageContainer from "../components/ImageContainer";
    import BidContainer from "../components/BidContainer";
    export default {
        name: "SingleAuction",
        components: {BidContainer, ImageContainer},
        computed: {
            auctionPost() {
                return this.$store.state.auctionPosts.filter(auction => auction.auctionid == this.$route.params.auctionid)[0]
            },
            auctionPosts() {
                return this.$store.state.auctionPosts;
            }
        }
    }
</script>

<style scoped>
    .mainContainer {
        margin-top: 10px;
    }
    .contentContainer{
        background: white;
    }
</style>