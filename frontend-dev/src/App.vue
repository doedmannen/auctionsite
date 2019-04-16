<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
export default {
  name: "App",
  created() {
    this.$store.dispatch("getPostsFromDb");
  }
};

</script>

<style>
    #app {
        font-family: 'Oxygen', sans-serif;
        background-color: whitesmoke;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>
