<script setup>
import { ref, computed } from 'vue';

// Props received from the parent component
const props = defineProps({
    result: {
        type: Object,
        required: true,
    }
});

// Extracting the performance map
const performanceMap = ref(props.result.performanceMap);

// Pagination logic
const currentPage = ref(1);
const itemsPerPage = 10; // Customize this value to show more/less items per page

const totalPages = computed(() => {
    return Math.ceil(Object.keys(performanceMap.value).length / itemsPerPage);
});

const paginatedPerformanceMap = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const entries = Object.entries(performanceMap.value);
    return entries.slice(start, end);
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
        <!-- Case Duration Table with Pagination -->
        <div class="mt-8">
            <h2 class="text-xl font-bold mb-4">Case Duration Analysis</h2>
            <table class="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                <thead class="bg-gray-50 dark:bg-gray-700">
                    <tr>
                        <th scope="col"
                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                            Case ID
                        </th>
                        <th scope="col"
                            class="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                            Duration (ms)
                        </th>
                    </tr>
                </thead>
                <tbody class="bg-white dark:bg-gray-900 divide-y divide-gray-200 dark:divide-gray-700">
                    <tr v-for="(caseDuration, index) in paginatedPerformanceMap" :key="index">
                        <td class="px-6 py-4 text-sm text-gray-900 dark:text-gray-200">{{ caseDuration[0] }}</td>
                        <td class="px-6 py-4 text-sm text-gray-900 dark:text-gray-200">{{ caseDuration[1].toFixed(2) }}
                        </td>
                    </tr>
                </tbody>
            </table>

            <!-- Pagination Controls -->
            <div class="flex justify-between items-center mt-4">
                <button @click="previousPage" :disabled="currentPage === 1"
                    class="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-600 dark:text-gray-300 font-semibold rounded-lg">
                    Previous
                </button>
                <span class="text-gray-600 dark:text-gray-300">Page {{ currentPage }} of {{ totalPages }}</span>
                <button @click="nextPage" :disabled="currentPage === totalPages"
                    class="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-600 dark:text-gray-300 font-semibold rounded-lg">
                    Next
                </button>
            </div>
        </div>
    </div>
</template>

<style>
/* Custom styles for the component (if any) */
</style>
