<template>
  <div>
    <section><h2>Check available rooms</h2></section>
    <base-card>
      <div class="form-control">
        <label for="checkIn">Check-in</label>
        <input type="date" id="checkIn" v-model="checkIn" class="mb-2">
        <label for="checkOut">Check-out</label>
        <input type="date" id="checkOut" v-model="checkOut">
        <button class="btn btn-primary w-100 text-white mt-3" v-on:click="getAvailableRoomsCount"> Check available
          rooms
        </button>
      </div>
      <div v-show="errorMessage" class="alert alert-danger" role="alert">
        {{ errorMessage }}
      </div>
    </base-card>
    <section v-show="roomTypes.length>0">
      <base-card>
        <div class="controls"></div>
        <ul>
          <room-item v-for="room in roomTypes"
                     :key="room.id"
                     :id="room.id"
                     :description="room.description"
                     :picture-name="room.pictureName"
                     :price="room.price"
                     :rooms-count="room.roomsCount"
                     :check-in="checkIn"
                     :check-out="checkOut"
          ></room-item>
        </ul>
      </base-card>
    </section>

  </div>
</template>

<script>
import RoomItem from "@/components/roomtypes/RoomItem";
import BaseCard from "@/components/base/BaseCard";
import EventBus from "@/components/event-bus";

export default {
  name: "Rooms",
  components: {
    BaseCard,
    RoomItem,
  },
  data() {
    return {
      roomTypes: [],
      checkIn: '',
      checkOut: '',
      errorMessage: null,
    }
  },

  methods: {
    getAvailableRoomsCount: async function () {
      fetch('/available-rooms?checkIn=' + this.checkIn + "&checkOut=" + this.checkOut, {
        credentials: "include",
        method: 'GET',
      })
          .then(response => response.json())
          .then(data => {
            this.roomTypes = data;
            this.errorMessage = data.title;
          })
          .catch(error => console.error(error));
    },
  },
  mounted() {
    EventBus.$on('reservationFormClosed', ()=>{
      this.getAvailableRoomsCount();
    })
  }
}

</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.controls {
  display: flex;
  justify-content: space-between;
}

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