<template>
  <div class="container">
    <div class="card mt-3 mb-3">
      <div class="card-body">
        <div class="row">
          <h1> Reservations list </h1>
          <div>
            <table striped hover class="table">
              <thead>
              <tr>
                <th>{{ 'Room' }}</th>
                <th>{{ 'Customer' }}</th>
                <th>{{ 'Check-In' }}</th>
                <th>{{ 'Check-Out' }}</th>
              </tr>
              </thead>
              <tbody>
              <template v-for="reservation in reservationList">
              <tr >
                <td>
                  <a v-on:click="changeReservation(reservation.id)" class="nav-link" href="#">
                    {{ reservation.customerFirstName }} {{ reservation.customerLastName }}
                  </a>
                </td>
                <td>{{ reservation.roomNumber }}</td>
                <td>{{ formatDate(reservation.checkIn) }}</td>
                <td>{{ formatDate(reservation.checkOut) }}</td>
                <td>
                  <button class="btn btn-secondary w-100 text-white" type="button"
                          v-on:click="deleteReservation(reservation.id)">
                    <Icon name='trash' />
                  </button>
                </td>
              </tr>
                <tr v-show="show === reservation.id">
                  <change-reservation-component
                      :id="reservation.id"
                      :customer-first-name="reservation.customerFirstName"
                      :customer-last-name="reservation.customerLastName"
                      :room-number="reservation.roomNumber"
                      :room-type="reservation.roomType"
                      :check-in="reservation.checkIn"
                      :check-out="reservation.checkOut"
                  ></change-reservation-component>
                </tr>
              </template>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>


import Icon from "@/components/Icon";
import ChangeReservationComponent from "@/components/reservation/ChangeReservationComponent";

export default {
  name: "ReservationsListView",
  components: {
    Icon,
    ChangeReservationComponent
  },
  data() {
    return {
      reservationList: [],
      showReservationChangeForm:false,
      selectedReservationId: '',
    }
  },
  computed: {
    show() {
      return this.selectedReservationId;
    },
  },
  methods: {
    getAllReservations() {
      fetch('/reservationsList', {
        credentials: "include"
      })
          .then(response => response.json())
          .then(data => {
            this.reservationList = data;
          })
          .catch(error => console.error(error))
    },

    changeReservation(id) {
      if (!(this.selectedReservationId === '')) {
        this.selectedReservationId = '';
      } else {
        this.selectedReservationId = id;
      }
    },

    deleteReservation(id) {
      fetch('/deleteReservation?id=' + id, {
        credentials: 'include',
        method: 'DELETE'
      })
          .then(() => {
            this.getAllReservations()
          })
    },
  },

  mounted() {
    this.getAllReservations()
  }


}
</script>

<style scoped>
.nav-link {
  border-bottom: none;
  background-color: white;
  border: white;
}

</style>