<template>
  <v-container>
    <h1>Ship locations!</h1>
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
          <td v-for="(number, n) in columns" :key="n">
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
      rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"]
    };
  }
};
</script>

<style>
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
</style>
