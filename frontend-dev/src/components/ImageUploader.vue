<template lang="html">
  <div>
      <label class="filePointer" for="poweif23">
          <span>&#x1f4c1; Click here to upload pictures (max 5)</span>
          <input type="file" name="files2upload" id="poweif23" accept="image/*" multiple required @change="loadImages" style="display: none">
      </label>

      <div v-if="this.thumbnailPreview.length > 0" class="imageFrame">
          <div v-for="(elem, index) of this.thumbnailPreview" :class="['isolatedImage', index == 0 ? 'primaryImage':'']">
              <div :id="'imageContainer'+index">
                  <i class="fas fa-arrow-circle-left arrow" v-if="index != 0" @click="moveImage"> </i>
                  <i class="fas fa-arrow-circle-right arrow" v-if="index+1 != thumbnailPreview.length" @click="moveImage"> </i>
              </div>
              <figure class="uploadPic">
                  <img :src="elem">
              </figure>
              <span class="primaryDisplay" v-if="index == 0">Primary image</span>
          </div>
      </div>
      <div v-else>
          <p>No pictures have been chosen</p>
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
        },
        moveImage(e){
            let dir, index, newIndex, tmp;
            dir = e.originalTarget.className.includes("right") ? 1 : -1;
            index = e.originalTarget.parentElement.id.replace(/[^0-9]/g,"");
            dir = dir / 1;
            index = index / 1;
            newIndex = index + dir;
            tmp = this.pics.splice(index, 1)[0];
            this.pics.splice(newIndex,0,tmp);
            this.$store.commit('orderUploads', {
                index: index,
                newIndex: newIndex
            })
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
.primaryImage{
    border: 3px solid blue;
}
.filePointer{
    cursor: pointer;
}
img{
    width: 100%;
}
.imageFrame{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.isolatedImage{
    display: inline-block;
    display: flex;
    flex-direction: column;
}
.arrow{
    display: inline;
    padding: 0 5px 0 5px;
    cursor: pointer;
}
.primaryDisplay{
    font-size: 10pt;
    font-weight: bold;
}
</style>
