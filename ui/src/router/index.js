import Vue from 'vue'
import VueRouter from 'vue-router'
import ReservationsListView from "@/views/ReservationsListView";
import NotFound from "@/views/NotFound";
import RoomsView from "@/views/RoomsView";
import Cancelation from "@/views/Cancelation";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/rooms'
    },
    {
        path: '/rooms',
        name: 'rooms',
        component: RoomsView
    },
    {
        path: '/all-reservations',
        name: 'reservations',
        component: ReservationsListView
    },
    {
        path: '/cancelation',
        name: 'reservations',
        component: Cancelation
    },
    {
        path: '/:not.Found(.*)',
        name: 'reservations',
        component: NotFound
    }
]

const router = new VueRouter({
    routes,
    // mode: 'history'
})

export default router
