<template>
<div v-if="SucessoVerificar == true">
  <Sucesso :msg="MsgSucesso"/>
</div>
<div v-if="AtencaoVerificar == true">
  <Atencao :msg="MsgAtencao"/>
</div>
  <div class="container">
      <div class="div1">
        <img src="@/assets/dp.png" alt="ImgLeilaoEspecifico" class="imagem" />
      </div>
      <div class="div2 inner_container">
        <div class="div_t">
          <h1 id="leilaoNome" class="mt-0">{{ leilao.nome }}</h1>
          <h6 id="lanceAtual">Lance atual</h6>
          <h2 id="leilaoValor">R$ {{ leilao.valor }}</h2>
          <h6 id="nomeAuthor">{{ lances.length > 0 ?  lances[lances.length-1].nomeAuthor : "..."}}</h6>
          <div class="testeclasse">
            <img
              src="@/assets/teste.png"
              alt="ImgLeilaoEspecifico"
              class="imagemHistorico"
            />
            <p id="leilaoHistorico" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Histórico de lances</p>
              <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content" style="background-color: #1e2339;">
                  <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel" style="color: #4e526e;">Lance -> {{leilao.nome}}</h5>
                  </div>
                  <div class="modal-body">
                    <div v-for="lance of lances" :key="lance.id">
                        <h5 style="color: #4e526e;">Nome: {{lance.nomeAuthor}}</h5>
                        <p style="color: #8978f2;">Lance: R$ {{lance.valor}} 💰</p>
                        <br>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="leilaoBotao">Close</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="div_o">
          <input id="leilaoInput" v-model="lance.valor" type="number" placeholder="Valor do lance" />
          <button id="leilaoBotao" v-on:click="darLance" class="mt-4" type="submit">Lance</button>
        </div>
      </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from 'axios'
import Cookie from "js-cookie";
import Atencao from "../Telas/Atenção/Atencao.vue"
import Sucesso from "../Telas/Sucesso/Sucesso.vue"
export default {
  name: "ArtigoEspecifico",
  data() {
    return {
      id: this.$route.params.id,
      lance: {
        valor: ""
      },
      MsgAtencao: "",
      AtencaoVerificar: Boolean,
      MsgSucesso: "",
      SucessoVerificar: Boolean,
    }
  },
  components: {
    Atencao,
    Sucesso
  },
  methods: {
    ...mapActions(["buscarLeilaoEspecifico", "buscarLanceEspecifico"]),
    darLance() {
      const lanceForm = {
        idLeilao: this.leilao.id,
        statusLeilao: this.leilao.status,
        nomeLeilao: this.leilao.nome,
        token:  Cookie.get('token'),
        valorLeilao: this.leilao.valor,
        valor: this.lance.valor
      }
      const urlLance = "http://localhost:8080/api/lance"

 axios.post(urlLance, lanceForm,{
            headers: {'Access-Control-Allow-Origin': "*"},
             mode: 'cors',
          }).then(response => {
            this.SucessoVerificar = true
            this.MsgSucesso = response.data
            setTimeout(() => {
              this.SucessoVerificar = false
              this.$router.go();
            }, 3000)
          }).catch((error) => {
            if(error.response.status == 406) {
              this.AtencaoVerificar = true
              this.MsgAtencao = error.response.data
              setTimeout(() => {
                this.AtencaoVerificar = false
              }, 3000)
            } else if(error.response.status == 500) {
              this.AtencaoVerificar = true
              this.MsgAtencao = "Você precisa se logar para efetuar um lance !"
              setTimeout(() => {
                this.AtencaoVerificar = false
              }, 3000)
            }
          })
    }
  },
  computed: {
    ...mapState(["leilao", "lances"]),
  },
  created() {
    this.buscarLeilaoEspecifico(this.id);
    this.buscarLanceEspecifico(this.id);
  },
};
</script>

<style scoped>
.container {
  margin-top: 3%;
  width: 70%;
  height: 460px;
  display: flex;
  padding: 0;
  border-bottom: 8px solid #0d0e17;
}

.div1 {
  width: 70%;
}

.imagem {
  width: 100%;
  height: 100%;
}

.imagemHistorico {
  width: 6%;
  border: transparent thin solid;
  margin-right: 10px;
}

.testeclasse {
  display: flex;
  justify-content: center;
  align-items: center;
}

.testeclasse p {
  align-items: center;
  margin: 0;
}

.div2 {
  width: 50%;
}

.inner_container {
  display: flex;
  flex-direction: column;
}

.div_t {
  background: #1e2339;
  height: 60%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

.div_o {
  background: #191c2c;
  height: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#leilaoNome {
  color: #4e526e;
}

#lanceAtual {
  padding-top: 2%;
  color: #4e526e;
  font-size: 15px;
}

#leilaoValor {
  color: #8978f2;
  font-size: 35px;
}

#nomeAuthor {
  color: white;
  background-color: #7568cf;
  height: 20px;
  width: 100px;
  display: flex;
  justify-content: center;
  align-content: center;
}

#leilaoHistorico {
  color: #4e526e;
}

#leilaoInput {
  background-color: #161624;
  outline: 0;
  border: none;
  color: #524992;
  height: 35px;
  width: 60%;
}

#leilaoBotao {
  background-color: #202336;
  border: none;
  height: 35px;
  width: 25%;
  border-bottom: 4px solid #8978f2;
  color: #8978f2;
}

#leilaoBotao:hover {
  opacity: 70%;
  transition: 0.2s;
}

input::placeholder {
  color: #524992;
}

input[type=number]::-webkit-inner-spin-button { 
    -webkit-appearance: none;
    
}
input[type=number] { 
   -moz-appearance: textfield;
   appearance: textfield;

}
</style>