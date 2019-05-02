<template lang="html">
    <div class="main" v-if="isValid">
        <div class="buttonClass" @click="chatWindowToggle">
            <i class="fas fa-comments"></i><span v-if="unreadMessages"> {{unreadMessages}}</span>
        </div>
        <div class="chatWindow" v-if="chatVisible">
            <div v-if="!activeChat">
                <p class="sublogo">Chat menu</p>
                <div class="convoContainer" v-if="hasConversations">
                    <div v-for="(elem) in this.myConversations"
                    class="singleConversation"
                    :id="'userchat'+elem.userid"
                    @click="openConversation">
                    <div :class="(isOnline(elem.email) ? 'online':'offline')">
                        <i class="fas fa-circle"></i>
                    </div>
                    <div class="nameContainer">
                        {{elem.firstname}} {{elem.lastname}}
                    </div>
                    <div class="unread">
                        {{(unreadMessagesSpecific(elem.userid))}}
                    </div>
                    </div>
                </div>
            </div>
            <div v-else>
                <ChatComponent />
            </div>
        </div>


        <!-- Ignore -->
        <div class="invisiblecover"
        v-if="chatVisible"
        @click="closeChatWindow">

        </div>
    </div>
</template>

<script>
export default {
    name: 'Chat',
    components:{
        ChatComponent: () => import ('@/components/ChatComponent.vue')
    },
    computed: {
        isValid(){
            return this.$store.state.socketConnected && this.$store.state.me;
        },
        hasConversations(){
            return this.$store.state.chatMessages.length != 0;
        },
        myMessages(){
            return this.$store.state.chatMessages;
        },
        activeChat(){
            return this.$store.state.activeChat;
        },
        myConversations(){
            let hash = {}, list = [];
            for(let m of this.$store.state.chatMessages){
                hash[m.receiverid] = m.receiver;
                hash[m.senderid] = m.sender;
            }
            delete hash[this.$store.state.me.userid];
            for(let id in hash){
                list.push(hash[id])
            }
            return list;
        },
        me(){
            return this.$store.state.me;
        },
        unreadMessages(){
            return this.$store.state.chatMessages
                .filter(m => !m.hasread)
                .filter(m => m.receiverid == this.me.userid)
                .length
        },
        chatVisible(){
            this.$store.dispatch("getOnlineList");
            return this.$store.state.showChat;
        }
    },
    methods: {
        isOnline(email){
            return this.$store.state.onlineList.includes(email);
        },
        openConversation(e){
            let name = e.target.innerHTML.replace(/ {2,}/g, "").replace(/\[^\w ]/g, "");
            let id = e.target.parentElement.id.replace(/[^0-9]/g,"")/1;
            this.$store.commit('setActiveChat', {id: id, name: name});
        },
        closeChatWindow(){
            this.$store.commit('setShowChat', false);
        },
        chatWindowToggle(){
            this.$store.commit('setShowChat', !this.chatVisible);
        },
        unreadMessagesSpecific(id){
            let size = this.$store.state.chatMessages
                .filter(m => !m.hasread)
                .filter(m => m.receiverid == this.me.userid)
                .filter(m => m.senderid == id)
                .length;
            if(size){
                return ''+size+' new';
            }
        }
    }

}
</script>

<style lang="css" scoped>
.singleConversation{
    cursor: pointer;
    display: flex;
    align-items: center;
}
.nameContainer{
    flex: 1;
    margin-left: 15px;
}
.unread{
    font-style: italic;
}
.invisiblecover{
    min-height: 100vh;
    min-width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9000;
}
.convoContainer{
    text-align: left;
    width: 19vw;
    height: 41vh;
    overflow-y: scroll;
}
.chatWindow{
    padding: 0;
    position: absolute;
    min-width: 20vw;
    max-width: 20vw;
    min-height: 50vh;
    max-height: 50vh;
    z-index: 9001;
    margin-left: -22vw;
    margin-top: -5vh;
    background: white;
    border-radius: 4px;
    border: 1px solid lightgrey;
    display: flex;
    justify-content: center;
    box-shadow: 2px 2px 5px;
}
.buttonClass{
    margin-right: 40px;
    cursor: pointer;
}
.online{
    font-size: 6pt;
    padding-right: 5px;
    margin-left: 10px;
}
.online > *{
    color: green;
}
.offline{
    font-size: 6pt;
    padding-right: 5px;
    margin-left: 10px;
}
.offline > *{
    color: red;
}
</style>
