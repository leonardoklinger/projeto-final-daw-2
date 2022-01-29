<template>
<div v-if="leiloes == ''">
    <Atencao msg="Nenhum Leilão encontrado !"/>
</div>
<div v-if="MsgExclusaoSucesso == true">
  <Sucesso :msg="MsgExclusao"/>
</div>
<div v-if="ErroExclusao == true">
  <Erro :msg="ErroMsgExclusao"/>
</div>
  <div v-for="leilao of leiloes" :key="leilao.id" class="divFor">
    <a class="div-main">
      <div class="card">
        <router-link :to="{ name: 'LeilaoEspecifico', params: { id: leilao.id } }">
        <img src="@/assets/dp.png" alt="Logo" class="imgCabecalho" />
        </router-link>
        <div class="corpo-div">
          <h1 class="titulo">{{ leilao.nome.length > 10 ? leilao.nome.slice(0, 9) + "..." : leilao.nome }}</h1>
          <p class="preco">R$ {{ leilao.valor }}</p>
          <div class="pai">
            <div class="esquerda">
              <p class="data">Data Expiração</p>
              <p class="data-info">{{ leilao.data == null ? "Data invalida": leilao.data.split('-').reverse().join('/') }}</p>
            </div>
            <div class="direita">
              <p class="status">Status</p>
              <p class="status-info">{{ leilao.status }}</p>
            </div>
          </div>
          <div class="crud">
            <div class="dropdown">
            <a type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"><img src="@/assets/engrenagem.png" alt=""></a>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <router-link class="dropdown-item" :to="{ name: 'EditarLeilao', params: { id: leilao.id } }">Editar</router-link>
                <li><a class="dropdown-item" v-on:click="excluir(leilao.id, leilao.nome)">Excluir</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </a>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Atencao from "../Telas/Atenção/Atencao.vue"
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
import Erro from "../Telas/Erro/Erro.vue"
import axios from 'axios'
import Cookie from "js-cookie"
export default {
  data() {
    return {
      MsgExclusao: "",
      MsgExclusaoSucesso: Boolean,
      ErroMsgExclusao: "",
      ErroExclusao: Boolean
    }
  },
  components: {
    Atencao,
    Erro,
    Sucesso
  },
  methods: {
    ...mapActions(["listarTodosLeiloes"]),
    excluir(id, nome) {
      const url = "http://localhost:8080/api/leilao"
      const tokenMain = Cookie.get('token')
      const token = {
        "Authorization": `Bearer ${tokenMain}`
      }
      axios.delete(`${url}/${id}`, { headers: token }).then((resp) => {
        console.log(resp, nome)
        if(resp.status == 200) {
            this.MsgExclusaoSucesso = true
            this.MsgExclusao = `Leilao => ${nome} excluido com sucesso`
          setTimeout(() => {
            this.MsgExclusaoSucesso = false
            this.$router.go();
          }, 3000)
        }
      }).catch((error) => {
        if(error.response.status == 403) {
          this.ErroExclusao = true
          this.ErroMsgExclusao = `Você não possui permissão para excluir o Leilao => ${nome}`
          setTimeout(() => {
            this.ErroExclusao = false
          }, 3000)
        }
      })
    }
  },
  computed: {
    ...mapState(["leiloes"]),
  },
  created() {
    this.listarTodosLeiloes();
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap");
body {
  height: 100%;
  background-color: #262a41;
  font-family: "Alfa Slab One", cursive;
}

a {
  text-decoration: none;
}

.divFor {
  display: inline-block;
}

.imgCabecalho {
  width: 100%;
  height: 100%;
}

.div-main {
  margin-top: 5%;
  display: inline-block;
  margin-left: 5%;
  padding-left: 9%;
  margin-bottom: 5%;
  text-decoration: none;
}

.dropdown-menu{
  border-top: 5px solid #0d0e17;
  background-color: #141624;
  width: 100%;
}

.dropdown-item {
  color: #666a93;
}

.dropdown-item:hover {
  background-color: #505673;
  transition: 0.7s;
}

.corpo-div {
  background-color: #191c2c;
  border-bottom: 8px solid #0d0e17;
}

.crud {
  background-color: #141624;
  padding-bottom: 3%;
}

.crud:hover img {
  opacity: 60%;
  transition: 0.3s;
}

.crud  img{
  margin-top: 3%;
  width: 10%;
  height: 10%;
  position: relative;
	left: 46%;
}

div.pai {
  padding-left: 5%;
}

div.esquerda {
  width: 38%;
  display: inline-block;
}

div.direita {
  width: 38%;
  display: inline-block;
  margin-left: 24%;
}

.titulo {
  margin-top: 3%;
  color: #4e526e;
  padding-left: 5%;
  font-size: 30px;
}

.data {
  color: #4d536f;
  padding-left: 5%;
  font-size: 12px;
}

.data-info {
  color: #666a93;
  padding-left: 5%;
  font-size: 10px;
}

.status {
  color: #4d536f;
  padding-left: 5%;
  font-size: 12px;
  padding-top: 30%;
}

.status-info {
  color: #00ff4e;
  padding-left: 5%;
  font-size: 10px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin-left: 10%;
  border: none;
}

.preco {
  color: #8978f2;
  font-size: 30px;
  padding-left: 5%;
}
</style>