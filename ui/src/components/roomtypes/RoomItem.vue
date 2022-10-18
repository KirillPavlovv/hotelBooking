<template>
  <li>
    <h3> {{ id }} - bed room </h3>
    <h4> €{{ price }}/night </h4>
    <div> {{ description }}</div>
    <div> Available rooms: {{ roomsCount }}</div>
    <div class="actions">
      <button class="btn btn-primary w-50 text-white mt-3" v-if="!(roomsCount === 0)" v-on:click="sendData"> Book a
        room
      </button>
    </div>
    <div class="reservation" v-if="showReservationForm">
      <reservation-component
          :check-in="checkIn"
          :check-out="checkOut"
          :id="id"
      ></reservation-component>
    </div>
  </li>
</template>

<script>
import ReservationComponent from "@/components/reservation/ReservationComponent";
import EventBus from "@/components/event-bus";

export default {
  name: "RoomItem",
  components: {ReservationComponent},
  props: ['id', 'description', 'pictureName', 'price', 'roomsCount', 'checkIn', 'checkOut'],
  data() {
    return {
      showReservationForm: false,
    }
  },
  methods: {
    async sendData() {
      this.showReservationForm = true;
      this.$emit('reservationData', this.checkIn, this.checkOut, this.id)
    },
  },
  mounted() {
    EventBus.$on('reservationFormClosed', () => {
      this.showReservationForm = false;
    })
  }
}
</script>

<style scoped>
li {
  margin: 1rem 0;
  border: 1px solid #424242;
  border-radius: 12px;
  padding: 1rem;
}

h3 {
  font-size: 1.5rem;
}

h3,
h4 {
  margin: 0.5rem 0;
}

div {
  margin: 0.5rem 0;
}

.actions {
  display: flex;
  justify-content: flex-end;
}
</style>