<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section><h2>Check available days</h2></section>
    <div>
      <div class="form-control">
        <label for="checkIn">Check-in</label>
        <input type="date" id="checkIn" v-model="checkIn">
      </div>
      <div class="form-control">
        <label for="checkOut">Check-out</label>
        <input type="date" id="checkOut" v-model="checkOut">
      </div>
    </div>
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
      checkIn: null,
      checkOut: null,
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
</style>