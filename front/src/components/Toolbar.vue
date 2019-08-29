<template>
  <div>
    <v-toolbar>
      <v-toolbar-title>
        <router-link to="/">
          <span class="home">Battle</span>
          <span class="font-weight-light home">ship!</span>
        </router-link>
      </v-toolbar-title>

      <div class="flex-grow-1"></div>

      <v-toolbar-items>
        <v-btn text to="games">Games</v-btn>
        <v-btn text to="game/:gamePlayerID">My game</v-btn>
        <v-btn text>Link 3</v-btn>
      </v-toolbar-items>

      <template v-if="$vuetify.breakpoint.smAndUp">
        <v-btn icon>
          <v-icon>mdi-export-variant</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-delete-circle</v-icon>
        </v-btn>
        <v-btn @click="logout" icon>
          <i class="fas fa-sign-out-alt"></i>
        </v-btn>
      </template>
    </v-toolbar>
  </div>
</template>

<script>
export default {
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
