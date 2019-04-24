<template>
    <nav class="fixed-bottom navbar navbar-expand-lg">
        <div class="flex mx-auto">
            <p>1 (906) 789-5555</p>
            <p id="logo">Auction Site ©</p>
            <p>PO Box 1033, Los Angeles CA</p>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "Footer",
        props: {}
    };
</script>

<style scoped>
    * {
        color:rgb(32, 64, 96);
    }

    a {
        color: rgb(32, 64, 96);
    }

    .navbar {
        background-color: rgb(126, 199, 199);
    }

    .flex {
        display: flex;
    }

    .flex > p {
        margin: 0 3em;
    }

    #logo {
        font-family: 'Bungee Inline', cursive;
        font-size: 1.25em;
    }

</style>
