<template lang="html">
    <div>
        <div>
            <h2>Title</h2>
            <p class="errorText" v-if="this.formErrors && this.formErrors.title">You must provide a selling title</p>
            <input type="text" size="20" placeholder="A selling title" name="aucTitle">
        </div>
        <div>
            <h2>Description</h2>
            <p class="errorText" v-if="this.formErrors && this.formErrors.description">You must provide a selling description</p>
            <textarea name="aucDescription" placeholder="Some selling arguments for your item..." rows="6" cols="80" style="resize: none;"></textarea>
        </div>
        <div>
            <h2>Starting at price</h2>
            <p class="errorText" v-if="this.formErrors && this.formErrors.price">You must provide a starting price</p>
            <input type="text" size="20" placeholder="$10" name="aucPrice" @change="numberInput">
        </div>
        <div>
            <h2>Category</h2>
            <select name="aucCat">
                <option v-for="(cat) of this.categories" :value="cat.categoryid">{{cat.categoryname}}</option>
            </select>
        </div>
        <div>
            <h2>Files</h2>
            <span class="errorText" v-if="this.formErrors && this.formErrors.images">Auctions need pictures</span>
            <ImageUploader />
        </div>
        <div>
            <p class="errorText" v-if="this.formErrors && this.formErrors.server">Something went terribly wrong. Please try again later... </p>
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
    methods: {
        numberInput(){
            let input = document.getElementsByName('aucPrice')[0].value.replace(/^[^0-9]*0*|[^0-9]/g,"");
            let output = "";
            output = input.length > 0 ? "$ " + input : "";
            document.getElementsByName('aucPrice')[0].value = output;
        },
        async createAuction() {
            let images, data, responseFromServer;
            images = await this.uploadPictures();
            console.log(images);

            data = {};
            data.title = document.getElementsByName('aucTitle')[0].value;
            data.description = document.getElementsByName('aucDescription')[0].value;
            data.startprice = document.getElementsByName('aucPrice')[0].value.replace(/^[^0-9]*0*|[^0-9]/g,"");
            data.category = document.getElementsByName('aucCat')[0]
                .options[document.getElementsByName('aucCat')[0].selectedIndex].value;
            data.images = images;


            this.formErrors = this.checkForErrors(data);
            if (!this.formErrors) {
                responseFromServer = await fetch('/api/auction', {
                    method: "POST",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                responseFromServer = await responseFromServer.text();
                this.$store.dispatch('getPostsFromDb');
                console.log("our response", responseFromServer);
                if (!isNaN(responseFromServer)) {
                    this.$router.push('/auction/' + responseFromServer);
                } else {
                    if(!this.formErrors) this.formErrors = {};
                    this.formErrors.server = true;
                }
            }
        },
        async uploadPictures() {
            let imagePaths = [];

            if (this.filestorage.length < 1) {
                return null;
            }

            // Upload files to the backend and get filenames
            for (let file of this.filestorage) {
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
                imagePaths.push({
                    'path': responseFromServer,
                });
            }
            return imagePaths;
        },
        checkForErrors(data) {
            let errors = {};
            if (data.title.length < 1 || data.title.length > 20)
                errors.title = true;
            if (data.description.length < 1 || data.description.length > 255)
                errors.description = true;
            if (!data.images || data.images.length === 0)
                errors.images = true;
            if(data.startprice.length === 0 || isNaN(data.startprice) || data.startprice/1 === 0)
                errors.price = true;
            return Object.keys(errors).length === 0 ? null : errors;
        }
    },
    computed: {
        filestorage() {
            return this.$store.state.currentUploads;
        },
        categories() {
            return this.$store.state.categories;
        }
    }
}
</script>

<style lang="css" scoped>
.errorText{
    color: red;
}
</style>
