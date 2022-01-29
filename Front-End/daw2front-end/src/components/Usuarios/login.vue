<template>
<div v-if="usersenhaerrada == true">
    <Erro msg="E-mail / Senha incorreto !"/>
</div>
<div v-if="sucessoLogado == true">
    <Sucesso msg="Logado com sucesso"/>
</div>
  <form>
    <div class="container">
      <h1>Login</h1>
      <input type="email" v-model="leilao.email" placeholder="Email"/>
      <div class="div2">
        <input type="password" v-model="leilao.senha" placeholder="Senha"/>
      </div>
        <button type="submit"  v-on:click="enviar" class="d-block mr-0 ml-auto">Login</button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
import Cookie from "js-cookie";
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
import Erro from "../Telas/Erro/Erro.vue"
export default {
    data() {
        return {
            leilao: {
                email: "",
                senha: "",
            },
            usersenhaerrada: Boolean,
            sucessoLogado: Boolean
        }
    },
    components: {
        Sucesso,
        Erro
    },
    methods: {
        enviar() {
            const payload = {
                email: this.leilao.email,
                senha: this.leilao.senha,
            };
            if(this.leilao.email != "" || this.leilao.senha) {
                axios.post("http://localhost:8080/api/usuario/validarLogin", payload)
                .then((resp) => {
                    if(resp.status == 200) {
                        Cookie.set("token", resp.data.token);
                            if(resp.data.token) {
                                this.sucessoLogado = true
                                setTimeout(() => {
                                    this.sucessoLogado = false
                                    this.$router.push({ name: "Inicio" });
                                    setTimeout(() => {
                                        this.$router.go()
                                    }, 500)
                                }, 2000)
                            }
                    }
                }) .catch((error) => {
                    if(error.response.status == 400) {
                        this.usersenhaerrada = true
                        setTimeout(() => {
                            this.usersenhaerrada = false
                        }, 2000)
                    }
                });
                this.leilao.email = ""
                this.leilao.senha = ""
            }
        }
    }
}
</script>


<style scoped>
h1 {
    display: flex;
    justify-content: center;
    color: #8978f2;
}
label {
    color: white;
}

button {
    width: 150px;
    height: 40px;
    border: none;
    background-color: #1d2032;
    border-bottom: 5px solid #0d0e17;
    color: #7365cb;
}

button:hover {
    opacity: 70%;
    transition: 0.3s;
}

input {
    border: none;
    background-color: #161624;
    height: 35px;
    color: #524992;
    padding-left: 15px;
    outline: 0;
}

.container {
    margin-top: 3%;
    display: flex;
    justify-content: space-around;
    flex-direction: column;
    width: 50%;
    height: 300px;
    background-color: #1e2339;
    border-bottom: 5px solid #0d0e17;
}

.div2  input{
    width: 100%;
}

.container div {
    display: flex;
    justify-content: space-between;
}

.mr-0 {
  margin-right: 0;
}
.ml-auto {
  margin-left:auto;
}

input::placeholder {
    color: #524992;
}

input[type=date] {
    color: #524992;
}
</style>