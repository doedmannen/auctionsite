<template>
    <div v-if="auctionPost">
        <b-container class="mainContainer" fluid>
            <b-row>
                <b-col cols="2">
                </b-col>
                <b-col cols="6">
                    <h1>{{auctionPost.title}}</h1>
                    <img :src="imgPath + auctionPost.images[arrayNum] " alt="nej">
                    <div class="row">
                        <div class="thumbNailHolder" v-for="(image, index) in auctionPost.images" :key="image.id"
                             @click="changeMainPic(index)">
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
                                <span v-if="auctionPost.bids.length>0">Leading bid at: ${{auctionPost.bids[0].bidamount}}</span>
                                <span v-else>Be the first to bid!</span>
                                <br>
                                <span>Asking price: ${{auctionPost.startprice}} </span>
                            </template>

                            <hr class="my-4">
                            <div v-if="loggedIn">
                                <div v-if="auctionPost.users.userid != this.me.userid">
                                    <h3>Place your bid</h3>
                                    <input type="text" name="bidAmount" placeholder="Place your bid"
                                           @change="parseNumbers"><br>
                                    <b-button variant="primary" @click="placeBid">Place bid</b-button>
                                </div>
                                <div v-else>
                                    <h3>You can't bid on your own auctions</h3>
                                </div>
                            </div>

                            <p>{{auctionPost.endtime.toString().replace(/T/g," ")}}</p>
                            <p>Seller: {{ auctionPost.users.firstname+' '+auctionPost.users.lastname}}</p>
                            <div>
                                <b-button v-b-modal.modal-1 @click="resetLimit">Bid history</b-button>

                                <b-modal id="modal-1" title="Bid History">
                                    <b-container class="bv-example-row">
                                        <b-row>
                                            <b-col>User</b-col>
                                            <b-col>Amount</b-col>
                                            <b-col>Time</b-col>
                                        </b-row>
                                        <hr>
                                    </b-container>
                                    <b-container class="bv-example-row">
                                        <b-row v-for="bid in auctionPost.bids.slice(limit-5,limit)">
                                            <b-col>{{bid.userid}}</b-col>
                                            <b-col>${{bid.bidamount}}</b-col>
                                            <b-col>{{bid.bidtime.toString().replace(/T/g," ")}}</b-col>
                                        </b-row>
                                        <button @click="loadFiveMore">Next 5</button>
                                    </b-container>
                                </b-modal>
                            </div>
                            {{getUsers}}
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
            auctionPosts(){
                return this.$store.state.auctionPosts;
            },

            loggedIn() {
                return this.$store.state.me != null;
            },
            highestBid() {
                return this.auctionPost.bids.length > 0 ? this.auctionPost.bids[0].bidamount / 1 : this.auctionPost.startprice / 1;
            },
            me() {
                return this.$store.state.me;
            },
            getUsers() {
                return this.$store.state.allUsers;
            }
        },
        data() {
            return {
                imgPath: '/assets/img/',
                thumbnail: '/assets/img/thumbnail/',
                arrayNum: 0,
                modalShow: false,
                modalText: "",
                limit: 5,
                date: '',
                time: '',
            }
        },
        methods: {
            changeMainPic(index) {
                this.arrayNum = index;
            },
            loadFiveMore() {
                if(this.auctionPost.bids.length>this.limit){
                    this.limit += 5
                }

            },
            resetLimit() {
                this.limit = 5;
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
            }

        },
        created() {
        }
    }
</script>

<style scoped>
    .mainContainer {
        margin-top: 10px;
    }

    .thumbNailHolder {
        padding: 10px;
        cursor: pointer;
    }

    .row {
        justify-content: center;
    }
</style>
