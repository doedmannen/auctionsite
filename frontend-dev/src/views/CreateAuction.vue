<template lang="html">
    <div>
        <div v-if="loggedIn">
            <h1>Create new auction</h1>
            <AuctionForm />
        </div>
        <div v-else>
            <h1>Please login to create a new auction</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CreateAuction',
    components: {
        AuctionForm: () => import('@/components/AuctionForm.vue')
    },
    computed:{
        loggedIn(){
            return this.$store.state.me != null;
        }
    }
}
</script>

<style lang="css" scoped>
</style>
