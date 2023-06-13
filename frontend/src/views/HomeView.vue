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
      foto: [],
      ricerca: "",

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
      axios.get(BASE_API_URL + "/ricerca" + "?titolo=" + this.ricerca)
        .then(res => {
          const foto = res.data;
          this.foto = foto;
        })
        .catch(err => console.log(err));
    },
  },
  mounted() {
    this.getAllFoto();
  }
}
</script>

<template>
  <main>
    <AppHeader />
    <div class="container">
      <h1 class="text-center mb-3">Le foto del web</h1>
      <form class="d-flex mb-3">
        <input class="form-control me-2" type="text" placeholder="Titolo" v-model="ricerca" @keyup="filter()">
        <button class="btn btn-outline-success" type="submit">Cerca</button>
      </form>
      <div class="row">
        <div v-for="item in foto" class="col-3 text-center mb-3">
          <div class="card" style="height: 300px" v-if="item.visibile">
            <img :src="item.url" class="card-img-top h-75">
            <div class="card-body">
              <h5 class="card-title">{{ item.titolo }}</h5>
              <div class="card-text">
                <span class="badge bg-primary me-1" v-for="categoria in item.categorie">{{ categoria.nome }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>