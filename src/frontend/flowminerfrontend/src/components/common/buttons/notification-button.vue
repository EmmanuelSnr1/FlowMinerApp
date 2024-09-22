<template>
  <button type="button" class="p-2 mr-1 text-gray-500 rounded-lg hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600">
    <span class="sr-only">View notifications</span>
    <BellIcon class="w-6 h-6" />
  </button>
</template>

<script setup>
import { BellIcon } from '@heroicons/vue/24/outline';
</script>

<style scoped>
/* Add your custom styles here */
</style>
