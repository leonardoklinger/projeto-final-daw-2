import {createStore} from 'vuex'
import axios from 'axios'

const url = "http://localhost:8080/api/leilao"
const urlLance = "http://localhost:8080/api/lance"
const urlUser = "http://localhost:8080/api/usuario"

export default createStore({
    state: {
        leiloes: [],
        leilao: [],
        lances: [],
        lance: []
    },
    mutations: {
        listarTodosLeiloes(state, payload) {
            state.leiloes = payload
        },
        buscarLeilaoEspecifico(state, payload) {
            state.leilao = payload
        },
        buscarLanceEspecifico(state, payload) {
            state.lances = payload
        },
        registrarUser(state, payload) {
            state.lance = payload
        },
    },
    actions: {
        listarTodosLeiloes(context) {
            axios.get(`${url}`).then(resp => {
                context.commit('listarTodosLeiloes', resp.data)
            })
        },
        buscarLeilaoEspecifico(context, id) {
            axios.get(`${url}/${id}`).then((resp) => {
                context.commit('buscarLeilaoEspecifico', resp.data)
            })
        },
        buscarLanceEspecifico(context, id) {
            axios.get(`${urlLance}/${id}`).then((resp) => {
                context.commit('buscarLanceEspecifico', resp.data)
            })
        },
        registrarUser(context, payload) {
            axios.post(`${urlUser}`, payload).then((resp) => {
                context.commit('registrarUser', resp.data)
            })
        },
    },
})
