import {createRouter, createWebHashHistory} from 'vue-router'
import Home from '../views/TodosArtigos.vue'
import Inicio from '../views/Inicio.vue'

const routes = [
    {
        path: '/todosartigos',
        name: 'TodoArtigos',
        component: Home
    },
    {
        path: '/',
        name: 'Inicio',
        component: Inicio
    },
    {
        path: '/leilaoespecifico/:id',
        name: 'LeilaoEspecifico',
        component: () => import(/* webpackChunkName: "about" */ '../views/LeilaoEspecifico.vue')
    },
    {
        path: '/cadastrarLeilao',
        name: 'CadastrarLeilao',
        component: () => import(/* webpackChunkName: "about" */ '../views/CadastrarLeilao.vue')
    },
    {
        path: '/editarLeilao/:id',
        name: 'EditarLeilao',
        component: () => import(/* webpackChunkName: "about" */ '../views/EditarLeilao.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    },
    {
        path: '/registrar',
        name: 'Registrar',
        component: () => import(/* webpackChunkName: "about" */ '../views/Registrar.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
