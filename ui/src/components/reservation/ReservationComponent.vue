<template>
  <div>
    <section>
      <base-card>
        <h2>Fill up the reservation form</h2>
        <section>
          <form @submit.prevent="submitForm">
            <div>
              <label for="checkIn">Check-in: {{ reservation.checkIn }} </label>
            </div>
            <div>
              <label for="checkOut">Check-out: {{ reservation.checkOut }}</label>
            </div>
            <div class="form-control">
              <label for="firstName">First name</label>
              <input type="text" id="firstName" v-model.trim="reservation.firstName">
            </div>
            <div class="form-control">
              <label for="lastName">Last name</label>
              <input type="text" id="lastName" v-model.trim="reservation.lastName">
            </div>
            <div class="form-control">
              <label for="personalCode">Personal Code</label>
              <input type="text" id="personalCode" v-model.trim="reservation.personalCode">
            </div>
            <div class="form-control">
              <label for="email">Email address</label>
              <input type="email" id="email" v-model.trim="reservation.email">
            </div>
            <button v-on:click="closeReservationForm">Close</button>
            <base-button>Reserve</base-button>
          </form>
        </section>
      </base-card>
    </section>
  </div>
</template>

<script>
import BaseButton from "@/components/base/BaseButton";
import BaseCard from "@/components/base/BaseCard";
import EventBus from "@/components/event-bus";

export default {
  name: "ReservationView",
  components: {BaseCard, BaseButton},
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
      console.log(this.reservation)
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