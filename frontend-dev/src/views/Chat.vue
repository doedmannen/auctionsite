<template lang="html">
    <div>
        <ChatComponent />
    </div>
</template>

<script>
export default {
    name: 'Chat',
    components:{
        ChatComponent: () => import ('@/components/ChatComponent.vue')
    }
}
</script>

<style lang="css" scoped>
</style>
