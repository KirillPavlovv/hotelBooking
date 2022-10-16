<template>
  <div class="container">
    <div class="card mt-3 mb-3">
      <div class="card-body">
        <div class="row">
          <h1> Reservations list </h1>
          <div>
            <b-table striped hover :items="items" class="table">
              <thead>
              <tr>
                <th>{{ 'Customer' }}</th>
                <th>{{ 'Room' }}</th>
                <th>{{ 'Check-In' }}</th>
                <th>{{ 'Check-Out' }}</th>
              </tr>
              </thead>
              <tbody>
              <template v-for="reservation in reservationList">
                <tr :key="reservation.id">
                  <td>{{ reservation.customerFirstName }} {{ reservation.customerLastName }}</td>
                  <td>{{ reservation.roomNumber }}</td>
                  <td>{{ formatDate(reservation.checkIn) }}</td>
                  <td>{{ formatDate(reservation.checkOut) }}</td>

                  <td>

                    <button class="btn btn-secondary w-100 text-white" type="button">
                      <Icon name='pencil'/>
                    </button>
                  </td>
                  <td>
                    <button class="btn btn-secondary w-100 text-white" type="button" v-on:click="deleteReservation(reservation.id)">
                      <Icon name='trash'/>
                    </button>
                  </td>
                </tr>
              </template>
              </tbody>
            </b-table>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>

import Icon from "@/components/Icon";

export default {
  name: "ReservationsListView",
  components: {
    Icon
  },
  data() {
    return {
      reservationList: [],
    }
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
    deleteReservation(id) {
      fetch('/deleteReservation?id=' + id,{
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

</style>