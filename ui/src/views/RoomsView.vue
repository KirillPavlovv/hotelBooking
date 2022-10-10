<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section><h2>Check available days</h2></section>
    <div class="row gx-3 mb-3">
      <div class="col-6 py-2">
        <label class="form-check-label" for="absenceStart">Starting from</label>
        <input type="date" id="absenceStart" name="absenceStart" v-model="startReservation"
               class="form-control">
      </div>
      <div class="col-6 py-2">
        <label class="form-check-label" for="absenceEnd">Until</label>
        <input type="date" id="absenceEnd" name="absenceEnd" v-model="endReservation"
               class="form-control">
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
    RoomItem
  },
  data() {
    return {
      roomTypes: [],
      startReservation: null,
      endReservation: null,
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