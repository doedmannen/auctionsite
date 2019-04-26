<template lang="html">
    <div>
        <h1>Chat with</h1>
        <div v-if>

        </div>
    </div>
</template>

<script>
export default {
    name: 'Chat',
    props:{
        
    },
    computed: {
        messages(){
            return this.$store.state.chatMessages;
        }
    }
}
</script>

<style lang="css" scoped>
</style>
