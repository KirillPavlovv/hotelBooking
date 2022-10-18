<template>
  <div>
    <section>
      <base-card>
        <h2>Change reservation</h2>
        <section>
          <form @submit.prevent="submitForm">
            <div class="form-control">
              <label for="firstName">Customer name: {{ reservation.customerFirstName }}
                {{ reservation.customerLastName }}</label>
              <label for="checkIn">Check-in: {{ formatDate(reservation.checkIn) }} </label>
              <input type="date" id="checkIn" v-model="reservation.checkIn" class="mb-2">
              <label for="checkOut">Check-out: {{ formatDate(reservation.checkOut) }}</label>
              <input type="date" id="checkOut" v-model="reservation.checkOut" class="mb-2">
              <div class="input-group-sm mb-3">
                <div class="input-group-prepend">
                  <label for="roomTypeId">Room type</label>
                </div>
                <select class="custom-select" id="roomTypeId" v-model="reservation.roomType">
                  <option selected>Choose...</option>
                  <option value="1">One-bed room</option>
                  <option value="2">Two-bed room</option>
                  <option value="3">Three/bed room</option>
                </select>
              </div>
              <div v-show="errorMessage" class="alert alert-danger" role="alert">
                {{ errorMessage }}
              </div>
              <button class="btn btn-primary w-25 text-white mt-3">Reserve</button>
            </div>
          </form>
        </section>
      </base-card>
    </section>
  </div>
</template>

<script>
import BaseCard from "@/components/base/BaseCard";

export default {
  name: "ReservationView",
  components: {BaseCard},
  props: ['id', 'checkIn', 'checkOut', 'customerFirstName', 'customerLastName', 'roomNumber', 'roomType'],
  data() {
    return {
      reservation: {
        id: this.id,
        checkIn: this.checkIn,
        checkOut: this.checkOut,
        customerFirstName: this.customerFirstName,
        customerLastName: this.customerLastName,
        roomNumber: this.roomNumber,
        roomType: this.roomType
      },
      errorMessage: false,
    }
  },

  methods: {
    submitForm() {
      if (this.roomType === this.reservation.roomType) {
        this.sendChangeRequest();
      }
    },
    sendChangeRequest() {
      this.errorMessage = false;
      fetch('/updateReservation', {
        credentials: 'include',
        method: 'PUT',
        body: JSON.stringify(this.reservation),
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(response => response.json())
          .then(data => {
            this.errorMessage = data.title;
          })
    },
  },
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