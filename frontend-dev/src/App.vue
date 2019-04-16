<template>
    <div id="app">
        <Header/>
        <router-view/>
        <Footer/>
    </div>
</template>

<script>
    import Header from "@/components/Header.vue";
    import Footer from "@/components/Footer.vue";

    export default {
        name: "app",
        components: {
            Header,
            Footer
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
