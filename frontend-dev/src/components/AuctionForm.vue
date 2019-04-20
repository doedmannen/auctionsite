<template lang="html">
    <div>
        <div>
            <h2>Title</h2>
            <span v-if="this.formErrors && this.formErrors.title">There are errors here</span>
            <input type="text" size="20" placeholder="A selling title" name="aucTitle">
        </div>
        <div>
            <h2>Description</h2>
            <span v-if="this.formErrors && this.formErrors.description">There are errors here</span>
            <textarea name="aucDescription" placeholder="Some selling arguments for your item..." rows="8" cols="80"></textarea>
        </div>
        <div>
            <h2>Starting at price</h2>
            <input type="text" size="20" placeholder="$100" name="aucPrice">
        </div>
        <div>
            <h2>Category</h2>
            <select name="aucCat">
                <option value="12">Stuff</option>
            </select>
        </div>
        <div>
            <h2>Files</h2>
            <ImageUploader />
            <p>max 5 files</p>
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
    data() {
        return {
            formErrors: null
        }
    },
    methods:{
        async createAuction(){
            let images, data, responseFromServer;
            images = await this.uploadPictures();

            data = {};
            data.title = document.getElementsByName('aucTitle')[0].value;
            data.description = document.getElementsByName('aucDescription')[0].value;
            data.startprice = document.getElementsByName('aucPrice')[0].value;
            data.category = 12;
            data.auctionowner = 3;
            // data.images = images.map(o => o.path);

            this.formErrors = this.checkForErrors(data);
            console.log(data);
            if(!this.formErrors){
                responseFromServer = await fetch('/api/auction', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                responseFromServer = await responseFromServer.text();
                console.log(responseFromServer);
            }
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
            }
            // console.log(imagePaths);
            return imagePaths;
        },
        checkForErrors(data){
            let errors = {};
            if(data.title.length < 1 || data.title.length > 20)
                errors.title = true;
            if(data.description.length < 1 || data.description.length > 255)
                errors.description = true;
            return Object.keys(errors).length === 0 ? null : errors;
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
