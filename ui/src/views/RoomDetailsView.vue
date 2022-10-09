<template>
  <div>
  <h1> ROOM DETAILS </h1>
  <router-view></router-view>
  <router-link to="/rooms/r1/detail">Room Description</router-link>
  </div>
</template>

<script>
export default {
  name: "RoomView"
}
</script>

<style scoped>

</style>