<template>
  <v-container>
    <v-simple-table>
      <thead>
        <tr>
          <th class="text-left">Player</th>
          <th class="text-left">Wins</th>
          <th class="text-left">Loses</th>
          <th class="text-left">Draws</th>
          <th class="text-left">Points</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="player in players" :key="player.userName">
          <!-- v-for="(champion, key, index) in bothFilters"
          v-bind:key="index"
          v-bind:champion="champion"-->
          <td>{{ player.userName }}</td>
        </tr>
      </tbody>
    </v-simple-table>
  </v-container>
</template>

<script>
import { log } from "util";
export default {
  data: () => ({}),
  data() {
    return {
      desserts: [
        {
          name: "Frozen Yogurt",
          calories: 159
        },
        {
          name: "Ice cream sandwich",
          calories: 237
        }
      ]
    };
  },

  created() {
    this.getLeaderBoard();
  },
  methods: {
    getLeaderBoard() {
      fetch("/api/leader_board")
        .then(res => res.json())
        .then(leaderBoard => console.log(leaderBoard));
    }
  }
};
</script>
