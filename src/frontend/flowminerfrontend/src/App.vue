<script setup>
import Main from "./Base/Main.vue";
import NavBar from '../src/components/common/navbar.vue';
import SideBar from '../src/components/common/sidebar.vue';
</script>

<template>
  <div>
    <NavBar />
      <SideBar />
    <Main></Main>
  </div>
  <!-- <router-view /> -->

</template>

<style scoped></style>
