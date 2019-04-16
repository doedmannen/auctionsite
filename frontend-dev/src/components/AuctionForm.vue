<template lang="html">
    <div>
        <div>
            <input type="text" size="20" placeholder="A selling title" name="aucTitle">
        </div>
        <div>
            <textarea name="aucDescription" placeholder="Some selling arguments for your item..." rows="8" cols="80"></textarea>
        </div>
        <div>
            <ImageUploader />
        </div>
        <div>
            <button type="button" name="auctionButton" @click="createAuction">Create auction</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'AuctionForm',
    components: {
        ImageUploader: () => import('@/components/ImageUploader.vue')
    },
    methods:{
        createAuction(){
            this.uploadPictures();

        },
        async uploadPictures(){
            if(this.filestorage.length < 1){
                console.log("No files to upload");
                return false;
            }
            console.log(this.filestorage);

            let data = new FormData();
            data.append('file', this.filestorage[0])

            let responseFromServer = await fetch('/api/upload/', {
                method: 'POST',
                body: data
            });

            responseFromServer = await responseFromServer.text();
            console.log(responseFromServer);

            return true;
        }
    },
    computed: {
        filestorage(){
            return this.$store.state.currentUploads;
        }
    }
}
</script>

<style lang="css" scoped>

</style>
