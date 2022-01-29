<template>
<div v-if="EditadoSucesso == true">
    <Sucesso msg="Editado com Sucesso"/>
</div>
<div v-if="SemPermissao == true">
    <Erro msg="Sem permissão"/>
</div>
  <form>
    <div class="container">
      <h1>Editar Leilão</h1>
      <input type="text" v-model="leilao.nome" placeholder="Nome Leilão" />
      <div class="div2">
        <input type="text" v-model="leilao.valor" placeholder="Valor do Leilão" />
        <input type="date" v-model="leilao.data" placeholder="Data"/>
      </div>
        <select v-model="leilao.status" class="form-select" aria-label="Default select example">
            <option selected  disabled>-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-</option>
            <option value="ABERTO">Aberto</option>
            <option value="FECHADO">Fechado</option>
            <option value="EXPIRADO">Expirado</option>
            <option value="INATIVO">Inativo</option>
            <option value="FINALIZADO">Finalizado</option>
            <option disabled>-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-</option>
        </select>
        <button type="submit"  v-on:click="salvar" class="d-block mr-0 ml-auto">Editar</button>
    </div>
  </form>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
import Erro from "../Telas/Erro/Erro.vue"
import axios from 'axios'
import Cookie from "js-cookie"
export default {
    data(){
        return {
            id: this.$route.params.id,
            SemPermissao: Boolean,
            EditadoSucesso: Boolean
        }
    },
    components: {
        Erro,
        Sucesso
    },
    methods: {
        ...mapActions(["buscarLeilaoEspecifico"]),
        salvar() {
            const editar = {
                id: this.leilao.id,
                nome: this.leilao.nome,
                valor: this.leilao.valor,
                data: this.leilao.data,
                status: this.leilao.status
            }
        const url = "http://localhost:8080/api/leilao"
        const tokenMain = Cookie.get('token')
        const token = {
            "Authorization": `Bearer ${tokenMain}`
        }
            axios.put(`${url}`, editar, { headers: token }).then((resp) => {
                if(resp.status == 200) {
                    this.EditadoSucesso = true
                    setTimeout(() => {
                        this.EditadoSucesso = false
                    }, 2000)
                }
            }).catch((error) => {
                if(error.response.status == 403) {
                    this.SemPermissao = true
                    setTimeout(() => {
                        this.SemPermissao = false
                    }, 2000)
                }
            })
        }
    },
    computed: {
        ...mapState(["leilao"]),
    },
  created() {
    this.buscarLeilaoEspecifico(this.id);
  },
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

.form-select {
    border: none;
    border-radius: 0;
    color: #524992;
    background: #161624;
}

input::placeholder {
    color: #524992;
}


input[type=date] {
    color: #524992;
}
</style>