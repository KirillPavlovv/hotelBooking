<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section><h2>Check available days</h2></section>
    <base-card>
      <div>
        <div class="form-control">
          <label for="checkIn">Check-in</label>
          <input type="date" id="checkIn" v-model="checkIn">
        </div>
        <div class="form-control">
          <label for="checkOut">Check-out</label>
          <input type="date" id="checkOut" v-model="checkOut">
        </div>
        <button v-on:click="getAvailableRoomsCount"> Check available rooms </button>
      </div>
    </base-card>
    <section>
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
          ></room-item>
        </ul>
      </base-card>
    </section>

  </div>
</template>

<script>
import RoomItem from "@/components/roomtypes/RoomItem";
import BaseCard from "@/components/base/BaseCard";

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
    }
  },

  methods: {
    getRoomTypes() {
      fetch('/room-types', {
        credentials: "include"
      })
          .then(response => response.json())
          .then(data => {
            this.roomTypes = data;
          })
          .catch(error => console.error(error))
    },

    getAvailableRoomsCount: async function() {
      fetch('/available-rooms?checkIn=' + this.checkIn + "&checkOut=" + this.checkOut, {
        credentials: "include"
      })
          .then(response => response.json())
          .then(data => {
            this.roomTypes = data;
          })
          .catch(error => console.error(error))
    },
  },

  mounted() {
    this.getRoomTypes()
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
button,
a {
  text-decoration: none;
  padding: 0.75rem 1.5rem;
  font: inherit;
  background-color: #5f327a;
  border: 1px solid #400a5d;
  color: white;
  cursor: pointer;
  border-radius: 30px;
  margin-right: 0.5rem;
  display: inline-block;
}

a:hover,
a:active,
button:hover,
button:active {
  background-color: #270041;
  border-color: #270041;
}
</style>