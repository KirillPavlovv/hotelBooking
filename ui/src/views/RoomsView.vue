<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section>
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
    </section>
  </div>
</template>

<script>
import RoomItem from "@/components/roomtypes/RoomItem";

export default {
  name: "Rooms",
  components: {
    RoomItem
  },
  data() {
    return {
      // room: {},
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

</style>