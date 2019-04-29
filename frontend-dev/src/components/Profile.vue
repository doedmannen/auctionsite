<template>
    <div>
        <div class="mainFlex">
            <div class="flexSection" id="namePic">
                <i class="fas fa-cat" id="profileIcon"></i>
                <h3>{{indivProfile.users.firstname}} {{indivProfile.users.lastname}}</h3>

                    <div v-if="me && indivProfile.users.userid == me.userid">
                        <button type="button" class="btn btnStyle" data-toggle="modal" data-target="#exampleModal"> Edit
                            icon
                        </button>

                        <!-- Change details modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">My details</h5>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Save changes"></button>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h5>Change icon</h5>
                                        <i id="smile" class="fas fa-smile" @click="changeActive('smile')"
                                           v-bind:class="{ active: isActive }"></i>
                                        <i id="cat" class="fas fa-cat" @click="changeActive('cat')"
                                           v-bind:class="{ active: isActive }"></i>
                                        <i id="dragon" class="fas fa-dragon" @click="changeActive('dragon')"
                                           v-bind:class="{ active: isActive }"></i>
                                        <i id="hippo" class="fas fa-hippo" @click="changeActive('hippo')"
                                           v-bind:class="{ active: isActive }"></i>
                                        <hr>
                                        <h5>Change color</h5>
                                        <span class="dot color1"></span>
                                        <span class="dot color2"></span>
                                        <span class="dot color3"></span>
                                        <span class="dot color4"></span>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btnStyle" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btnStyle">Save changes</button>
                                    </div>
                                </div>

                        </div>
                    </div>
                </div>


                <button class="btn btnStyle">Contact</button>
            </div>
            <div class="flexSection">
                <h3>Auctions</h3>
                <div class="flex" v-for="auction in indivAuctions">
                    <a :href="'/auction/' + auction.auctionid">
                        <p>Auction: {{auction.title}}</p>
                        <p>Ending: {{auction.endtime.toString().replace(/T/g," ")}}</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Profile',
        data() {
            return {
                isActive: false
            }
        },
        computed: {
            indivProfile() {
                return this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid)[0];
            },
            indivAuctions() {
                return this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid);
            },
            me() {
                return this.$store.state.me;
            },
            loggedIn() {
                return this.$store.state.me != null;
            }
        },
        methods: {
            showModal() {
                let element = this.$refs.modal.$el;
                $(element).modal('show');
            },
            changeActive(icon) {
                document.getElementById(icon).isActive = !this.isActive;
            }
        }
    };
</script>

<style scoped>
    .mainFlex {
        display: flex;
        justify-content: center;
        margin-top: 3em;
    }

    .flexSection {
        display: flex;
        flex-direction: column;

    }

    #namePic {
        margin-right: 5em;
        width: 30%;
    }

    #profileIcon {
        font-size: 7em;
        align-self: center;
    }

    .btnStyle {
        background-color: rgb(32, 64, 96);
        color: rgb(126, 199, 199);
        width: 30%;
        align-self: center;
        margin-bottom: 0.5em;
    }

    .flex > * {
        display: flex;
        flex-direction: row;
    }

    p {
        margin-right: 2em;
    }

    .fas {
        padding: 0.5em;
        font-size: 2em;
        outline: #0c5460;
    }

    .dot {
        height: 30px;
        width: 30px;
        border-radius: 50%;
        display: inline-block;
        margin: 1em;
    }

    .color1 {
        background-color: cornflowerblue;
    }

    .color2 {
        background-color: #e83e8c;
    }

    .color3 {
        background-color: limegreen;
    }

    .color4 {
        background-color: gold;
    }

    .active {
        color: rgb(126, 199, 199);
    }
</style>