<template>
  <nav
    class="bg-white border-b border-gray-200 px-4 py-2.5 dark:bg-gray-800 dark:border-gray-700 fixed left-0 right-0 top-0 z-50">
    <div class="flex flex-wrap justify-between items-center">
      <div class="flex justify-start items-center">
        <button
          class="p-2 mr-2 text-gray-600 rounded-lg cursor-pointer md:hidden hover:text-gray-900 hover:bg-gray-100 focus:bg-gray-100 dark:focus:bg-gray-700 focus:ring-2 focus:ring-gray-100 dark:focus:ring-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
          <Bars4Icon class="w-6 h-6" />
        </button>
        <a href="https://flowbite.com" class="flex items-center justify-between mr-4">
          <img src="../../assets/images/Snr Black.jpg" class="mr-3 h-8" alt="Flowbite Logo" />
          <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">FlowMiner (FM)</span>
        </a>
        <form action="#" method="GET" class="hidden md:block md:pl-2">
          <label for="topbar-search" class="sr-only">Search</label>
          <div class="relative md:w-96">
            <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
              <MagnifyingGlassIcon class="w-5 h-5 text-gray-500 dark:text-gray-400" />
            </div>
            <input type="text" id="topbar-search"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
              placeholder="Search" />
          </div>
        </form>
      </div>
      <div class="flex items-center lg:order-2">
        <button
          class="p-2 mr-1 text-gray-500 rounded-lg md:hidden hover:text-gray-900 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600">
          <MagnifyingGlassIcon class="w-6 h-6" />
        </button>
        <ThemeButton class="px-3" />
        <NotificationButton />
        <AppButton />
        <UserProfile />
      </div>
    </div>
  </nav>
</template>

<script setup>
import { Bars4Icon, MagnifyingGlassIcon } from "@heroicons/vue/24/outline";
import NotificationButton from "../common/buttons/notification-button.vue";
import AppButton from "../common/buttons/app-button.vue";
import UserProfile from "../common/user-profile.vue";
import ThemeButton from "./buttons/theme-button.vue";
</script>

<style scoped>
/* Add your custom styles here */
</style>
