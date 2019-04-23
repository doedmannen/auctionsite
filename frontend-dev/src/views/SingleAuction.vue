<template>
    <div>
        <b-container class="mainContainer" fluid>
            <b-row>
                <b-col cols="2">
                </b-col>
                <b-col cols="6">
                    <img :src="imgPath + auctionPost.images[arrayNum] " alt="nej">
                    <div class="row">
                        <div class="thumbNailHolder" v-for="(image, index) in auctionPost.images" :key="image.id" @click="changeMainPic(index)">
                            <img :src="thumbnail + image">
                        </div>
                        <div>
                        </div>
                    </div>
                    <div>
                        {{auctionPost.description}}
                    </div>
                </b-col>
                <b-col cols="4">
                    <div>
                        <b-jumbotron>
                            <template slot="lead">
                                <br>
                                <span v-if="auctionPost.bids.length>0">Leading bid at: {{auctionPost.bids[auctionPost.bids.length-1].bidamount}}$</span>
                                <span v-else>Be the first to bid!</span>
                                <br>
                                <span>Asking price: {{auctionPost.startprice}}$ </span>
                            </template>

                            <hr class="my-4">


                                <div>
                                    <h2>Description</h2>
                                    <textarea name="bidAmount" placeholder="Place your bid" rows="6" cols="80" style="resize: none;"></textarea>
                                </div>
                            <p>{{auctionPost.endtime.toString().replace(/T/g," ")}}</p>
                            <p>{{'Seller '+ auctionPost.users.firstname+' '+auctionPost.users.lastname}}</p>
                            <b-button variant="primary" @click="placeBid">Place bid</b-button>
                        </b-jumbotron>
                    </div>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>

    export default {
        name: "SingleAuction",
        computed: {
            auctionPost() {
                return this.$store.state.auctionPosts.filter(auction => auction.auctionid == this.$route.params.auctionid)[0]
            }
        },
        data() {
            return {
                imgPath: '/assets/img/',
                thumbnail: '/assets/img/thumbnail/',
                arrayNum: 0,

            }
        },
        methods: {
            changeMainPic(index){
                this.arrayNum=index;
            },
            async placeBid(){
               let data = {};
               data.bidamount=document.getElementsByName('bidAmount')[0].value;
               data.auctionid=this.$route.params.auctionid;

                await fetch('/api/bid', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
            }
        },
       created(){
       }
    }
</script>

<style scoped>
    .mainContainer {
        margin-top: 10px;
    }
    .thumbNailHolder{
        padding:10px;
        cursor:pointer;
    }
    .row{
        justify-content: center;
    }
</style>