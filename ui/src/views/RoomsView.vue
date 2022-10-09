<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section>
      <base-card>
      <div class="controls"></div>
      <ul>
          <room-item v-for="room in roomTypes"
                     :key="room.id"
                     :id="room.id"
                     :description="room.description"
                     :price="room.price"
                     :picture-name="room.pictureName"
          ></room-item>>
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