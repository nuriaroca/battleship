<template>
  <v-container>
    <h1>Home</h1>
    <v-btn @click="login">Login</v-btn>
  </v-container>
</template>

<script>
export default {
  created() {
    this.login();
  },
  method: {
    login() {
      fetch("/api/login", {
        credentials: "include",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST",
        body: getBody(ourData)
      })
        .then(function(data) {
          console.log("Request success: ", data);
        })
        .catch(function(error) {
          console.log("Request failure: ", error);
        });
    }
  }
};
</script>
