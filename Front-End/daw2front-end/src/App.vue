<template>
  <div id="nav">
    <NavBar />
  </div>
  <router-view />
</template>

<script>
import NavBar from "./components/Leiloes/navbar.vue";
export default {
  components: {
    NavBar,
  },
};
</script>

<style scoped>
</style>