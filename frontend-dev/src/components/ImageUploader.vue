<template lang="html">
  <div>
      <input type="file" name="files2upload" accept="image/*" multiple required @change="loadImages">

      <div v-if="this.pictures4uploads.length > 0" class="pictureFrame">
          <figure v-for="(elem) of this.pictures4uploads" class="uploadPic" >
              <img :src="elem" alt="picture">
          </figure>
      </div>

  </div>
</template>

<script>
export default {
    name: 'ImageUploader',
    methods:{
        loadImages(e){
            this.pics = [];
            this.$store.commit('clearUploads');
            let uploads = document.getElementsByName('files2upload')[0].files;
            for(let pic of uploads){
                this.cropAndResizeImage(pic);
            }
        },
        cropAndResizeImage(pic){
            let reader = new FileReader();

            reader.readAsDataURL(pic);

            reader.onload = (e) => {
                let image = new Image();
                image.onload = () => {
                    let file = this.canvasToFile(image);
                    this.$store.commit('addUpload', file)
                }
                image.src = e.target.result;
            }
        },
        canvasToFile(image){
                let s, cx, cy, ix, iy, cvs, ctx;
                cvs = document.createElement("canvas");
                ctx = cvs.getContext("2d");
                cx = 800;
                cy = 600;
                cvs.width = cx;
                cvs.height = cy;
                // ctx.fillStyle = 'white';
                // ctx.fillRect(0,0, cx, cy);
                s = cx / image.width;
                s = (image.height * s) > cy ? cy / image.height : s;
                ix = image.width * s;
                iy = image.height * s;
                ctx.drawImage(image,
                    (cx / 2 - ix / 2),
                    (cy / 2 - iy / 2), ix, iy);
                ctx.fillStyle = 'black';
                ctx.strokeStyle = 'white';
                ctx.font = '26px Arial';
                ctx.fillText("auctionsite.com", 10, cy-30);
                ctx.strokeText("auctionsite.com", 10, cy-30);
                this.pics.push(cvs.toDataURL('image/png'));
                return new File([cvs.toDataURL('image/png', 0.1)], "file.png", {type: "image/png"});
        },
        async urlToFile(url, fileName, mimeType){
            let url = 
        }
    },
    data(){
        return{
            pics: []
        }
    },
    computed: {
        pictures4uploads(){
            return this.pics;
        },
        filestorage(){
            return this.$store.state.currentUploads;
        }
    }
}
</script>

<style lang="css" scoped>
.uploadPic{
    max-height: 150px;
    max-width: 200px;
    display: inline-block;
}
.pictureFrame{
}
img{
    width: 100%;
}

</style>
