<template lang="html">
    <div>
        <Profile/>
    </div>
</template>

<script>
    export default {
        name: 'ProfilePage',
        components: {
            Profile: () => import('@/components/Profile.vue')
        }
    }
</script>

<style lang="css" scoped>
</style>