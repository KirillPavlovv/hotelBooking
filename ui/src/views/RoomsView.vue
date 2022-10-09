<template>
  <div>
    <section><h1>ROOMS</h1></section>
    <section>
      <div class="controls"></div>
      <button>Refresh</button>

      <ul>
        <li v-for="room in roomTypes" :key="room.id"></li>
        {{ room.description }}
      </ul>


    </section>
  </div>
</template>

<script>
export default {
  name: "Rooms",
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