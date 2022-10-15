<template>

  <div class="container">
    <h1> RESERVATIONS LIST </h1>
    <div>
      <table class="table">
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
          <tr :key="reservation.roomNumber">
            <td>{{ reservation.customerFirstName }} {{ reservation.customerLastName }}</td>
            <td>{{ reservation.roomNumber }}</td>
            <td>{{ reservation.checkIn }}</td>
            <td>{{ reservation.checkOut }}</td>
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
      </table>
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