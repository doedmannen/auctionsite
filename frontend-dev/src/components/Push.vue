<template lang="html">
  <main>
      <div v-if="hasPush" class="pushContent">
          <div class="header">
              <div class="title">
                  <span v-if="pushType == 'Notification'">New bid</span>
                  <span v-else>New message</span>
              </div>
              <div class="closeButton" @click="closePush">
                  <i class="far fa-times-circle"></i>
              </div>
          </div>
          <div class="textContent" @click="clickPush">
              <div v-if="pushType == 'Notification'">
                  <div v-if="msgObject.bid.user.userid == msgObject.auction.users.userid">
                      <p>
                          <strong>You are loosing an auction. </strong> {{msgObject.bid.user.firstname}} {{ msgObject.bid.user.lastname}} placed a bid of <strong>$ {{msgObject.bid.bidamount}}</strong> on <em><strong>{{msgObject.auction.title}}</strong></em>.
                      </p>
                  </div>
                  <div class="notificationText" v-else>
                      <p>
                          {{msgObject.bid.user.firstname}} {{ msgObject.bid.user.lastname}} placed a bid of <strong>$ {{msgObject.bid.bidamount}}</strong> on your auction <strong><em>{{msgObject.auction.title}}</em></strong>.
                      </p>
                  </div>
              </div>
              <div v-else>
                  <strong>{{msgObject.sender.firstname}} {{msgObject.sender.lastname}}: </strong> {{msgObject.message}}
              </div>
          </div>
      </div>
      <div class="bottom">
      </div>
  </main>
</template>

<script>
export default {
    name: 'Push',
    data(){
        return{
            timer: setTimeout(() => {},1)
        }
    },
    computed: {
        pushMsg(){
            return this.$store.state.pushes[0];
        },
        hasPush(){
            clearTimeout(this.timer);
            this.timer = setTimeout(() => {
                this.closePush();
            },5000);
            return this.$store.state.pushes.length;
        },
        pushType(){
            return this.$store.state.pushes[0].type;
        },
        msgObject(){
            return this.$store.state.pushes[0].msgObject;
        }
    },
    methods: {
        clickPush(){
            if(this.pushType == 'Notification'){
                this.$router.push('/auction/'+this.msgObject.auctionid);
            } else {
                let id = this.msgObject.senderid;
                let name = this.msgObject.sender.firstname + " " + this.msgObject.sender.lastname;
                this.$store.commit('setShowChat', true);
                this.$store.commit('setActiveChat', {id: id, name: name});
            }
            this.closePush();
        },
        closePush(){
            this.$store.dispatch('removePush');
        }
    }
}
</script>

<style lang="css" scoped>
main{
    width: 250px;
    height: 250px;
    position: fixed;
    bottom: 0;
    right: 0;
    z-index: 1111;
    margin: 10px;
}
.pushContent{
    height: 210px;
    background: white;
    border: 1px solid black;
    border-radius: 5px;
}
.bottom{
    height: 40px;
}
.header{
    display: flex;
    flex-direction: row;
    align-items: baseline;
}
.title{
    font-size: 12pt;
    font-weight: bold;
    flex: 1;
}
.closeButton{
    display: inline;
    padding-right: 5px;
    font-size: 16pt;
    margin-left: -15px;
    cursor: pointer;
}
.textContent{
    padding: 0 10px 0 10px;
    font-size: 11pt;
    cursor: pointer;
    height: 180px;
}
</style>
