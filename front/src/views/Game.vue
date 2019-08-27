<template>
  <v-container>
    <h1>Ship locations!</h1>
    <div v-if="game">
      <div class="players">
        <div>
          <b
            v-if="gamePlayerID == game.gamePlayers[0].id"
          >{{game.gamePlayers[0].player.firstName}} {{game.gamePlayers[0].player.lastName}}</b>
          <span
            v-else
          >{{game.gamePlayers[0].player.firstName}} {{game.gamePlayers[0].player.lastName}}</span>
          <span>{{" "}}VS{{" "}}</span>
          <b
            v-if="gamePlayerID == game.gamePlayers[1].id"
          >{{game.gamePlayers[1].player.firstName}} {{game.gamePlayers[1].player.lastName}}</b>
          <span
            v-else
          >{{game.gamePlayers[1].player.firstName}} {{game.gamePlayers[1].player.lastName}}</span>
        </div>
      </div>
      <li v-for="(ship, s) in game.ships" :key="s">{{ship.type}}: {{ship.location}}</li>
    </div>
    <div class="tables">
      <div class="table">
        <h4>Ship Gird</h4>
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
              <td v-for="(number, n) in columns" :key="n" :id="letter+number+'mine'">
                <span class="letter" v-if="n==0">{{letter}}</span>
                <span v-else></span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="table">
        <h4>Salvo Gird</h4>
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
              <td v-for="(number, n) in columns" :key="n" :id="letter+number+'opp'">
                <span class="letter" v-if="n==0">{{letter}}</span>
                <span v-else></span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
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
      this.getMySalvoes();
      this.getOppSalvoes();
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
        })
        .catch(function(error) {
          console.log("Request failed: " + error.message);
        });
    },
    paintShips() {
      this.game.ships.forEach(ship => {
        ship.location.forEach(loc => {
          document
            .getElementById(loc + "mine")
            .setAttribute("class", ship.type);
        });
      });
    },
    getMySalvoes() {
      this.game.salvo.forEach(sal => {
        sal.salvoLocations.forEach(loc => {
          document
            .getElementById(loc + "opp")
            .setAttribute("class", "mySalvoes");
        });
      });
    },
    getOppSalvoes() {
      this.game.salvoEnemy.forEach(sal => {
        sal.salvoLocations.forEach(loc => {
          document
            .getElementById(loc + "mine")
            .setAttribute("class", "oppSalvoes");
        });
      });
    }
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
.tables {
  display: flex;
  align-content: space-between;
}
.table {
  display: flex;
  flex-direction: column;
}
.letter {
  font-weight: bold;
}
h4 {
  margin-top: 20px;
  margin-bottom: 5px;
}
th {
  width: 40px;
  height: 40px;
}
td {
  text-align: center;
  width: 40px;
  height: 40px;
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
.mySalvoes {
  background-color: pink;
}
.oppSalvoes {
  background-color: cadetblue;
}
</style>
