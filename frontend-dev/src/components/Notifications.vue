<template lang="html">
    <div class="main" v-if="isValid">
        <div class="buttonClass" @click="notificationWindowToggle">
            <i class="fas fa-bell"></i><span v-if="unreadNotifications"> {{unreadNotifications}}</span>
        </div>
        <div class="notificationWindow" v-if="notificationVisible">
            <p class="logo">Notifications</p>
            <div v-if="hasNotifications" class="notificationList">
                <!-- <div class="hideScroll"> -->
                    <!--  -->
                <!-- </div> -->
                <div class="notificationContainer" v-for="(elem) in myNotifications"
                @click="closeNotificationWindow">
                <router-link :to="'/auction/'+elem.auctionid">
                    <span v-if="!elem.hasread" class="unreadNotification"> NEW </span>
                    <div class="notificationText" v-if="elem.bid.user.userid == elem.auction.users.userid">
                        <p>
                            <strong>You are loosing an auction. </strong> {{elem.bid.user.firstname}} {{ elem.bid.user.lastname}} placed a bid of <strong>$ {{elem.bid.bidamount}}</strong> on <em><strong>{{elem.auction.title}}</strong></em>.
                        </p>
                    </div>
                    <div class="notificationText" v-else>
                        <p>
                            {{elem.bid.user.firstname}} {{ elem.bid.user.lastname}} placed a bid of <strong>$ {{elem.bid.bidamount}}</strong> on your auction <strong><em>{{elem.auction.title}}</em></strong>.
                        </p>
                    </div>
                </router-link>
                </div>
            </div>
            <div v-else>
                <p>You have no notifications</p>
            </div>
        </div>


        <!-- Ignore -->
        <div class="invisiblecover"
        v-if="notificationVisible"
        @click="closeNotificationWindow">

        </div>
    </div>
</template>

<script>
export default {
    name: 'Chat',
    data(){
        return {
            showNotifications: false
        }
    },
    computed: {
        isValid(){
            return this.$store.state.socketConnected && this.$store.state.me;
        },
        hasNotifications(){
            console.log(this.$store.state.notifications);
            return this.$store.state.notifications.length != 0;
        },
        myNotifications(){
            return this.$store.state.notifications;
        },
        unreadNotifications(){
                return this.$store.state.notifications
                .filter(m => !m.hasread)
                .length
        },
        notificationVisible(){
            return this.showNotifications;
        }
    },
    methods: {
        closeNotificationWindow(){
            this.showNotifications = false;
            this.$store.dispatch('markNotificationsAsRead');

        },
        notificationWindowToggle(){
            this.showNotifications = !this.showNotifications;
        }
    }

}
</script>

<style lang="css" scoped>
.buttonClass{
    margin-right: 35px;
    cursor: pointer;
}
.invisiblecover{
    min-height: 100vh;
    min-width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9000;
}
.notificationContainer{
    text-align: left;
    min-width: 20vw;
    font-size: 10pt;
    cursor: pointer;
    padding: 5px 5px 0px 10px;
}
.notificationWindow{
    padding: 0;
    position: absolute;
    min-width: 25vw;
    max-width: 25vw;
    min-height: 50vh;
    max-height: 50vh;
    z-index: 9001;
    margin-left: -27vw;
    margin-top: -5vh;
    background: white;
    border-radius: 4px;
    border: 1px solid lightgrey;
    display: flex;
    flex-direction: column;
}
.notificationText{
    display: inline;
}
.notificationText > *{
    display: inline;
}
.unreadNotification{
    font-style: italic;
    font-weight: bold;
    color: red;
    margin-right: 10px;
}
.notificationList{
    overflow-y: scroll;
    overflow-x: hidden;
    height: 40vh;
}
.hideScroll{
    background: white;
    width: 18px;
    height: 40vh;
    position: absolute;
    margin-left: 23.3vw;
}
</style>
