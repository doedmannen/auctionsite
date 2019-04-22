<template lang="html">
  <div>
      <label class="filePointer" for="poweif23">
          <span>&#x1f4c1; Click here to upload pictures (max 5)</span>
          <input type="file" name="files2upload" id="poweif23" accept="image/*" multiple required @change="loadImages" style="display: none">
      </label>

      <div v-if="this.thumbnailPreview.length > 0" class="pictureFrame">
          <figure v-for="(elem) of this.thumbnailPreview" class="uploadPic" >
              <img :src="elem" alt="picture">
          </figure>
      </div>

  </div>
</template>

<script>
export default {
    name: 'ImageUploader',
    mounted() {
        this.pics = [];
        this.$store.commit('clearUploads');
    },
    methods: {
        async loadImages() {
            this.pics = [];
            this.$store.commit('clearUploads');
            let uploads = document.getElementsByName('files2upload')[0].files;
            for (let pic of uploads) {
                if (!this.fileLimitIsReached) {
                    await this.cropAndResizeImage(pic);
                }
            }
        },
        fileToBase64(file) {
            let reader = new FileReader();
            reader.readAsDataURL(file)
            reader.onload = (e) => {
                return reader.result;
            }
        },
        cropAndResizeImage(pic) {
            return new Promise((res, rej) => {
                let reader = new FileReader();
                reader.readAsDataURL(pic);
                reader.onload = (e) => {
                    let image = new Image();
                    image.onload = () => {
                        let large = this.canvasToFile(image);
                        let thumb = this.canvasToFile(image, 160, 120);
                        this.$store.commit('addUpload', {
                            fullsize: large,
                            thumbnail: thumb
                        });
                        res();
                    }
                    image.src = e.target.result;
                }
            });
        },
        canvasToFile(image, width, height) {
            let s, cx, cy, ix, iy, cvs, ctx, defaultx, defaulty;
            cvs = document.createElement("canvas");
            ctx = cvs.getContext("2d");
            defaultx = 640;
            defaulty = 480;
            cx = width || defaultx;
            cy = height || defaulty;
            cvs.width = cx;
            cvs.height = cy;
            s = cx / image.width;
            s = (image.height * s) > cy ? cy / image.height : s;
            ix = image.width;
            iy = image.height;
            if (s < 1) {
                ix = ix * s;
                iy = iy * s;
            }
            ctx.fillStyle = 'white';
            ctx.fillRect(0,0,cx,cy)
            // Get image into canvas
            ctx.drawImage(image,
                (cx / 2 - ix / 2),
                (cy / 2 - iy / 2), ix, iy);
            // Watermark if not thumbnail
            if (cx === defaultx) {
                ctx.fillStyle = 'black';
                ctx.strokeStyle = 'white';
                ctx.font = (cx * 0.05) + 'px Arial';
                ctx.fillText("auctionsite.com", 10, cy - 30);
                ctx.strokeText("auctionsite.com", 10, cy - 30);
            }
            // Convert base64 to byteArray
            let imgData = cvs.toDataURL('image/jpeg', 1);
            // Append thumbnail to preview
            if (cx != defaultx)
                this.pics.push(imgData);
            // return new file
            return new File([this.dataUrlToFile(imgData)], "file.jpg", {
                type: "image/jpeg"
            });
        },
        dataUrlToFile(data) {
            let byteString = atob(data.split(',')[1]);
            let arrBuffer = new ArrayBuffer(byteString.length);
            let ui8a = new Uint8Array(arrBuffer)
            for (let i = 0; i < byteString.length; i++) {
                ui8a[i] = byteString.charCodeAt(i);
            }
            return ui8a;
        }
    },
    data() {
        return {
            pics: []
        }
    },
    computed: {
        thumbnailPreview() {
            return this.pics;
        },
        filestorage() {
            return this.$store.state.currentUploads;
        },
        fileLimitIsReached() {
            return this.$store.state.currentUploads.length > 4;
        }
    }
}
</script>

<style lang="css" scoped>
.uploadPic{
    display: inline-block;
}
.filePointer{
    cursor: pointer;
}
img{
    width: 100%;
}

</style>
