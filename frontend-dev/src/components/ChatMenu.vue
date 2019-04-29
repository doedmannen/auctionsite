<template lang="html">
    <div class="main" v-if="isValid">
        <div class="buttonClass" @click="chatWindowToggle">
            <i class="fas fa-comments"></i>
        </div>
        <div class="chatWindow" v-if="chatVisible">
            <div v-if="!activeChat">
                <p class="logo">Chat menu</p>
                <div class="convoContainer" v-if="hasConversations">
                    <div v-for="(elem) in this.myConversations"
                    class="singleConversation"
                    :id="'userchat'+elem.userid"
                    @click="openConversation">
                    <div class="activityIndication">
                        <i class="fas fa-circle"></i>
                    </div>
                    {{elem.firstname}} {{elem.lastname}}</div>
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
    data(){
        return {
            chatVisible: false
        }
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
        }
    },
    methods: {
        openConversation(e){
            let id = e.target.id.replace(/[^0-9]/g,"")/1;
            this.$store.commit('setActiveChat', id);
            console.log(e.target.id.replace(/[^0-9]/g,""));
        },
        closeChatWindow(){
            this.chatVisible = false;
        },
        chatWindowToggle(){
            this.chatVisible = !this.chatVisible;
        }
    }

}
</script>

<style lang="css" scoped>
.singleConversation{
    cursor: pointer;
}
.invisiblecover{
    min-height: 100vh;
    min-width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 9000;
}
.convoContainer{
    text-align: left;
}
.chatWindow{
    padding: 0;
    position: fixed;
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
    justify-content: center;
}
.buttonClass{
    margin-right: 30px;
    cursor: pointer;
}
.activityIndication{
    color: green;
    display: inline;
    font-size: 6pt;
}
</style>
