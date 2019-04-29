<template>
  <div class="home">
    <router-view/>
    <div class="body">
      <h1 class="logo">Sell your stuff!</h1>
      <p>
        Wow, an auction site! Check out some auctions below, select a category or search for exactly what you
        want.
      </p>
      <input
        class="searchBox"
        type="text"
        v-on:keyup="searchInDb"
        v-model="searchAuctions"
        :placeholder="searchPlaceholder"
      >
      <!--Söker när man släpper en tangent-->
      <!--v-model = sparar det man sökt, value-->

      <div class="flexActivComp">
        <p class="filterby">Click to show:</p>

        <span
          @click="toggleActive"
          class="filterby filter"
          :style="this.showActive ? this.showCompleted = false: this.showCompleted = true"
          style="cursor: pointer;"
        >{{this.showActive ? "Active": "Completed"}}</span>
      </div>

      <div class="mainFlex">
        <div
          class="border"
          v-for="(auction, index) in auctionPosts.slice(0,auctionsShown)"
          :key="index"
        >
          <a :href="'/auction/' + auction.auctionid">
            <b-container class="container">
              <b-row>
                <b-col>
                  <img :src="'/assets/img/thumbnail/'+auction.images[0]" id="image">
                </b-col>
              </b-row>

              <b-row class="flex">
                <b-col>
                  <p class="title">{{ auction.title }}</p>
                </b-col>
                <b-col>
                  <p
                    class="textLeft"
                    v-if="auction.description.length > 40"
                  >{{auction.description.substring(0,40)+"..."}}</p>
                  <p class="textLeft" v-else>{{auction.description}}</p>
                </b-col>
              </b-row>

              <b-row class>
                <b-col>
                  <p
                    class="textLeft"
                    v-if="auction.bids.length > 0"
                  >Price: ${{ auction.bids[auction.bids.length-1].bidamount }} Bids: {{ auction.bids.length }}</p>
                  <p
                    class="textLeft"
                    v-else
                  >Price: ${{auction.startprice }} Bids: {{ auction.bids.length }}</p>
                </b-col>
                <b-col>
                  <p class="textRight">{{auction.endtime.replace(/T/g, "~").toString().slice(5, 16)}}</p>
                </b-col>
              </b-row>
            </b-container>
          </a>
        </div>
      </div>

      <!--  <div class="mainFlex" v-for="(auction, index) in auctionPosts.slice(0,auctionsShown)" :key="index">
            <a :href="'/auction/' + auction.auctionid" class="flexSection">
             <img :src="'/assets/img/thumbnail/'+auction.images[0]" id="image">
                <div class="flex">
                    <p class="title">{{ auction.title }}</p>
                    <p>{{ auction.description }}</p>
                        <a :href="'/profile/' + auction.users.userid">
                            <p>Seller: {{ auction.users.firstname }} {{ auction.users.lastname }}</p>
                        </a>
                    
                        <p class="currBid" v-if="auction.bids.length > 0">Price: ${{ auction.bids[auction.bids.length-1].bidamount }} ~ Bids: {{ auction.bids.length  }}</p>
                        <p class="currBid" v-else>Price: ${{auction.startprice }} ~ Bids: {{ auction.bids.length }}</p>
                        <p class="currBid"> EndTime: {{auction.endtime.replace(/T/g, " ")}} </p>   
                </div>
            </a>
      </div>-->

      <div class="showMoreButton">
        <b-button class="showMore mt-2" v-on:click="loadMore">Load more</b-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  mounted() {
    console.log();
    console.log();
  },
  data() {
    return {
      searchAuctions: "",
      showActive: false,
      showCompleted: true,
      auctionsShown: 6
    };
  },
  computed: {
    auctionPosts() {
      let ap = this.$store.state.auctionPosts;
      let active = [];
      let complete = [];
      let timeNow = new Date();
      let path = this.$route.path.split("/");
      if (path[1] != "" && path[2]) {
        let cat = this.$store.state.categories.filter(
          c => c.categoryname == this.$route.params.cat
        );
        ap = this.$store.state.auctionPosts.filter(
          auction => auction.category == cat[0].categoryid
        );
      }

      if (this.showActive) {
        active = ap.filter(auction => {
          let dat = auction.endtime.replace(/T/g, " ");
          return timeNow > new Date(dat);
        });
      }
      if (this.showCompleted) {
        complete = ap.filter(auction => {
          let dat = auction.endtime.replace(/T/g, " ");
          return timeNow <= new Date(dat);
        });
      }

      return active.concat(complete);
    },
    searchPlaceholder() {
      return this.$route.params.cat
        ? "Search in " + this.$route.params.cat
        : "Search";
    }
  },
  methods: {
    toggleActive() {
      this.showActive = !this.showActive;
    },
    toggleComplete() {
      this.showCompleted = !this.showCompleted;
    },
    loadMore() {
      this.auctionsShown += 3;
    },
    searchInDb() {
      this.$store.commit("homeSearchInDb", this.searchAuctions);
      //skickar value till store/mutated
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

.body {
  margin-top: 2em;
}

.border {
  background-color: white;
  margin: 0.5rem;
  box-shadow: 2px 2px 5px gray;
}

.mainFlex {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 80vw;
  margin-left: 10vw;
}

.flexSection {
  display: flex;
  width: 70vw;
  margin: 1vh;
  border: 2px gray solid;
  align-self: center;
  background-color: white;
  box-shadow: 2px 2px 5px gray;
}

#image {
  padding: 0.5em;
  width: 300px;
  height: 240px;
  margin: 0.2em;
}

.flexActivComp {
  display: flex;
  width: 20vw;
  margin-left: 43vw;
  margin-bottom: 32px;
}

.filterby {
  padding-right: 0.5rem;
  margin-top: 5px;
}

.filter {
  text-shadow: 2px 2px 2px gray;
}

.title {
  font-size: 1.5em;
  font-weight: bold;
}

.textLeft {
  float: left;
}

.textRight{
    float: right;
}

.searchBox {
  margin-bottom: 0;
}

.flex {
  display: flex;
  flex-direction: column;
}
</style>
