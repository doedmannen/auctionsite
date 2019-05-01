<template lang="html">
  <main>
      <div v-if="hasPush" class="pushContent" @click="clickedPush">
          <p :id="'auctionurl'+auctionurl">{{msg}}</p>
      </div>
      <div class="bottom">
      </div>
  </main>
</template>

<script>
export default {
    name: 'Push',
    computed: {
        pushMsg(){
            return null;
        },
        hasPush(){
            return null;
        }
    },
    methods: {
        clickedPush(e){
            console.log(e);
        }
    }
}
</script>

<style lang="css" scoped>
main{
    width: 200px;
    height: 200px;
    position: fixed;
    bottom: 0;
    right: 0;
    z-index: 1111;
    margin: 10px;
}
.pushContent{
    height: 150px;
}
.bottom{
    height: 50px;
}
</style>
