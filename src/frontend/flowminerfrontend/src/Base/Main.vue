<script setup>
import NavBar from '../components/common/navbar.vue';
import SideBar from '../components/common/sidebar.vue';
import ContentBox from '../components/common/content-box.vue';
</script>
<template>
    <div class="antialiased bg-gray-200 dark:bg-gray-900">
      <NavBar />
      <SideBar />
      <main class="p-4 md:ml-64 h-auto pt-20">
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-4">
          <ContentBox v-for="i in 4" :key="i" />
        </div>
        <div class="border-2 border-dashed rounded-lg border-gray-300 dark:border-gray-600 h-96 mb-4"></div>
        <div class="grid grid-cols-2 gap-4 mb-4">
          <ContentBox v-for="i in 4" :key="i" />
        </div>
        <div class="border-2 border-dashed rounded-lg border-gray-300 dark:border-gray-600 h-96 mb-4"></div>
        <div class="grid grid-cols-2 gap-4">
          <ContentBox v-for="i in 4" :key="i" />
        </div>
      </main>
    </div>
  </template>
  
 
  
  <style scoped>
  /* Add your custom styles here */
  </style>
  