<template>
  <li>
<!--    <picture>{{ picture }}</picture>-->
    <h3> {{ id }} - bed room </h3>
    <h4> €{{ price }}/night </h4>
    <div> {{ description }}</div>
    <div> Available rooms: {{ roomsCount }}</div>
    <div class="actions">
      <base-button link to="/reservation"> Book a room </base-button>
    </div>
  </li>
</template>

<script>
import BaseButton from "@/components/base/BaseButton";
export default {
  name: "RoomItem",
  components: {BaseButton},
  props: ['id', 'description', 'pictureName', 'price', 'roomsCount', 'checkIn', 'checkOut'],
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