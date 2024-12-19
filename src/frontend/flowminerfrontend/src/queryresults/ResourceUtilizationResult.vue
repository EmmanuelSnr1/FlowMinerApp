<template>
    <div>
      <h2 class="text-xl font-bold">Resource Utilization</h2>
      <table class="min-w-full divide-y divide-gray-200">
        <thead>
          <tr>
            <th>User</th>
            <th>Utilization</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(utilization, user) in utilizationMap" :key="user">
            <td>{{ user }}</td>
            <td>{{ utilization }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      result: {
        type: Object,
        required: true
      }
    },
    computed: {
      utilizationMap() {
        return this.result.utilizationMap;
      }
    }
  };
  </script>
  