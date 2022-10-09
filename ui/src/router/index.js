import Vue from 'vue'
import VueRouter from 'vue-router'
import Rooms from "@/views/RoomsView";
import ReservationView from "@/views/ReservationView";
import ReservationsListView from "@/views/ReservationsListView";
import NotFound from "@/views/NotFound";
import RoomDetailsView from "@/views/RoomDetailsView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/rooms'
  },
  {
    path: '/rooms',
    name: 'rooms',
    component: Rooms,
    children: [
      {
        path: '/room',
        name: 'room',
        component: RoomDetailsView,
      },
    ]
  },
  {
    path: '/reservation',
    name: 'reservation',
    component: ReservationView
  },
  {
    path: '/all-reservations',
    name: 'reservations',
    component: ReservationsListView
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
