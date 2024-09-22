<script setup>
import Main from "./Base/Main.vue";
import NavBar from '../src/components/common/navbar.vue';
import SideBar from '../src/components/common/sidebar.vue';
</script>

<template>
  <div>
    <NavBar />
      <SideBar />
    <router-view />
    <!-- <Main></Main> -->
  </div>
</template>

<style scoped></style>
