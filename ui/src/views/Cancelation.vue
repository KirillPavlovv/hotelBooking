<template>
  <div>
    <h2>Cancel your reservation</h2>
    <base-card>
      <div v-show="errorMessage" class="alert alert-danger" role="alert">
        {{ errorMessage }}
      </div>
      <section>
        <form @submit.prevent="submitForm">
          <div class="form-control">
            <label for="personalCode">Personal Code</label>
            <input type="text" id="personalCode" v-model.trim="cancelRequest.personalCode">
            <label for="email">Email address</label>
            <input type="email" id="email" v-model.trim="cancelRequest.email">
          </div>
          <button class="btn btn-primary w-50 text-white mt-3">
            Cancel registration
          </button>
        </form>
      </section>
    </base-card>
  </div>
</template>

<script>
import BaseCard from "@/components/base/BaseCard";

export default {
  name: "Cancelation",
  components: {BaseCard},

  data() {
    return {
      cancelRequest: {
        personalCode: null,
        email: null
      },
      errorMessage: false
    }
  },
  methods: {
    submitForm() {
      console.log(this.cancelRequest)
    },
  }
}
</script>

<style scoped>
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