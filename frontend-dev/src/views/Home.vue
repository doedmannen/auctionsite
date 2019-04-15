<template>
  <div class="home">
    <router-view/>
    <Header/>
    <h1>Welcome to home</h1>
  </div>
</template>

<script>
  import Header from "@/components/Header.vue";

export default {
  name: 'home',
  components: {
    Header
  }
}
</script>

<style>

</style>