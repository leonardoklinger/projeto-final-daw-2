<template>
  <div class="CadastroLeilao">
    <cadastrarLeilao />
  </div>
</template>

<script>
import cadastrarLeilao from "../components/Leiloes/cadastrarLeilao.vue";

export default {
  name: "Home",
  components: {
    cadastrarLeilao,
  },
};
</script>
