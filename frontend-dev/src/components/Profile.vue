<template>
    <div>
        <div class="mainFlex" v-if="user">
            <div class="flexSection" id="namePic">
                <i :class="getAvatar" :style="'color: '+indivProfile.users.avatar_color" id="profileIcon"></i>
                <h3>{{indivProfile.users.firstname}} {{indivProfile.users.lastname}}</h3>

                <div v-if="me && indivProfile.users.userid == me.userid">
                    <button type="button" class="btn btnStyle" data-toggle="modal" data-target="#iconModal"> Edit
                        icon
                    </button>

                    <!-- Change details modal -->
                    <div class="modal fade" id="iconModal" tabindex="-1" role="dialog" aria-hidden="true">
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
                                    <div :class="fakeComputed">
                                        <i :class="me.avatar_class" :style="'color: '+currentColorHolder" id="iconChoice"></i>
                                        <h5>Change icon</h5>
                                        <button class="icon" @click="changeIcon"><i id="smile" class="fas fa-smile"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i class="fas fa-laugh-beam"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i id="dragon" class="fas fa-dragon"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i id="hippo" class="fas fa-hippo"></i>
                                        </button>
                                    </div>
                                    <div>
                                        <button class="icon" @click="changeIcon"><i id="cat" class="fas fa-cat"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i class="fas fa-kiwi-bird"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i class="fas fa-horse"></i>
                                        </button>
                                        <button class="icon" @click="changeIcon"><i class="fas fa-user-secret"></i>
                                        </button>
                                    </div>
                                    <div>
                                        <h5>Change color</h5>
                                        <button class="icon" @click="changeColor"><i class="fas fa-circle" style="color: black;" id="colorblack"></i>
                                        </button>
                                        <button class="icon" @click="changeColor"><i class="fas fa-circle" style="color: green;" id="colorgreen"></i>
                                        </button>
                                        <button class="icon" @click="changeColor"><i class="fas fa-circle" style="color: blue;" id="colorblue"></i>
                                        </button>
                                        <button class="icon" @click="changeColor"><i class="fas fa-circle" style="color: brown;" id="colorbrown"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btnStyle" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btnStyle" @click="saveIcon">Save changes</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div v-else>
                    <button v-if="me" class="btn btnStyle" @click="openChatWithUser">Start chat</button>
                </div>
            </div>
            <div class="flexSection">
                <h3>{{indivProfile.users.firstname}}'s Auctions</h3>
                <div v-if="indivAuctions.length > 0">
                    <div class="flex" v-for="auction in indivAuctions">
                        <div id="left">
                            <router-link :to="'/auction/' + auction.auctionid">
                                Auction: {{auction.title}}
                            </router-link>
                        </div>
                        <div id="right">
                            Ending: {{auction.endtime.toString().replace(/T/g," ")}}
                        </div>
                        <div class="clear"></div>
                    </div>

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
                isActive: false,
                user: null,
                avatar: null,
                avatar_c: ''
            }
        },
        mounted() {
            this.getUserFromDB();
        },
        computed: {
            indivProfile() {
                return this.user
            },
            fakeComputed(){
                this.avatar_c = this.avatar_c || this.me.avatar_color;
                return '';
            },
            indivAuctions() {
                return this.$store.state.auctionPosts.filter(auction => auction.users.userid == this.$route.params.userid);
            },
            me() {
                return this.$store.state.me;
            },
            loggedIn() {
                return this.$store.state.me != null;
            },
            getAvatar() {
                return this.avatar || this.indivProfile.users.avatar_class;
            },
            currentColorHolder(){
                return this.avatar_c;
            }
        },
        methods: {
            async getUserFromDB() {
                let id = this.$route.params.userid
                let user = await (await fetch('/api/user/profile/' + id)).json();
                this.user = {users: user}
            },

            showModal() {
                let element = this.$refs.modal.$el;
                $(element).modal('show');
            },
            changeIcon(e) {
                let iconChoice = document.getElementById("iconChoice");
                iconChoice.className = e.target.className;
            },
            changeColor(e) {
                this.avatar_c = e.target.id.replace("color", "");
                console.log(this.avatar_c);
            },
            async saveIcon() {
                let data = {};
                data.avatar_class = document.getElementById("iconChoice").className;
                data.avatar_color = this.avatar_c;
                this.avatar = data.avatar_class;
                document.getElementById('profileIcon').style = "color: " + data.avatar_color;


                $('#iconModal').modal('toggle');

                await fetch('/api/user', {
                    method: "PUT",
                    body: JSON.stringify(data),
                    headers: {
                        "content-type": "application/json"
                    }
                });
                this.$store.dispatch('whoami');
            },
            openChatWithUser(){
                let id = this.indivProfile.users.userid;
                let name = this.indivProfile.users.firstname + ' ' + this.indivProfile.users.lastname;
                this.$store.commit('setActiveChat', {id: id, name: name});
                this.$store.commit('setShowChat', true);
            }
        }
    }
    ;
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
        margin-bottom: 0.5em;
    }

    .fas {
        padding: 0.5em;
        font-size: 2em;
        outline: #0c5460;
    }

    .icon {
        background-color: white;
        border: none;
        outline: none;
    }

    #left {
        width: 250px;
        float: left;
        text-align: left;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        display: block;
    }

    #right {
        margin-left: 250px;
    }

    .clear {
        clear: both;
    }
</style>
