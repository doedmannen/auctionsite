<template>
    <div>
        <h1>Profile</h1>
        <div class="mainFlex">
            <div class="flexSection" id="namePic">
                <img src="http://www.atabjkpg.se/wp-content/uploads/2017/12/default-user-image.png" id="profileIcon">
                <h3>{{indivProfile.users.firstname}} {{indivProfile.users.lastname}}</h3>
                <div v-if="this.me != null">
                    <div v-if="indivProfile.users.userid = this.me.userid">
                    <button type="button" class="btn btnStyle" data-toggle="modal" data-target="#exampleModal"> Edit details</button>

                    <!-- Change details modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">My details</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Save changes"></button>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h5>Change icon</h5>
                                    <i class="far fa-smile"></i>
                                    <i class="fas fa-cat"></i>
                                    <i class="fas fa-dragon"></i>
                                    <i class="fas fa-hippo"></i>
                                    <hr>
                                    <h5>Change color</h5>
                                    <span class="color"></span>
                                    <span class="color"></span>
                                    <span class="color"></span>
                                    <span class="color"></span>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btnStyle" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btnStyle">Save changes</button>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>


                <button class="btn btnStyle">Contact</button>
            </div>
            <div class="flexSection">
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
            return {}
        },
        computed: {
            indivProfile() {
                console.log(this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid)[0])

                return this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid)[0];
            },
            indivAuctions() {
                console.log(this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid))

                return this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid);
            },
            me(){
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
            }
        }
    };
</script>

<style>
    .mainFlex {
        display: flex;
        justify-content: center;
        margin-top: 3em;
    }

    .flexSection {
        display: flex;
        flex-direction: column;

    }

    .flexSection > * {
        margin-bottom: 1em;
    }

    #namePic {
        margin-right: 5em;
        width: 30%;
    }

    #profileIcon {
        height: 50%;
    }

    .btnStyle {
        background-color: rgb(32, 64, 96);
        color: rgb(126, 199, 199);
        width: 30%;
        align-self: center;
    }

    .flex > * {
        display: flex;
        flex-direction: row;
    }

    p {
        margin-right: 2em;
    }

    .far, .fas {
        padding: 0.5em;
        font-size: 2em;
    }

    .color {
        height: 30px;
        width: 30px;
        background-color: cornflowerblue;
        border-radius: 50%;
        display: inline-block;
        margin: 1em;
    }
</style>