<template>
<div v-if="campos == true">
    <Atencao msg="Preenche todos os campos."/>
</div>
<div v-if="camposSucesso == true">
<Sucesso msg="Cadastro realizado com sucesso."/>
</div>
  <form>
    <div class="container">
      <h1>Registrar</h1>
      <input type="text" v-model="leilao.username" placeholder="Nome"/>
      <input type="email" v-model="leilao.email" placeholder="Email"/>
      <div class="div2">
        <input type="password" v-model="leilao.senha" placeholder="Senha"/>
      </div>
        <button type="submit"  v-on:click="enviar" class="d-block mr-0 ml-auto">Registrar</button>
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex'
import Atencao from "../Telas/Atenção/Atencao.vue"
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
export default {
    data() {
        return {
            leilao: {
                email: "",
                senha: "",
                username: ""
            },
            campos: Boolean,
            camposSucesso: Boolean
        }
    },
    components: {
        Atencao,
        Sucesso
    },
    methods: {
        ...mapActions(['registrarUser']),
        enviar() {
            const form = {
                email: this.leilao.email,
                senha: this.leilao.senha,
                nome: this.leilao.username
            }
            if(form.nome && form.email && form.senha != ""){
                this.registrarUser(form)
                this.leilao.email = ""
                this.leilao.senha = ""
                this.leilao.username = ""
                this.camposSucesso = true
                setTimeout(() => {
                    this.camposSucesso = false
                }, 2000)
            } else {
                this.campos = true
                setTimeout(() => {
                    this.campos = false
                }, 2000)
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