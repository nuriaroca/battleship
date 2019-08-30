import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    player: ""
  },
  mutations: {
    SET_PLAYERLOG: (state, data) => {
      state.player = data
    }
  },
  actions: {
    getPlayerLogged(context) {
      fetch("/api/games")
        .then(function (response) {
          if (response.ok) {
            return response.json();
          }
          throw new Error(response.statusText);
        })
        .then(function (json) {
          context.state.player = json.playerLogged;
        })
        .catch(function (error) {
          console.log("Request failed: " + error.message);
        });
    },
  },
  getters: {
    getPlayer: state => state.player,
  }
})