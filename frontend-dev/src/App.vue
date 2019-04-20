<template>
    <div id="app">
        <Header/>
            <main>
                <router-view/>
            </main>
        <Footer/>
    </div>
</template>

<script>
    import Header from "@/components/Header.vue";
    import Footer from "@/components/Footer.vue";

    export default {
        name: "App",
        components: {
            Header,
            Footer
        },
        created() {
            this.$store.dispatch("getPostsFromDb");
        }
    };

</script>

<style scoped>
    #app {
        font-family: 'Oxygen', sans-serif;
        background-color: whitesmoke;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        display: flex;
        flex-direction: column;
        height: 100vh;
    }
    main{
        flex: 1;
        padding-bottom: 75px;
    }
</style>
