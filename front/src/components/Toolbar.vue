<template>
  <div>
    <v-toolbar>
      <v-toolbar-title>
        <router-link to="/">
          <span class="home">Battle</span>
          <span class="font-weight-light home">ship!</span>
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <div v-if="$store.state.player != 'guest'">
        <span class="font-weight-light">Hi</span>
        <span>{{" " + $store.state.player}}</span>
      </div>

      <div v-if="$store.state.player == 'guest'">
        <span class="font-weight-light">Log in for a full experience.</span>
      </div>

      <div class="flex-grow-1"></div>
      <v-toolbar-items>
        <v-btn text to="games">Games</v-btn>
        <!-- <v-btn text :to="game/'+gamePlayer.id'">My game</v-btn> -->
        <v-btn text>Link 3</v-btn>
      </v-toolbar-items>

      <template v-if="$vuetify.breakpoint.smAndUp">
        <v-btn v-if="$store.state.player == 'guest'" icon to="/">
          <i class="fas fa-sign-in-alt"></i>
        </v-btn>
        <v-btn v-if="$store.state.player != 'guest'" @click="logout" icon>
          <i class="fas fa-sign-out-alt"></i>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-export-variant</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-delete-circle</v-icon>
        </v-btn>
      </template>
    </v-toolbar>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  props: ["gamePlayerID"],
  methods: {
    logout() {
      fetch("/api/logout", {
        credentials: "include",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST"
      }).then(() => window.location.reload());
    }
  }
};
</script>
<style>
.home {
  color: black;
}

a {
  text-decoration: none;
}
</style>
