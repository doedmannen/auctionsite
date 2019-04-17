<template>
    <div>
        <Header/>
        <b-container class="mainContainer" fluid>
            <b-row>
                <b-col cols="2">
                </b-col>
                <b-col cols="6">
                    <template>
                        <div class='carousel-view'>
                            <transition-group
                                    class='carousel'
                                    tag="div">
                                <div
                                        v-for="slide in slides"
                                        class='slide'
                                        :key="slide.id">
                                    <img alt="nope"/>
                                </div>
                            </transition-group>
                            <div class='carousel-controls'>
                                <button class='carousel-controls__button' @click="previous">prev</button>
                                <button class='carousel-controls__button' @click="next">next</button>
                            </div>
                        </div>
                    </template>
                    <div v-for="auction in auctionPosts">
                        {{auction.description}}</div></b-col>
                <b-col cols="4"><div>
                    <b-jumbotron>
                        <template slot="lead">
                            Leading bid {highestBid}
                        </template>

                        <hr class="my-4">

                        <template>
                            <div>
                                <b-form-group
                                        id="fieldset-1"
                                        label="Enter your bid"
                                        label-for="input-1"
                                        :invalid-feedback="invalidFeedback"
                                        :valid-feedback="validFeedback"
                                        :state="state"
                                >
                                    <b-form-input id="input-1" v-model="name" :state="state" trim></b-form-input>
                                </b-form-group>
                            </div>
                        </template>


                        <b-button variant="primary" href="#">Place bid</b-button>
                    </b-jumbotron>
                </div></b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import Header from "../components/Header";

    export default {
        name: "SingleAuction",
        components: {Header},
        computed: {
            auctionPosts() {
                return this.$store.state.auctionPosts;
            }
        },
        data () {
            return {
                slides: [
                    {
                        title: 'I am Slide A',
                        id: 1
                    },
                    {
                        title: 'I am Slide B',
                        id: 2
                    },
                    {
                        title: 'I am Slide C',
                        id: 3
                    },
                    {
                        title: 'I am Slide D',
                        id: 4
                    },
                    {
                        title: 'I am Slide E',
                        id: 5
                    }
                ]
            }
        },
        methods: {
            next () {
                const first = this.slides.shift()
                this.slides = this.slides.concat(first)
            },
            previous () {
                const last = this.slides.pop()
                this.slides = [last].concat(this.slides)
            }
        }
    }
</script>

<style scoped>
.mainContainer{
    margin-top: 10px;
}
.carousel-view {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.carousel {
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;

    width: 24em;
    min-height: 25em;
}
.slide {
    flex: 0 0 20em;
    height: 20em;
    margin: 1em;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: transform 0.3s ease-in-out;
}
.slide:first-of-type {
    opacity: 0;
}
.slide:last-of-type {
    opacity: 0;
}
</style>