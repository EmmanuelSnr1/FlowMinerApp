<template>
    <div class="bg-white shadow rounded-lg p-4">
        <h2 class="text-xl font-bold mb-4 text-gray-900 dark:text-white">Quick Stats</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            <div class="bg-blue-100 text-blue-800 p-4 rounded-lg">
                <p class="text-lg font-semibold">Total Files</p>
                <p class="text-2xl">{{ stats.totalFiles }}</p>
            </div>
            <div class="bg-green-100 text-green-800 p-4 rounded-lg">
                <p class="text-lg font-semibold">Total Events Processed</p>
                <p class="text-2xl">{{ stats.totalEvents }}</p>
            </div>
            <div class="bg-yellow-100 text-yellow-800 p-4 rounded-lg">
                <p class="text-lg font-semibold">Active Users</p>
                <p class="text-2xl">{{ stats.activeUsers }}</p>
            </div>
        </div>
    </div>
</template>

<script setup>
// Dummy data for demonstration
const stats = {
    totalFiles: 25,
    totalEvents: 12345,
    activeUsers: 5
};
</script>

<style scoped>
/* Add any additional styling here if needed */
</style>
