<template>
  <div class="home">
    <todosLeiloes />
  </div>
</template>

<script>
import todosLeiloes from "../components/Leiloes/todosLeiloes.vue";

export default {
  name: "Home",
  components: {
    todosLeiloes,
  },
};
</script>
