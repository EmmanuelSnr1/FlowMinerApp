<script setup>
import { ref, onMounted, watch } from 'vue';
import { dia, shapes } from 'jointjs';
import { useProcessResourceStore } from '../store';

const processResourceStore = useProcessResourceStore();
const graph = ref(null);
const paper = ref(null);
const petriNetData = ref(null);
const activeTab = ref(0); // To track the currently active tab
const elementsMap = new Map(); // To map place/transition IDs to their JointJS elements

const renderPetriNet = (data) => {
  if (!graph.value || !data) return;

  // Clear the graph before adding new elements
  graph.value.clear();
  elementsMap.clear(); // Clear the mapping

  // Add places to the graph
  data.places.forEach((placeId) => {
    const place = new shapes.standard.Circle({
      position: { x: Math.random() * 600, y: Math.random() * 400 },
      size: { width: 50, height: 50 },
      attrs: {
        body: { fill: 'lightblue' },
        label: { text: placeId.slice(0, 5), fill: 'black', fontSize: 10 }, // Minimal label
      },
      id: placeId, // Set the ID
    });
    place.addTo(graph.value);
    elementsMap.set(placeId, place); // Add to the map
  });

  // Add transitions to the graph
  data.transitions.forEach((transition) => {
    const rect = new shapes.standard.Rectangle({
      position: { x: Math.random() * 600, y: Math.random() * 400 },
      size: { width: 70, height: 30 },
      attrs: {
        body: { fill: 'lightgreen' },
        label: { text: transition.slice(0, 10), fill: 'black', fontSize: 10 }, // Minimal label
      },
      id: transition, // Set the ID
    });
    rect.addTo(graph.value);
    elementsMap.set(transition, rect); // Add to the map
  });

  // Add arcs (links) to the graph
  data.arcs.forEach((arc) => {
    const sourceElement = elementsMap.get(arc.source);
    const targetElement = elementsMap.get(arc.target);

    if (sourceElement && targetElement) {
      const link = new dia.Link({
        source: { id: sourceElement.id },
        target: { id: targetElement.id },
        attrs: {
          line: {
            stroke: 'black',
            strokeWidth: 2,
          },
        },
      });
      link.addTo(graph.value);
    }
  });

  // Add tooltips to elements
  addTooltips(graph.value);
};

// Function to add tooltips to elements
const addTooltips = (graph) => {
  graph.getElements().forEach((element) => {
    const tooltip = new joint.ui.Tooltip({
      target: `[model-id="${element.id}"]`,
      content: element.get('data')?.info || `Info for ${element.id}`,
      top: 10,
      left: 10,
      direction: 'right',
      padding: 5,
      className: 'my-tooltip',
      style: {
        'background-color': 'white',
        'border': '1px solid black',
        'border-radius': '3px',
        'padding': '5px',
      },
    });
    tooltip.render();
  });
};

// Function to parse Petri net data
const parsePetriNet = (netString) => {
  try {
    const placesMatch = netString.match(/places:\s*\[([^\]]+)\]/);
    const transitionsMatch = netString.match(/transitions:\s*\[([^\]]+)\]/);
    const arcsMatch = netString.match(/arcs:\s*\[([^\]]+)\]/);

    const places = placesMatch ? placesMatch[1].split(',').map(p => p.trim()) : [];
    const transitions = transitionsMatch
      ? transitionsMatch[1].split(',').map(t => {
          const match = t.match(/\(\s*[^,]+,\s*'([^']+)'/);
          return match ? match[1] : t.trim();
        })
      : [];
    const arcs = arcsMatch
      ? arcsMatch[1].split(',').map(a => {
          const match = a.match(/\(([^,]+),\s*([^)]+)\)\s*->\s*([^,]+)/);
          return match ? { source: match[1].trim(), target: match[3].trim() } : null;
        }).filter(arc => arc !== null)
      : [];

    return { places, transitions, arcs };
  } catch (error) {
    console.error('Error parsing Petri net:', error);
    return { places: [], transitions: [], arcs: [] };
  }
};

// Mounted hook to initialize graph and paper
onMounted(() => {
  graph.value = new dia.Graph();

  paper.value = new dia.Paper({
    el: document.getElementById('paper-container'),
    model: graph.value,
    width: 800,
    height: 600,
    gridSize: 1,
  });

  // Fetch discovery results when the component is mounted
  processResourceStore.fetchDiscoveryResults(1);
});

// Watcher to update the Petri net visualization when discovery results change
watch(() => processResourceStore.discoveryResults, (newResults) => {
  if (newResults && newResults.length > 0) {
    petriNetData.value = newResults.map(result => ({
      method: result.method,
      data: parsePetriNet(result.net),
    }));
    // Render the first tab's Petri net initially
    renderPetriNet(petriNetData.value[0].data);
  }
}, { immediate: true });

// Watcher to update the Petri net visualization when the active tab changes
watch(activeTab, (newTab) => {
  if (petriNetData.value && petriNetData.value[newTab]) {
    renderPetriNet(petriNetData.value[newTab].data);
  }
});
</script>

<template>
  <main class="p-4 md:ml-64 h-auto pt-10">
    <div>
      <h1 class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl">
        <span class="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">Flow Miner,</span>
        Process Mining At Scale.
      </h1>
      <p class="text-lg font-normal text-gray-500 lg:text-xl dark:text-gray-400">
        Here at Flowbite we focus on markets where technology, innovation, and capital can unlock long-term value and drive economic growth.
      </p>
    </div>
    <div class="tabs mt-8">
      <ul class="flex">
        <li
          v-for="(method, index) in petriNetData"
          :key="index"
          :class="{ 'active-tab': activeTab === index }"
          class="px-4 py-2 cursor-pointer"
          @click="activeTab = index"
        >
          {{ method.method }}
        </li>
      </ul>
    </div>
    <div id="paper-container" class="mt-8"></div>
  </main>
</template>

<style scoped>
#paper-container {
  border: 1px solid #ccc;
  width: 100%;
  height: 600px;
}

.tabs ul {
  border-bottom: 1px solid #ccc;
}

.tabs ul li {
  border: 1px solid transparent;
  border-bottom: none;
  padding: 8px 16px;
}

.tabs ul li.active-tab {
  border-color: #ccc;
  border-bottom: none;
  background-color: #f5f5f5;
}

.my-tooltip {
  font-size: 12px;
  color: #333;
}
</style>
