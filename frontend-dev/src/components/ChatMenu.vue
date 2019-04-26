<template lang="html">
    <div class="main" v-if="isValid">
        <div class="contentMenu">
            <b-dropdown variant="link" id="dropdown-login" dropleft ref="dropdownlogin" class="m-2" no-caret>
                <template slot="button-content"><i class="fas fa-comments"></i>
                </template>
                <b-dropdown-form>
                    <p class="logo">Chat menu</p>
                    <ul v-if="">
                        <li></li>
                    </ul>
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
            return this.$store.state.chatMessages;
        }
    }
}
</script>

<style lang="css" scoped>
</style>
