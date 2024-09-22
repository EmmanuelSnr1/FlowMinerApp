<template>
    <div class="bg-white shadow rounded-lg p-4">
        <h2 class="text-xl font-bold mb-4 text-gray-900 dark:text-white">Uploaded Files</h2>
        <ul class="divide-y divide-gray-200">
            <li v-for="file in files" :key="file.id" class="py-2 flex justify-between items-center">
                <span class="text-gray-700 dark:text-gray-300">{{ file.name }}</span>
                <div>
                    <button class="text-blue-500 hover:underline mr-2">View</button>
                    <button class="text-green-500 hover:underline mr-2">Edit</button>
                    <button class="text-red-500 hover:underline">Delete</button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script setup>
// Dummy data for demonstration
const files = [
    { id: 1, name: 'Process File 1' },
    { id: 2, name: 'Process File 2' },
    { id: 3, name: 'Process File 3' },
    { id: 4, name: 'Process File 4' }
];
</script>

<style scoped>
/* Add any additional styling here if needed */
</style>
