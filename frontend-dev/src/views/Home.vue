<template>
  <div class="home">
    <router-view/>
    <Header/>
    <div class="body">
      <h1 class="logo">Sell your stuff!</h1>
      <p>Wow, an auction site! Check out some auctions below. Information and etc and blah blah.</p>
      
      <div class="mainFlex" v-for="(auction, index) in auctionPosts" :key="index">
          <div>
            <p class="title">{{ auction.title }}</p>
            <summary>{{ auction.description }}</summary>
            <p>{{auction.users.firstname}}</p>
            <p>{{auction.images.path}}</p>
          </div>
      </div>

    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "home",
  components: {
    Header,
    Footer
  },
  computed: {
    auctionPosts() {
      return this.$store.state.auctionPosts; 
    }
  }
};
</script>

<style scoped>
.logo {
  font-family: "Bungee Inline", cursive;
}

a {
  color: black;
}

a:hover {
  text-decoration: none;
}

.home {
  padding-bottom: 75px;
}

.body {
  margin-top: 2em;
}

.mainFlex {
  display: flex;
  flex-direction: column;
}

.flexSection {
  display: flex;
  height: 30vh;
  width: 70vw;
  margin: 1vh;
  border: 2px gray solid;
  align-self: center;
}

#image {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 0.5em;
  width: 15vw;
  margin: 0.2em;
}

.flex {
  display: flex;
  justify-content: space-between;
  margin: 1em;
}

summary {
  text-align: left;
  text-indent: 1em;
  margin: 1em;
}

.title {
  font-size: 1.5em;
  font-weight: bold;
}

.currBid {
  font-size: 1.5em;
}
</style>