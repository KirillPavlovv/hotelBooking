<template>
  <div>
    <h2>Cancel your reservation</h2>
    <base-card>
      <div v-show="errorMessage" class="alert alert-danger" role="alert">
        {{ errorMessage }}
      </div>
      <section>
        <h3 style="font-style: italic; color: red">*It is possible to delete a reservation up to 3 days before the start of the reservation</h3>
        <form @submit.prevent="submitForm">
          <div class="form-control">
            <label for="personalCode">Personal Code</label>
            <input type="text" id="personalCode" v-model.trim="personalCode">
          </div>
          <button class="btn btn-primary w-50 text-white mt-3" v-on:click="submitForm">
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
      personalCode: null,
      errorMessage: false
    }
  },
  methods: {
    submitForm() {
      fetch('/cancel?personalCode=' +this.personalCode, {
        credentials: "include",
        method: 'DELETE',
        body: JSON.stringify(this.personalCode),
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(response => response.json())
          .then(data => {
            // this.errorMessage = data.title;
          })
          .catch(error => console.error(error));

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