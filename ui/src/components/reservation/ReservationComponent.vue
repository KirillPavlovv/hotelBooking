<template>
  <div>
    <section>
      <base-card>
        <h2>Fill up the reservation form</h2>
        <section>
          <form @submit.prevent="submitForm">
            <div>
              <label for="checkIn">Check-in: {{ formatDate(reservation.checkIn) }} </label>
              <label for="checkOut">Check-out: {{ formatDate(reservation.checkOut) }}</label>
            </div>
            <div class="form-control">
              <label for="firstName">First name</label>
              <input type="text" id="firstName" v-model.trim="reservation.firstName">
              <label for="lastName">Last name</label>
              <input type="text" id="lastName" v-model.trim="reservation.lastName">
              <label for="personalCode">Personal Code</label>
              <input type="text" id="personalCode" v-model.trim="reservation.personalCode">
              <label for="email">Email address</label>
              <input type="email" id="email" v-model.trim="reservation.email">
            </div>
            <button class="btn btn-primary w-25 text-white mt-3 mr-1" v-on:click="closeReservationForm">Close</button>
            <button class="btn btn-primary w-25 text-white mt-3">Reserve</button>
          </form>
        </section>
      </base-card>
    </section>
  </div>
</template>

<script>
import BaseCard from "@/components/base/BaseCard";
import EventBus from "@/components/event-bus";

export default {
  name: "ReservationView",
  components: {BaseCard},
  props: ['checkIn', 'checkOut', 'id'],
  data() {
    return {
      reservation: {
        checkIn: this.checkIn,
        checkOut: this.checkOut,
        firstName: '',
        lastName: '',
        personalCode: '',
        email: '',
        roomType: this.id,
      },
    }

  },
  methods: {
    submitForm() {
      fetch('/reservation', {
        credentials: 'include',
        method: 'POST',
        body: JSON.stringify(this.reservation),
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(response => console.log(response))
    },

    closeReservationForm() {
      EventBus.$emit('reservationFormClosed')
    },
  },
  mounted() {
    EventBus.$on('reservationData', data => {
      this.reservation.roomType = data.id;
    })
  }

}
</script>

<style scoped>
.form-control {
  margin: 0.5rem 0;
}

label {
  text-align: left;
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

input {
  display: block;
  width: 100%;
  border: 1px solid #ccc;
  font: inherit;
}

input:focus {
  background-color: #f0e6fd;
  outline: none;
  border-color: #3d008d;
}

h3 {
  margin: 0.5rem 0;
  font-size: 1rem;
}

.invalid label {
  color: red;
}

.invalid input {
  border: 1px solid red;
}
</style>