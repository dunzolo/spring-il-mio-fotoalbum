<script>
import axios from 'axios';
import AppHeader from '../components/AppHeader.vue'

const BASE_API_URL = "http://localhost:8080/api/v1";

export default {
  components: {
    AppHeader
  },
  data() {
    return {
      messaggio: {
        email: "",
        testo: ""
      }
    }
  },
  methods: {
    send() {
      axios.post(BASE_API_URL + "/messaggio", this.messaggio)
        .then(res => { this.$router.push('/'); })
        .catch(err => console.log(err));
    }
  },
} 
</script>

<template lang="">
  <AppHeader/>
    <form class="container" @submit.prevent="send">
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="text" class="form-control" name="email" v-model="messaggio.email">
      </div>
      <div class="mb-3">
        <label for="testo" class="form-label">Testo</label>
        <input type="text" class="form-control" name="testo" v-model="messaggio.testo">
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</template>

<style lang="">

</style>