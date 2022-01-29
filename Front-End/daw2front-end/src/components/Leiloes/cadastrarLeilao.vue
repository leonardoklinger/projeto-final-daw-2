<template>
<div v-if="atencao == true">
<Atencao msg="Preenche todos os campos."/>
</div>
<div v-if="alerta == true">
<Sucesso msg="Cadastro realizado com sucesso."/>
</div>
<div v-if="cadastroErro == true">
<Erro msg="Erro, você não possui permissão para cadastrar um Leilão !"/>
</div>
  <form>
    <div class="container">
      <h1>Cadastro Leilão</h1>
      <input type="text" v-model="leilao.nome" placeholder="Nome Leilão"/>
      <div class="div2">
        <!-- <label>Last name:</label> -->
        <input type="text" v-model="leilao.valor" placeholder="Valor do Leilão"/>
        <!-- <label>Data:</label> -->
        <input type="date" v-model="leilao.data" placeholder="Data"/>
      </div>
        <button type="submit"  v-on:click="cadastrarleilao" class="d-block mr-0 ml-auto">Cadastrar</button>
    </div>
  </form>
</template>

<script>
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
import Atencao from "../Telas/Atenção/Atencao.vue"
import Erro from "../Telas/Erro/Erro.vue"
import axios from "axios"
import Cookie from "js-cookie"
export default {
    data() {
        return {
            leilao: {
                nome: "",
                valor: "",
                data: ""
            },
            alerta: Boolean,
            atencao: Boolean,
            cadastroErro: Boolean
        }
    },
    components: {
        Sucesso,
        Atencao,
        Erro
    },
    methods: {
        cadastrarleilao() {
            const tokenMain = Cookie.get('token')
            const token = {
                "Authorization": `Bearer ${tokenMain}`
            }
            const enviarForm = {
                nome: this.leilao.nome,
                valor: this.leilao.valor,
                data: this.leilao.data.split('/').reverse().join('-')
            }

            if(enviarForm.nome && enviarForm.valor && enviarForm.data != ""){
                axios.post(`http://localhost:8080/api/leilao`, enviarForm, { headers: token })
                .then((resp) => {
                    if(resp.status == 200) {
                        this.alerta = true
                            setTimeout(() => {
                                this.alerta = false
                            }, 2000)
                        this.leilao.nome = ""
                        this.leilao.valor = ""
                        this.leilao.data = ""
                    } 
                }).catch((error) => {
                    if(error.response.status == 400) {
                        this.cadastroErro = true
                        setTimeout(() => {
                            this.cadastroErro = false
                        }, 2000)
                    }
                })
            } else {
                this.atencao = true
                setTimeout(() => {
                    this.atencao = false
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
    width: 48%;
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