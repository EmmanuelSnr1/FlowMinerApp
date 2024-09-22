<template>
  <aside class="fixed top-0 left-0 z-40 w-64 h-screen pt-14 transition-transform -translate-x-full bg-white border-r border-gray-200 md:translate-x-0 dark:bg-gray-800 dark:border-gray-700" aria-label="Sidenav">
    <div class="overflow-y-auto py-5 px-3 h-full bg-white dark:bg-gray-800">
      <form action="#" method="GET" class="md:hidden mb-2">
        <label for="sidebar-search" class="sr-only">Search</label>
        <div class="relative">
          <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
            <MagnifyingGlassIcon class="w-5 h-5 text-gray-500 dark:text-gray-400" />
          </div>
          <input type="text" id="sidebar-search" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full pl-10 p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500" placeholder="Search" />
        </div>
      </form>
      <ul class="space-y-2">
        <li>
          <router-link to="/home" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
            <ChartPieIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="ml-3">Overview</span>
          </router-link>
        </li>
        <li>
          <router-link to="/dashboard" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
            <ArrowTrendingUpIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="ml-3">Dashboard</span>
          </router-link>
        </li>
        <li>
          <router-link to="/analytics" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
            <ChartBarSquareIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="flex-1 ml-3 whitespace-nowrap">Analytics</span>
          </router-link>
        </li>
        <li>
          <router-link to="/process-files" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
            <InboxStackIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="flex-1 ml-3 whitespace-nowrap">Process Files</span>
          </router-link>
        </li>
      </ul>
      <ul class="pt-5 mt-5 space-y-2 border-t border-gray-200 dark:border-gray-700">
        <li>
          <router-link to="/docs" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg transition duration-75 hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group">
            <DocumentTextIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="ml-3">Docs</span>
          </router-link>
        </li>
        <li>
          <router-link to="/components" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg transition duration-75 hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group">
            <PuzzlePieceIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="ml-3">Components</span>
          </router-link>
        </li>
        <li>
          <router-link to="/help" class="flex items-center p-2 text-base font-medium text-gray-900 rounded-lg transition duration-75 hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group">
            <QuestionMarkCircleIcon class="w-6 h-6 text-gray-500 group-hover:text-gray-900 dark:group-hover:text-white" />
            <span class="ml-3">Help</span>
          </router-link>
        </li>
      </ul>
    </div>
  </aside>
</template>

<script setup>
import { MagnifyingGlassIcon, ArrowTrendingUpIcon, InboxStackIcon, ChartBarSquareIcon, ChartPieIcon, DocumentTextIcon, PuzzlePieceIcon, QuestionMarkCircleIcon } from '@heroicons/vue/24/outline';
import CollapseButton from '../common/buttons/collapse-button.vue';
</script>

<style scoped>
/* Add your custom styles here */
</style>
