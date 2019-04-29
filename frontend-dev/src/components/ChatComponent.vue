<template lang="html">
    <div class="motherland">
        <div class="header">
            <div class="">
                <h4>{{activeChat}}</h4>
            </div>
            <div @click="killConvo"><i class="far fa-times-circle"></i></div>
        </div>
        <div class="main">
            <div v-for="(elem) of this.messages" class="singularity">
                <p class="sender">{{elem.sender.firstname}} {{elem.sender.firstname}}</p>
                <p class="message">{{elem.message}}</p>
            </div>
        </div>
        <div class="footer">
            <input type="text" name="chatbox">
        </div>
    </div>
</template>

<script>
export default {
    name: 'Chat',
    computed: {
        messages(){
            return this.$store.state.chatMessages
                .filter(m => m.receiverid === this.activeChat
                    || m.senderid === this.activeChat);
        },
        activeChat(){
            return this.$store.state.activeChat;
        }
    },
    methods: {
        killConvo(){
            this.$store.commit('setActiveChat', null);
        }
    }

}
</script>

<style lang="css" scoped>
.motherland{
    min-height: 20vh;
    max-height: 20vh;
}
.head{
    display: flex;
    flex-direction: column;
    width: 100%;
}
.main{
    flex: 1;
    overflow-x: hidden;
    overflow-y: scroll;
}
.singularity{

}
.sender{

}
.message{

}
.footer{

}
</style>
