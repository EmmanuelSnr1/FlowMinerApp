<template>
    <div>
        <button type="button" @click="toggle"
            class="flex items-center p-2 w-full text-base font-medium text-gray-900 rounded-lg transition duration-75 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
            aria-controls="dropdown" :aria-expanded="isOpen">
            <slot name="icon" />
            <span class="flex-1 ml-3 text-left whitespace-nowrap">{{ title }}</span>
            <ChevronDownIcon class="w-6 h-6" :class="{ 'rotate-180': isOpen }" />
        </button>
        <ul :id="id" class="hidden py-2 space-y-2" :class="{ block: isOpen }">
            <slot />
        </ul>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { ChevronDownIcon } from "@heroicons/vue/24/outline";

const props = defineProps({
    title: String,
    id: String,
});

const isOpen = ref(false);

const toggle = () => {
    isOpen.value = !isOpen.value;
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
