<template>
    <div class="bg-white shadow rounded-lg p-4">
        <h2 class="text-xl font-bold mb-4 text-gray-900 dark:text-white">Recent Activity</h2>
        <ul class="divide-y divide-gray-200">
            <li v-for="activity in activities" :key="activity.id" class="py-2">
                <p class="text-gray-700 dark:text-gray-300">{{ activity.description }}</p>
                <span class="text-sm text-gray-500">{{ activity.timestamp }}</span>
            </li>
        </ul>
    </div>
</template>

<script setup>
// Dummy data for demonstration
const activities = [
    { id: 1, description: 'Uploaded Process File 1', timestamp: '2024-08-25 14:32' },
    { id: 2, description: 'Edited Process File 2', timestamp: '2024-08-25 13:22' },
    { id: 3, description: 'Deleted Process File 3', timestamp: '2024-08-25 12:15' },
    { id: 4, description: 'Viewed Process File 4', timestamp: '2024-08-25 11:00' }
];
</script>

<style scoped>
/* Add any additional styling here if needed */
</style>
