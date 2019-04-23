<template lang="html">
    <div>
        <h1>Create new auction</h1>
        <AuctionForm />
    </div>
</template>

<script>
export default {
    name: 'CreateAuction',
    components: {
        AuctionForm: () => import('@/components/AuctionForm.vue')
    }
}
</script>

<style lang="css" scoped>
</style>
