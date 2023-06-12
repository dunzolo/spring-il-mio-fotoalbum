<script>
import axios from 'axios';

const BASE_API_URL = "http://localhost:8080/api/v1";

export default {
  data() {
    return {
      foto: [],
      ricerca: "",
      messaggio: {
        email: "",
        testo: ""
      }
    }
  },
  methods: {
    getAllFoto() {
      axios.get(BASE_API_URL + "/foto")
        .then(res => {
          const foto = res.data;
          this.foto = foto;
        })
        .catch(err => console.log(err));
    },
    filter() {
      axios.get(BASE_API_URL + "/foto" + "?nome=" + this.ricerca)
        .then(res => {
          const foto = res.data;
          this.foto = foto;
        })
        .catch(err => console.log(err));
    },
    send() {
      axios.get(BASE_API_URL + "/messaggio", this.ricerca)
        .then(res => { })
        .catch(err => console.log(err));
    }
  },
  mounted() {
    this.getAllFoto();
  }
}
</script>

<template>
  <main>
    <h1>Le foto del web</h1>
    <div>
      <input type="text" v-model="ricerca" @keyup="filter()">
    </div>
    <div v-for="item in foto">
      <div class="card" style="width: 18rem;" v-if="item.visibile">
        <img :src="item.url" class="card-img-top h-50">
        <div class="card-body">
          <h5 class="card-title">{{ item.titolo }}</h5>
          <div class="card-text">
            <span class="badge bg-primary me-1" v-for="categoria in item.categorie">{{ categoria.nome }}</span>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>