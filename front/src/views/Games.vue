<template>
  <v-container>
    <h1>Salvo!</h1>
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
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      games: {}
    };
  },
  created() {
    this.getGames();
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
          console.log(json);
        })
        .catch(function(error) {
          console.log("Request failed: " + error.message);
        });
    }
  }
};
</script>

<style>
</style>
