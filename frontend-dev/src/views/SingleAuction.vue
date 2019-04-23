<template>
    <div>
        <b-container class="mainContainer" fluid>
            <b-row>
                <b-col cols="2">
                </b-col>
                <b-col cols="6">
                    <img :src="imgPath + auctionPost.images[arrayNum] " alt="nej">
                    <div class="row">
                        <div class="thumbNailHolder" v-for="(image, index) in auctionPost.images" @click="changeMainPic(index)">
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

                            <template>
                                <div>
                                    <b-form-group
                                            id="fieldset-1"
                                            label="Enter your bid"
                                            label-for="input-1"
                                            invalid-feedback="invalidFeedback"
                                            valid-feedback="validFeedback"
                                            state="state"
                                    >
                                        <b-form-input id="input-1" v-model="name" state="state" trim></b-form-input>
                                    </b-form-group>
                                </div>
                            </template>

                            <p>{{auctionPost.endtime.toString().replace(/T/g," ")}}</p>

                            <b-button variant="primary" href="#">Place bid</b-button>
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
                arrayNum: 0
            }
        },
        methods: {
            changeMainPic(index){
                this.arrayNum=index;
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>
    .mainContainer {
        margin-top: 10px;
    }
    .thumbNailHolder{
        cursor:pointer;
    }
</style>