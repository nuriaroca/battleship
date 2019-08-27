<template>
  <v-container>
    <h1>Salvo!</h1>
    <div class="tables">
      <div class="table">
        <h4>Games List</h4>
        <v-simple-table>
          <thead>
            <tr>
              <th class="text-left">Game</th>
              <th class="text-left">Date</th>
              <th class="text-left">Player 1</th>
              <th class="text-left">Player 2</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(game, g) in games" :key="g">
              <td>{{ game.id }}</td>
              <td>{{ game.date | moment("dddd, MMMM Do YYYY")}}</td>
              <td>{{ game.gamePlayer[0].player.firstName +" "+ game.gamePlayer[0].player.lastName }}</td>
              <td
                v-if="game.gamePlayer[1]"
              >{{ game.gamePlayer[1].player.firstName +" "+ game.gamePlayer[1].player.lastName }}</td>
              <td v-else>Waiting for the next victim</td>
            </tr>
          </tbody>
        </v-simple-table>
      </div>
      <div class="table">
        <h4>Leader Board</h4>
        <v-simple-table>
          <thead>
            <tr>
              <th class="text-left">User</th>
              <th class="text-left">Wins</th>
              <th class="text-left">Loses</th>
              <th class="text-left">Draws</th>
              <th class="text-left">Points</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(lead, l) in leader" :key="l">
              <td>{{leader}}</td>
              <td>{{leader.wins}}</td>
              <td>{{leader.loses}}</td>
              <td>{{leader.draws}}</td>
              <td>{{leader.points}}</td>
            </tr>
          </tbody>
        </v-simple-table>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      games: {},
      leader: {}
    };
  },
  created() {
    this.getGames();
    this.getLeaderBoard();
  },
  methods: {
    getGames() {
      let that = this;
      fetch("/api/games")
        .then(function(response) {
          if (response.ok) {
            return response.json();
          }
          throw new Error(response.statusText);
        })
        .then(function(json) {
          that.games = json;
          // console.log(json);
        })
        .catch(function(error) {
          console.log("Request failed: " + error.message);
        });
    },
    getLeaderBoard() {
      let that = this;
      fetch("/api/leader_board")
        .then(function(response) {
          if (response.ok) {
            return response.json();
          }
          throw new Error(response.statusText);
        })
        .then(function(json) {
          console.log(json);
          let data = json;

          that.leader = Object.values(data)
            .sort((a, b) => b.points - a.points)
            .forEach(key => {});
          console.log(that.leader);
        })
        .catch(function(error) {
          console.log("Request failed: " + error.message);
        });
    }
  }
};
</script>

<style>
h4 {
  margin-top: 20px;
  margin-bottom: 5px;
}
</style>
