<template>
  <div class="home">
    <router-view/>
    <Header/>
    <h1>Welcome to home</h1>
    <Footer/>
  </div>
</template>

<script>
  import Header from "@/components/Header.vue";
  import Footer from "@/components/Footer.vue";

export default {
  name: 'home',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
  .home {
    margin-bottom: 75px;
  }

</style>