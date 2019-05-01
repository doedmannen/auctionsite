<template>
    <div id="app">
        <Header/>
            <main>
                <router-view/>
            </main>
        <Footer/>
        <Push />
    </div>
</template>

<script>
    import Header from "@/components/Header.vue";
    import Footer from "@/components/Footer.vue";
    import Push from "@/components/Push.vue";

    export default {
        name: "App",
        components: {
            Header,
            Footer,
            Push
        },
        created() {
            this.$store.dispatch("getPostsFromDb");
            this.$store.dispatch("getCategoriesFromDb");
            this.$store.dispatch("whoami");
            this.$store.dispatch("getChatHistory");
            this.$store.dispatch("connectSocket");
            this.$store.dispatch("getNotifications");
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
        min-height: 100vh;
    }
    main{
        flex: 1;
        padding-bottom: 75px;
    }
</style>
