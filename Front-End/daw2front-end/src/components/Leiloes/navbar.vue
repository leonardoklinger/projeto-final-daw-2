<template>
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#"
        ><img
          src="@/assets/logo.png"
          alt="Logo"
          class="img-fluid"
          width="40" 
          height="40"
      /></a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link">INICIO</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/todosartigos" class="nav-link"
              >Leilões</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/cadastrarLeilao" class="nav-link"
              >Cadastrar Leilões</router-link
            >
          </li>
        </ul>
        <div class="d-flex">
          <router-link v-if="validarLogin() == false" to="/login" id="botaoentrar" class="btn ms-3" :click="validarLogin"
            >ENTRAR</router-link
          >
          <router-link v-if="validarLogin() == false" to="/registrar" id="botaocadastrar" class="btn ms-3"
            >CADASTRA-SE</router-link>
            <button v-else id="botaocadastrar" class="btn ms-3" @click="botao()">Logout</button>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import Cookie from "js-cookie";
export default {
  data() {
    return {
    }
  },
  methods: {
    validarLogin() {
      const token = Cookie.get('token')
      if(token){
        return this.verificarLogin = true;
      } else {
        return this.verificarLogin = false;
      }
    },
    botao(){
      Cookie.remove('token')
      this.$router.push({ name: "Inicio" });
      setTimeout(() => {
        this.$router.go()
      }, 500)
    }
  }
}
</script>


<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Staatliches&display=swap");

* {
  font-family: "Bebas Neue", cursive;
  font-size: 20px;
}

.navbar {
    background-color: #1c1f31;
}

.nav-item  .nav-link {
    color:  #8978f2;
}

.nav-item  .nav-link:hover {
    color:  #8978f2;
    opacity: 70%;
    transition: opacity .3s;
}

#botaocadastrar {
  background-color: #1f233a;
  color:  #8978f2;
  border-bottom: 5px solid #0d0e17;
}

#botaocadastrar:hover {
    opacity: 70%;
    transition: opacity .3s;
}

#botaoentrar {
  color: #8978f2;
}

#botaoentrar:hover {
    opacity: 70%;
    transition: opacity .3s;
}
</style>