<script>
export default {

}
</script>

<template lang="">
  <header>
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid justify-content-center">
        <div>
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'contatti'}" class="nav-link">Contattaci</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<style lang="">

</style>