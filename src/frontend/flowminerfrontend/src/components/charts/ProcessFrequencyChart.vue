<template>
    <div class="bg-white shadow rounded-lg p-6">
      <h2 class="text-xl font-bold mb-4 text-gray-900 dark:text-white">Process Frequencies in Most Recently Mined Processes</h2>
      <VueApexCharts type="line" height="350" :options="chartOptions" :series="series" />
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import VueApexCharts from 'vue3-apexcharts';
  
  // Register ApexCharts globally if you are using this in multiple components
  const series = ref([
    {
      name: "Process A",
      data: [10, 41, 35, 51, 49, 62, 69, 91, 148],
    },
    {
      name: "Process B",
      data: [23, 33, 54, 75, 81, 92, 107, 123, 135],
    },
    {
      name: "Process C",
      data: [15, 25, 45, 60, 70, 80, 90, 110, 125],
    },
  ]);
  
  const chartOptions = ref({
    chart: {
      id: "process-frequencies-chart",
      toolbar: {
        show: false,
      },
    },
    xaxis: {
      categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"],
      title: {
        text: 'Time (months)',
        style: {
          fontSize: '12px',
          color: '#666'
        }
      }
    },
    yaxis: {
      title: {
        text: 'Frequency',
        style: {
          fontSize: '12px',
          color: '#666'
        }
      }
    },
    stroke: {
      curve: 'smooth',
      width: 2,
    },
    colors: ['#4F46E5', '#EF4444', '#10B981'],
    dataLabels: {
      enabled: false,
    },
    legend: {
      position: 'top',
      horizontalAlign: 'right',
    },
    grid: {
      borderColor: '#e7e7e7',
      strokeDashArray: 4,
    },
  });
  </script>
  
  <style scoped>
  /* Additional styling can go here */
  </style>
  