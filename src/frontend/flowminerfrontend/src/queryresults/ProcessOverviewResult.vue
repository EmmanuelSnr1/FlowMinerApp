<script setup>
import { ref, computed } from 'vue';

// Props received from the parent component
const props = defineProps({
  result: {
    type: Object,
    required: true,
  }
});

// Extracting the relevant data from the result object
const numEvents = props.result.numEvents;
const numCases = props.result.numCases;
const numActivities = props.result.numActivities;
const averageThroughputTime = props.result.averageThroughputTime;
const numVariants = props.result.numVariants;

// Extracting the top variants array and paginating it
const topVariants = ref(JSON.parse(props.result.topVariants));

// Pagination logic
const currentPage = ref(1);
const itemsPerPage = 5; // Customize this as needed

const totalPages = computed(() => {
  return Math.ceil(topVariants.value.length / itemsPerPage);
});

const paginatedVariants = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return topVariants.value.slice(start, end);
});

function nextPage() {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
}

function previousPage() {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
}
</script>

<template>
  <div class="p-4">
    <!-- Summary Section -->
    <div class="mb-6">
      <h2 class="text-2xl font-bold mb-4">Process Overview</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <div class="p-4 bg-gray-100 dark:bg-gray-800 rounded-lg">
          <p class="text-sm font-medium text-gray-500 dark:text-gray-300">Number of Events</p>
          <p class="text-lg font-bold">{{ numEvents }}</p>
        </div>
        <div class="p-4 bg-gray-100 dark:bg-gray-800 rounded-lg">
          <p class="text-sm font-medium text-gray-500 dark:text-gray-300">Number of Cases</p>
          <p class="text-lg font-bold">{{ numCases }}</p>
        </div>
        <div class="p-4 bg-gray-100 dark:bg-gray-800 rounded-lg">
          <p class="text-sm font-medium text-gray-500 dark:text-gray-300">Number of Activities</p>
          <p class="text-lg font-bold">{{ numActivities }}</p>
        </div>
        <div class="p-4 bg-gray-100 dark:bg-gray-800 rounded-lg">
          <p class="text-sm font-medium text-gray-500 dark:text-gray-300">Average Throughput Time</p>
          <p class="text-lg font-bold">{{ averageThroughputTime.toFixed(2) }} ms</p>
        </div>
        <div class="p-4 bg-gray-100 dark:bg-gray-800 rounded-lg">
          <p class="text-sm font-medium text-gray-500 dark:text-gray-300">Number of Variants</p>
          <p class="text-lg font-bold">{{ numVariants }}</p>
        </div>
      </div>
    </div>

    <!-- Top Variants Table with Pagination -->
    <div class="mt-8">
      <h2 class="text-xl font-bold mb-4">Top Variants</h2>
      <table class="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
        <thead class="bg-gray-50 dark:bg-gray-700">
          <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
              Variant Sequence
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
              Number of Cases
            </th>
          </tr>
        </thead>
        <tbody class="bg-white dark:bg-gray-900 divide-y divide-gray-200 dark:divide-gray-700">
          <tr v-for="(variant, index) in paginatedVariants" :key="index">
            <td class="px-6 py-4 text-sm text-gray-900 dark:text-gray-200">
              <!-- Display the variant sequence as a list -->
              <ul class="list-disc list-inside">
                <li v-for="(step, idx) in variant.variant" :key="idx">{{ step }}</li>
              </ul>
            </td>
            <td class="px-6 py-4 text-sm text-gray-900 dark:text-gray-200">
              {{ variant.num_cases }}
            </td>
          </tr>
        </tbody>
      </table>

      <!-- Pagination Controls -->
      <div class="flex justify-between items-center mt-4">
        <button 
          @click="previousPage"
          :disabled="currentPage === 1"
          class="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-600 dark:text-gray-300 font-semibold rounded-lg"
        >
          Previous
        </button>
        <span class="text-gray-600 dark:text-gray-300">Page {{ currentPage }} of {{ totalPages }}</span>
        <button 
          @click="nextPage"
          :disabled="currentPage === totalPages"
          class="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-600 dark:text-gray-300 font-semibold rounded-lg"
        >
          Next
        </button>
      </div>
    </div>
  </div>
</template>

<style>
/* Custom styles for the component (if any) */
</style>
