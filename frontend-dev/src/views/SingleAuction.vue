<template>
<div v-if="auctionPost">
    <b-container class="mainContainer" fluid>
        <b-row>
            <b-col cols="2">
            </b-col>
            <b-col cols="7" class="contentContainer">
                <h1>{{auctionPost.title}}</h1>
                <img :src="imgPath + auctionPost.images[arrayNum] " alt="nej">
                <div class="row">
                    <div class="thumbNailHolder" v-for="(image, index) in auctionPost.images" :key="image.id" @click="changeMainPic(index)">
                        <img :src="thumbnail + image">
                    </div>
                    <div>
                    </div>
                </div>
            </b-col>
            <b-col cols="3">
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
                        <div>
                            <h4>Description: </h4>
                            <p>{{description}}
                                <span class="showToggle" v-if="auctionPost.description.length > 50" @click="showMoreToggle">[
                                    {{(this.showMore ? 'Show less' : 'Show more')}}
                                ]</span>
                            </p>
                        </div>
                        <hr class="my-4">
                        <div v-if="loggedIn">
                            <div v-if="auctionPost.users.userid != this.me.userid">
                                <h3>Place your bid</h3>
                                <input type="text" name="bidAmount" placeholder="Place your bid" @change="parseNumbers"><br>
                                <b-button variant="primary" @click="placeBid">Place bid</b-button>
                            </div>
                            <div v-else>
                                <h3>You can't bid on your own auctions</h3>
                            </div>
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
            <b-modal v-model="modalShow">{{modalText}}</b-modal>
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
        loggedIn() {
            return this.$store.state.me != null;
        },
        highestBid() {
            return this.auctionPost.bids.length > 0 ? this.auctionPost.bids[this.auctionPost.bids.length - 1].bidamount / 1 :  this.auctionPost.startprice / 1;
        },
        me(){
            return this.$store.state.me;
        },
        description(){
            let d = this.auctionPost.description;
            if(this.auctionPost.description.length > 50 && !this.showMore){
                d = this.auctionPost.description.substring(0,50)+"...";
            }
            return d;
        }
    },
    data() {
        return {
            imgPath: '/assets/img/',
            thumbnail: '/assets/img/thumbnail/',
            arrayNum: 0,
            modalShow: false,
            modalText: "",
            showMore: false
        }
    },
    methods: {
        changeMainPic(index) {
            this.arrayNum = index;
        },
        async placeBid() {
            let data = {};
            data.bidamount = document.getElementsByName('bidAmount')[0].value.replace(/[^0-9]/g, "");
            data.auctionid = this.$route.params.auctionid;

            if (data.bidamount > this.highestBid) {
                await fetch('/api/bid', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                document.getElementsByName('bidAmount')[0].value = "";
                this.$store.dispatch("getPostsFromDb");
                this.modalText = "Bid placed!"
            } else {
                this.modalText = "Your bid was to low";
            }

            this.modalShow = true;
        },
        parseNumbers() {
            let input = document.getElementsByName('bidAmount')[0].value.replace(/^[^0-9]*0*|[^0-9]/g, '');
            let output = input.length > 0 ? '$ ' + input : '';
            document.getElementsByName('bidAmount')[0].value = output
        },
        showMoreToggle(){
            this.showMore = !this.showMore;
        }
    },
    created() {}
}
</script>

<style scoped>
.mainContainer {
    margin-top: 10px;
}

.thumbNailHolder {
    padding: 10px 0 10px 0;
    cursor: pointer;
}

.row {
    justify-content: center;
}
.contentContainer{
    background: white;
}
.showToggle{
    font-size: 10pt;
    font-weight: bold;
    display: block;
    cursor: pointer;
}
</style>
