<template>
  <v-container class="welcome container">
    <h1>Home</h1>
    <div class="card-content center-align"></div>
    <input v-model="name">
    <input v-model="pwd">

    <v-btn @click="login">Log in</v-btn>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      pwd: ""
    };
  },
  methods: {
    login() {
      var ourData = {
        name: this.name,
        pwd: this.pwd
      };
      fetch("/api/login", {
        credentials: "include",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST",
        body: this.getBody(ourData)
      })
        .then(function(data) {
          console.log("Request success: ", data);
        })
        .catch(function(error) {
          console.log("Request failure: ", error);
        });
    },
    getBody(json) {
      var body = [];
      for (var key in json) {
        var encKey = encodeURIComponent(key);
        var encVal = encodeURIComponent(json[key]);
        body.push(encKey + "=" + encVal);
      }
      return body.join("&");
    }
  }
};
</script>