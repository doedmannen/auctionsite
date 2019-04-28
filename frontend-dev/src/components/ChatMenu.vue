<template lang="html">
    <div class="main" v-if="isValid">
        <div class="contentMenu">
            <b-dropdown variant="link" id="dropdown-login" dropleft ref="dropdownchat" class="m-2" no-caret>
                <template slot="button-content"><i class="fas fa-comments"></i>
                </template>
                <b-dropdown-form>
                    <div v-if="!activeChat">
                        <p class="logo">Chat menu</p>{{hasMessages}}
                        <ul v-if="hasMessages">
                            <li v-for="(elem) in this.myMessages">{{elem}}</li>
                        </ul>
                    </div>
                    <div v-else>

                    </div>
                </b-dropdown-form>
            </b-dropdown>
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
        hasMessages(){
            return this.$store.state.chatMessages.length;
        },
        myMessages(){
            return this.$store.state.chatMessages;
        },
        activeChat(){
            return this.$store.state.activeChat;
        },
        myConversations(){
            let list = {};
            myMessages.map(m => m.sender);
            // ??????????
        }
    }
}
</script>

<style lang="css" scoped>
</style>
