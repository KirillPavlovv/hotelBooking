<template>
  <svg xmlns="http://www.w3.org/2000/svg" :width="width" :height="height" v-if="currentIcon"
       :viewBox="currentIcon.box" :fill="currentIcon.fill" :stroke="currentIcon.stroke">
    <path fill-rule="evenodd" :d=currentIcon.svg clip-rule="evenodd"></path>
  </svg>
</template>

<script>
export default {
  name: 'Icon',
  props: {
    width: {
      type: [Number, String],
      default: 20
    },
    height: {
      type: [Number, String],
      default: 20
    },
    name: {
      type: String
    },
  },

  data() {
    return {
      icons: [
        {
          box: "0 0 20 20",
          name: "trash",
          fill: "currentColor",
          stroke: "none",
          svg: "M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
        },
        {
          box: "0 0 20 20",
          name: "pencil",
          fill: "currentColor",
          stroke: "none",
          svg: "M2.695 14.763l-1.262 3.154a.5.5 0 00.65.65l3.155-1.262a4 4 0 001.343-.885L17.5 5.5a2.121 2.121 0 00-3-3L3.58 13.42a4 4 0 00-.885 1.343z"
        },
      ],
    }
  },
  computed: {
    currentIcon() {
      return this.icons.find(e => e.name === this.name)
    }
  }
}
</script>

<style scoped>

</style>