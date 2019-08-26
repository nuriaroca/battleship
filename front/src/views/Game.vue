<template>
  <v-container>
    <h1>Ship locations!</h1>
    <div v-if="game">
      <div class="players">
        <div>
          <b
            v-if="gamePlayerID == game.gamePlayers[0].id"
          >{{game.gamePlayers[0].player.firstName}} {{game.gamePlayers[0].player.lastName}}</b>
          <p v-else>{{game.gamePlayers[0].player.firstName}} {{game.gamePlayers[0].player.lastName}}</p>
          <p>VS</p>
          <b
            v-if="gamePlayerID == game.gamePlayers[1].id"
          >{{game.gamePlayers[1].player.firstName}} {{game.gamePlayers[1].player.lastName}}</b>
          <p v-else>{{game.gamePlayers[1].player.firstName}} {{game.gamePlayers[1].player.lastName}}</p>
        </div>
      </div>
      <li v-for="(ship, s) in game.ships" :key="s">{{ship.type}}: {{ship.location}}</li>
    </div>
    <table>
      <thead>
        <tr>
          <th v-for="(number, n) in columns" :key="n">
            <span v-if="n==0"></span>
            <span v-else>{{columns[n]}}</span>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(letter,l) in rows" :key="l">
          <td v-for="(number, n) in columns" :key="n" :id="letter+number">
            <span class="letter" v-if="n==0">{{letter}}</span>
            <span v-else>{{letter + number}}</span>
          </td>
        </tr>
      </tbody>
    </table>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
      game: null,
      gamePlayers: []
    };
  },
  created() {
    this.gameURL();
  },
  props: ["gamePlayerID"],
  watch: {
    game: function() {
      this.paintShips();
    }
  },
  methods: {
    gameURL() {
      let that = this;
      fetch(`/api/games_view/${that.gamePlayerID}`)
        .then(function(response) {
          if (response.ok) {
            return response.json();
          }
          throw new Error(response.statusText);
        })
        .then(function(json) {
          that.game = json;
          console.log(json);
          // that.paintShips();
        })
        .catch(function(error) {
          console.log("Request failed: " + error.message);
        });
    },
    paintShips() {
      this.game.ships.forEach(ship => {
        ship.location.forEach(loc => {
          document.getElementById(loc).setAttribute("class", ship.type);
        });
      });
    }
    // playerID() {
    //   this.game.gamePlayers.forEach(id => {
    //     if (gamePlayerID == id) {
    //     }
    //   });
    // }
  }
};
</script>

<style>
.players {
  text-align: center;
}
table {
  margin: auto;
  border-collapse: collapse;
}
.letter {
  font-weight: bold;
}
th {
  width: 50px;
  height: 50px;
}
td {
  text-align: center;
  width: 50px;
  height: 50px;
  border: solid 1px;
}
tr:first-child {
  border: none;
}
td:first-child {
  border: none;
}
.Destroyer {
  background-color: red;
  font-weight: bold;
}
.Submarine {
  background-color: blue;
  font-weight: bold;
}
.Patrol {
  background-color: yellow;
  font-weight: bold;
}
</style>
