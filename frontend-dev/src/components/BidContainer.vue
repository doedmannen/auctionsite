<template>
    <div v-if="activeAuction">
        <b-jumbotron>
            <template slot="lead">
                <br>
                <span v-if="auctionPost.bids.length>0">Leading bid at: ${{auctionPost.bids[0].bidamount}}</span>
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
                    <input type="text" name="bidAmount" placeholder="Place your bid"
                           @change="parseNumbers"><br>
                    <b-button variant="primary" @click="placeBid">Place bid</b-button>
                </div>
                <div v-else>
                    <h3>You can't bid on your own auctions</h3>
                </div>
            </div>

            <p>{{auctionPost.endtime.toString().replace(/T/g," ")}}</p>
            <a :href="'/profile/' + auctionPost.users.userid"><p>Seller: {{ auctionPost.users.firstname+' '+auctionPost.users.lastname}}</p></a>
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
                            <b-col>{{bid.user.firstname}}</b-col>
                            <b-col>${{bid.bidamount}}</b-col>
                            <b-col>{{bid.bidtime.toString().replace(/T/g," ")}}</b-col>
                        </b-row>
                        <button @click="loadFiveMore">Next 5</button>
                    </b-container>
                </b-modal>
            </div>
            <div v-if="oneDayRemaining">
                <countdown :end-time="auctionPost.endtime">
                                <span slot="process"
                                      slot-scope="{timeObj}">{{`Time left: ${timeObj.h}:${timeObj.m}:${timeObj.s}` }}</span>
                    <span slot="finish">Done!</span>
                </countdown>
            </div>
        </b-jumbotron>
    </div>
    <div v-else>
        <b-jumbotron>
            <template slot="header">SOLD</template>

            <template slot="lead">
                This is an ended auction
            </template>

            <hr class="my-4">

            <p>
                sold for: ${{auctionPost.bids[0].bidamount}}
            </p>
            <div>
                <b-button v-b-modal.modal-2 @click="resetLimit">Bid history</b-button>

                <b-modal id="modal-2" title="Bid History">
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
                            <b-col>{{bid.user.firstname}}</b-col>
                            <b-col>${{bid.bidamount}}</b-col>
                            <b-col>{{bid.bidtime.toString().replace(/T/g," ")}}</b-col>
                        </b-row>
                        <button @click="loadFiveMore">Next 5</button>
                    </b-container>
                </b-modal>
            </div>
        </b-jumbotron>
        <template>
            <div>
                <b-modal v-model="modalShow">{{modalText}}</b-modal>
            </div>
        </template>
    </div>
</template>

<script>
    export default {
        name: "BidContainer",
        data() {
            return {
                modalShow: false,
                modalText: "",
                limit: 5,
                oneDayInMS: 86400000,
                showMore: false
            }
        },
        methods: {
            loadFiveMore() {
                if (this.auctionPost.bids.length > this.limit) {
                    this.limit += 5
                }

            },
            showMoreToggle() {
                this.showMore = !this.showMore;
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
        },computed: {
            auctionPost() {
                return this.$store.state.auctionPosts.filter(auction => auction.auctionid == this.$route.params.auctionid)[0]
            },
            activeAuction() {
                return Date.now() <= Date.parse(this.auctionPost.endtime.toString())
            },
            oneDayRemaining() {
                return Date.parse(this.auctionPost.endtime.toString()) - this.oneDayInMS < Date.now()
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
            description() {
                let d = this.auctionPost.description;
                if (this.auctionPost.description.length > 50 && !this.showMore) {
                    d = this.auctionPost.description.substring(0, 50) + "...";
                }
                return d;
            }
        }
    }
</script>

<style scoped>
    .showToggle {
        font-size: 10pt;
        font-weight: bold;
        display: block;
        cursor: pointer;
    }
</style>