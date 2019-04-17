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
        async createAuction(){
            let auction = {};
            auction.title = document.getElementsByName('aucTitle')[0].value;
            auction.description = document.getElementsByName('aucDescription')[0].value;
            auction.images = await this.uploadPictures();
            console.log(auction);
        },
        async uploadPictures(){
            let imagePaths = [];

            if(this.filestorage.length < 1){
                return null;
            }

            // Upload files to the backend and get filenames
            for(let file of this.filestorage){
                // Append formdata
                let data = new FormData();
                data.append('fullsize', file.fullsize);
                data.append('thumbnail', file.thumbnail);
                // Send files
                let responseFromServer = await fetch('/api/upload/', {
                    method: 'POST',
                    body: data
                });
                responseFromServer = await responseFromServer.text();
                imagePaths.push( {'path': responseFromServer} );
                if(imagePaths.length > 4) break; 
            }
            // console.log(imagePaths);
            return imagePaths;
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
