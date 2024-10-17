<script setup>
// Import the necessary components
import { onMounted } from 'vue';

import { useProcessResourceStore } from '../store';

const processResourceStore = useProcessResourceStore();

import { useNLPStore } from '../store-nlp';

const nlpStore = useNLPStore();

import ProcessFrequencyChart from '../components/charts/ProcessFrequencyChart.vue';
import ProcessFilesList from '../components/cards/ProcessFilesList.vue';
import RecentActivity from '../components/cards/RecentActivity.vue';
import QuickStats from '../components/cards/QuickStats.vue';
import ToolCardGrid from '../components/cards/ToolCardGrid.vue';

onMounted(() => {
    processResourceStore.fetchFiles();
    processResourceStore.fetchProcessFiles();
    processResourceStore.fetchDiscoveryResults(1);
    processResourceStore.fetchBottleneckResults(1);
    processResourceStore.fetchFrequencyResults(1);
    processResourceStore.fetchPerformanceResults(1);

    nlpStore.testNLPService();

});
</script>
<template>
    <main class="p-4 md:ml-64 h-auto pt-5">
        <!-- Header Section -->
        <section id="dashboard-header" class="mb-6">
            <h3 class="mb-4 text-2md font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl ">
                Dashboard
            </h3>
        </section>
        <!-- Process Frequency Chart Section -->
        <section id="process-frequency-chart" class="container mx-auto px-4 mb-8">
            <ProcessFrequencyChart />
        </section>

        <!-- Tool Cards Section -->
        <section id="tool-cards" class="container mx-auto px-4 mt-8">
            <ToolCardGrid />
        </section>

        <h1
            class="mb-4 p-6 text-4xl text-center font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
            Statistics
        </h1>
        <!-- Cards Layout Section -->
        <section id="dashboard-cards"
            class="container mx-auto px-4 grid grid-cols-1 md:grid-cols-3 lg:grid-cols-3 gap-4">

            <!-- Uploaded Files Card -->
            <div id="uploaded-files-card" class="flex flex-col">
                <!-- Import and use ProcessFilesList.vue here -->
                <div class="flex-grow">
                    <ProcessFilesList />
                </div>
            </div>

            <!-- Recent Activity Card -->
            <div id="recent-activity-card" class="flex flex-col">
                <!-- Import and use RecentActivity.vue here -->
                <div class="flex-grow">
                    <RecentActivity />
                </div>
            </div>

            <!-- Quick Stats Card -->
            <div id="quick-stats-card" class="flex flex-col">
                <!-- Import and use QuickStats.vue here -->
                <div class="flex-grow">
                    <QuickStats />
                </div>
            </div>
        </section>
    </main>
</template>



<style scoped>
/* Add any additional styling here */
</style>
