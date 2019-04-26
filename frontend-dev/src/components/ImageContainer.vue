<template>
    <div>
        <h1>{{auctionPost.title}}</h1>
        <img :src="imgPath + auctionPost.images[arrayNum] " alt="nej">
        <div class="row">
            <div class="thumbNailHolder" v-for="(image, index) in auctionPost.images" :key="image.id"
                 @click="changeMainPic(index)">
                <img :src="thumbnail + image">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ImageContainer",
        data() {
            return {
                imgPath: '/assets/img/',
                thumbnail: '/assets/img/thumbnail/',
                arrayNum: 0,
            }
        },
        methods: {
            changeMainPic(index) {
                this.arrayNum = index;
            }
        }, computed: {
            auctionPost() {
                return this.$store.state.auctionPosts.filter(auction => auction.auctionid == this.$route.params.auctionid)[0]
            }
        }
    }
</script>

<style scoped>
    .thumbNailHolder {
        padding: 10px 0 10px 0;
        cursor: pointer;
    }

    .row {
        justify-content: center;
    }
</style>