<script setup>
import { ref, watch, computed } from 'vue';
import { useNLPStore } from '../store-nlp';
import resultsMap from '../queryresults/resultsMap';

const userInput = ref('');
const chatHistory = ref([]);
const nlpStore = useNLPStore();
const processFileId = 2; // Hardcoded processFileId for this example

function sendQuery() {
    if (userInput.value.trim() !== '') {
        // Add user query to chat history
        chatHistory.value.push({ role: 'user', text: userInput.value });

        // Indicate that the query is being processed
        chatHistory.value.push({ role: 'system', text: 'Processing your request...' });

        // Send the query to the NLP service
        nlpStore.handleNLPQuery(userInput.value, processFileId);

        // Reset input field
        userInput.value = '';
    }
}

// Watch for changes in queryStatus and update the chat history once a response is received
watch(() => nlpStore.queryStatus, (newStatus) => {
    if (newStatus && !nlpStore.error) {
        // Once a response is received, remove "Processing" message
        chatHistory.value.pop();

        // Add the response to the chat
        chatHistory.value.push({
            role: 'system',
            text: 'Query processed successfully. Please check the results section below.'
        });
    } else if (nlpStore.error) {
        // Handle errors
        chatHistory.value.pop(); // Remove the "Processing" message
        chatHistory.value.push({ role: 'system', text: `Error: ${nlpStore.error}` });
    }
});

// Dynamically resolve the component based on queryType from the result
const resolvedComponent = computed(() => {
    if (nlpStore.queryStatus && nlpStore.queryStatus.queryType) {
        const queryType = nlpStore.queryStatus.queryType;
        return resultsMap[queryType] || resultsMap['default']; // Default if queryType isn't found
    }
    return null;
});
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
                        <span class="bg-gray-300 dark:bg-gray-700 text-black dark:text-white p-2 rounded-lg">{{ message.text }}</span>
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

        <!-- Result display section -->
        <section id="result-section" class="mt-10">
            <div class="bg-white dark:bg-gray-900 p-6 rounded-lg shadow-md h-auto">
                <template v-if="resolvedComponent && nlpStore.queryStatus.result">
                    <!-- Dynamically render the component based on queryType -->
                    <component 
                      :is="resolvedComponent" 
                      :result="nlpStore.queryStatus.result"
                    ></component>
                </template>
                <template v-else>
                    <p class="text-gray-500 dark:text-gray-400">Results will be displayed here once available...</p>
                </template>
            </div>
        </section>
    </main>
</template>

<style>
/* Add any additional styles here */
</style>
