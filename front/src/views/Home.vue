<template>
  <v-container class="welcome container">
    <h1>Home</h1>
    <div class="card-content center-align"></div>

    <v-autocomplete v-model="name" label="User" :items="names"></v-autocomplete>
    <v-text-field
      v-model="pwd"
      :rules="[rules.required]"
      :type="show1 ? 'text' : 'password'"
      name="input-10-1"
      :append-icon="show1 ? 'fas fa-eye-slash' : 'fas fa-eye'"
      label="Password"
      @click:append="show1 = !show1"
      v-on:keyup.enter="login"
    ></v-text-field>

    <v-btn @click="login">
      Log in
      <i class="fas fa-sign-in-alt"></i>
    </v-btn>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      pwd: "",
      name: "",
      names: [
        "j.bauer@ctu.gov",
        "c.obrian@ctu.gov",
        "kim_bauer@gmail.com",
        "t.almeida@ctu.gov"
      ],
      show1: false,
      password: "Password",
      rules: {
        required: value => !!value || "Required",
        // min: v => v.length >= 8 || "Min 8 characters",
        emailMatch: () => "The email and password you entered don't match"
      }
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
        })
        .then(() => window.location.reload());
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
<style >
.fa-sign-in-alt {
  margin-left: 5px;
}
</style>