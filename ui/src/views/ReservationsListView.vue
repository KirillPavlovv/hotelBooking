<template>

  <div class="container">
    <div class="card mt-3 mb-3">
      <div class="card-body">
        <div class="row">
          <h1> RESERVATIONS LIST </h1>
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
                </tr>
                <!--              <tr v-show="show === campaign.id" >-->
                <!--                <td colspan="10" style="text-align: left">-->
                <!--                  <p>Age: {{ filterSet.age }} </p>-->
                <!--                  <p>Sex: {{ filterSet.sex }} </p>-->
                <!--                  <p>Address: {{ filterSet.address }} </p>-->
                <!--                  <p>Inner credit score: {{ filterSet.tangocustomerratingcolor }} </p>-->
                <!--                  <p>Outer credit score: {{ filterSet.externalinfojsonforcreditratingcolor }} </p>-->
                <!--                  <p>Has active contract: {{ filterSet.activecontracts }} </p>-->
                <!--                  <p>Marketing consent: {{ filterSet.marketingConsent }} </p>-->
                <!--                  <p>Product name: {{ filterSet.productname }} </p>-->
                <!--                  <p>Months until contract end: {{ filterSet.contractendmonths }} </p>-->
                <!--                  <p>Real estate owner: {{ filterSet.realestateowner }} </p>-->
                <!--                  <p>Customer payment ability: {{ filterSet.customerpaymentability }} </p>-->
                <!--                  <p>Number of dependents: {{ filterSet.numberofdependent }} </p>-->

                <!--                  <button class="btn-primary btn-sm btn ml-1" >Use same filters</button>-->
                <!--                </td>-->
                <!--              </tr>-->
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
export default {
  name: "ReservationsListView",
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
  },
  mounted() {
    this.getAllReservations()
  }

}
</script>

<style scoped>

</style>