<template>
    <div>
        <h1>Profile</h1>
        <div class="mainFlex">
            <div class="flexSection" id="namePic">
                <img :src=mockPerson.picture id="profilePic">
                <h3>{{mockPerson.firstname}} {{mockPerson.lastname}}</h3>
                <button class="btn" id="btnContact">Contact</button>
            </div>
            <div class="flexSection">
                <div class="flex" v-for="auction in mockPerson.auctions">
                    <a :href="auction.url">
                        <p>Auction: {{auction.name}}</p>
                        <p>Current Bid: {{auction.currentBid}}</p>
                        <p>Ending: {{auction.endingTime}}</p>
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
                mockPerson:
                    {
                        firstname: "Leroy",
                        lastname: "Jenkins",
                        auctions: [
                            {name: 'Really cool things', url: '/1', currentBid: '50', endingTime: "sometime"},
                            {name: 'auction 2', url: '/2', currentBid: '50'},
                            {name: 'auction 3', url: '/3', currentBid: '50'}
                        ],
                        picture: "http://www.atabjkpg.se/wp-content/uploads/2017/12/default-user-image.png",
                    }
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

    #profilePic {
        height: 50%;
    }

    #btnContact {
        background-color: rgb(32, 64, 96);
        color: rgb(126, 199, 199);
        width: 30%;
        align-self: center;
    }

    .flex > * {
        display: flex;
        flex-direction: row;
    }

    p{
        margin-right: 2em;
    }
</style>