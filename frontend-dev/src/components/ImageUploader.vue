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
                    this.$store.commit('addUpload', file._v)
                    console.log(file._v);
                }
                image.src = e.target.result;
            }
        },
        async canvasToFile(image, width, height){
                let s, cx, cy, ix, iy, cvs, ctx;
                cvs = document.createElement("canvas");
                ctx = cvs.getContext("2d");
                cx = width || 640;
                cy = height || 480;
                cvs.width = cx;
                cvs.height = cy;
                // ctx.fillStyle = 'white';
                // ctx.fillRect(0,0, cx, cy);
                s = cx / image.width;
                s = (image.height * s) > cy ? cy / image.height : s;
                ix = image.width;
                iy = image.height;
                if(s < 1){
                    ix = ix * s;
                    iy = iy * s;
                }
                // Get image into canvas
                ctx.drawImage(image,
                    (cx / 2 - ix / 2),
                    (cy / 2 - iy / 2), ix, iy);
                // Watermark
                ctx.fillStyle = 'black';
                ctx.strokeStyle = 'white';
                ctx.font = '26px Arial';
                ctx.fillText("auctionsite.com", 10, cy-30);
                ctx.strokeText("auctionsite.com", 10, cy-30);
                // Convert base64 to byteArray
                let imgData = cvs.toDataURL('image/png');
                // Append the resized file to our document
                this.pics.push(imgData);
                // return new file
                return new File([this.dataUrlToFile(imgData)], "file.png", {type: "image/png"});
        },
        dataUrlToFile(data){
            let byteString = atob(data.split(',')[1]);
            let arrBuffer = new ArrayBuffer(byteString.length);
            let ui8a = new Uint8Array(arrBuffer)
            for(let i = 0; i < byteString.length; i++){
                ui8a[i] = byteString.charCodeAt(i);
            }
            return ui8a;
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
