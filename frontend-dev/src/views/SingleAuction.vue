<template>
    <div v-if="auctionPost">
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
                                <span v-if="auctionPost.bids.length>0">Leading bid at: ${{auctionPost.bids[auctionPost.bids.length-1].bidamount}}</span>
                                <span v-else>Be the first to bid!</span>
                                <br>
                                <span>Asking price: ${{auctionPost.startprice}} </span>
                            </template>

                            <hr class="my-4">

                                <div v-if="loggedIn">
                                    <h3>Place your bid</h3>
                                    <input type="text" name="bidAmount" placeholder="Place your bid" @change="parseNumbers"><br>
                                    <b-button variant="primary" @click="placeBid">Place bid</b-button>
                                </div>
                            <p>{{auctionPost.endtime.toString().replace(/T/g," ")}}</p>
                            <p>Seller: {{ auctionPost.users.firstname+' '+auctionPost.users.lastname}}</p>
                        </b-jumbotron>
                    </div>
                </b-col>
            </b-row>
        </b-container>
        <template>
            <div>
                <b-modal v-model="modalShow">Bid Placed!</b-modal>
            </div>
        </template>
    </div>
</template>

<script>

    export default {
        name: "SingleAuction",
        computed: {
            auctionPost() {
                return this.$store.state.auctionPosts.filter(auction => auction.auctionid == this.$route.params.auctionid)[0]
            },
            loggedIn(){
                return this.$store.state.me!=null;
            }
        },
        data() {
            return {
                imgPath: '/assets/img/',
                thumbnail: '/assets/img/thumbnail/',
                arrayNum: 0,
                modalShow: false

            }
        },
        methods: {
            changeMainPic(index){
                this.arrayNum=index;
            },
            async placeBid(){
               let data = {};
               data.bidamount=document.getElementsByName('bidAmount')[0].value.replace(/[^0-9]/g,"");
               data.auctionid=this.$route.params.auctionid;
               let currentBid=this.auctionPost.bids[this.auctionPost.bids.length-1].bidamount;

               if(data.bidamount>currentBid){
                await fetch('/api/bid', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                this.$store.dispatch("getPostsFromDb");
                this.modalShow=true;}

            },
            parseNumbers(){
                let input=document.getElementsByName('bidAmount')[0].value.replace(/^[^0-9]*0*|[^0-9]/g,'');
                let output= input.length>0? '$ ' + input:'';
                document.getElementsByName('bidAmount')[0].value=output
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