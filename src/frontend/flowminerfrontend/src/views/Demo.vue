<script setup>
// Reactive state to manage chat input and messages
import { ref } from 'vue';

const userInput = ref(''); // To hold user input for the query
const chatHistory = ref([]); // To hold the chat history (user queries and system responses)

// Dummy function to simulate query processing and response
function sendQuery() {
    if (userInput.value.trim() !== '') {
        // Add user query to chat history
        chatHistory.value.push({ role: 'user', text: userInput.value });

        // Simulate a response and add to chat history (can later replace this with real API call)
        chatHistory.value.push({ role: 'system', text: 'Processing your request...' });

        // Reset input field
        userInput.value = '';
    }
}

// Dummy function to clear chat (optional)
function clearChat() {
    chatHistory.value = [];
}
</script>

<template>
    <main class="p-4 md:ml-64 h-auto pt-10">
        <!-- Welcome message section -->
        <div>
            <h1 class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl">
                Hi All Welcome to the Demo
            </h1>
        </div>

        <!-- Chat interface section -->
        <section id="chat-section" class="my-8">
            <!-- Chat box -->
            <div class="bg-gray-100 dark:bg-gray-800 rounded-lg p-4 h-96 overflow-auto">
                <!-- Loop through chat history and display messages -->
                <div v-for="(message, index) in chatHistory" :key="index" class="my-2">
                    <div v-if="message.role === 'user'" class="text-right">
                        <span class="bg-blue-500 text-white p-2 rounded-lg">{{ message.text }}</span>
                    </div>
                    <div v-else class="text-left">
                        <span class="bg-gray-300 dark:bg-gray-700 text-black dark:text-white p-2 rounded-lg">{{
                            message.text }}</span>
                    </div>
                </div>
            </div>

            <!-- Input for user to type their query -->
            <div class="flex mt-4">
                <input v-model="userInput" @keyup.enter="sendQuery" type="text" placeholder="Type your query..."
                    class="w-full p-2 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />
                <button @click="sendQuery" class="bg-blue-500 text-white p-2 rounded-r-lg hover:bg-blue-600">
                    Send
                </button>
                <!-- Optional Clear Button -->
                <button @click="clearChat" class="bg-red-500 text-white p-2 rounded-lg ml-2 hover:bg-red-600">
                    Clear Chat
                </button>
            </div>
        </section>

        <!-- Result display section (This will later be populated with data like tables, BPMNs, etc.) -->
        <section id="result-section" class="mt-10">
            <div class="bg-white dark:bg-gray-900 p-6 rounded-lg shadow-md h-64">
                <!-- Placeholder for results -->
                <p class="text-gray-500 dark:text-gray-400">Results will be displayed here...</p>
            </div>
        </section>
    </main>
</template>

<style>
/* You can add any additional styles here */
</style>
