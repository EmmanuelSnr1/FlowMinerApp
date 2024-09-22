<template>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      <div 
        v-for="(tool, index) in tools" 
        :key="index" 
        class="flex items-center justify-center p-6 bg-white border rounded-lg shadow hover:bg-gray-100 transition-all"
        :style="{ backgroundColor: tool.bgColor }"
      >
        <div class="flex flex-col items-center">
          <div class="p-4 rounded-full mb-2" :style="{ backgroundColor: tool.iconBgColor }">
            <component :is="tool.icon" class="w-10 h-10 text-white" />
          </div>
          <h3 class="text-xl font-semibold text-gray-900 dark:text-white">{{ tool.name }}</h3>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { 
    MagnifyingGlassIcon, 
    ArrowTrendingUpIcon, 
    InboxStackIcon, 
    ChartBarSquareIcon, 
    ChartPieIcon, 
    DocumentTextIcon, 
    PuzzlePieceIcon, 
    QuestionMarkCircleIcon 
  } from '@heroicons/vue/24/outline';
  
  // Define the tools array with Heroicons
  const tools = [
    {
      name: 'Search Process Models',
      icon: MagnifyingGlassIcon,
      bgColor: '#F0F4FF',
      iconBgColor: '#4F46E5',
    },
    {
      name: 'Trend Analysis',
      icon: ArrowTrendingUpIcon,
      bgColor: '#FEE2E2',
      iconBgColor: '#EF4444',
    },
    {
      name: 'Inbox Processing',
      icon: InboxStackIcon,
      bgColor: '#ECFDF5',
      iconBgColor: '#10B981',
    },
    {
      name: 'Bar Chart Visualization',
      icon: ChartBarSquareIcon,
      bgColor: '#FFFBEB',
      iconBgColor: '#F59E0B',
    },
    {
      name: 'Pie Chart Visualization',
      icon: ChartPieIcon,
      bgColor: '#EBF5FF',
      iconBgColor: '#1D4ED8',
    },
    {
      name: 'Document Analysis',
      icon: DocumentTextIcon,
      bgColor: '#FFF7ED',
      iconBgColor: '#D97706',
    },
    {
      name: 'Process Puzzle',
      icon: PuzzlePieceIcon,
      bgColor: '#EFF6FF',
      iconBgColor: '#3B82F6',
    },
    {
      name: 'Help & Support',
      icon: QuestionMarkCircleIcon,
      bgColor: '#F3F4F6',
      iconBgColor: '#6B7280',
    },
  ];
  </script>
  
  <style scoped>
  /* Additional styles for the component if needed */
  </style>
  