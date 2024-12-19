<script setup>
import { ref, onMounted } from 'vue';
import cytoscape from 'cytoscape';

// Props passed from the parent component (Inductive Miner result)
const props = defineProps({
  result: {
    type: Object,
    required: true,
  }
});

// Safely parse the responseJson from the result
let netData = null;
try {
  netData = JSON.parse(props.result.responseJson).net;
} catch (error) {
  console.error('Error parsing responseJson:', error);
}

// Prepare the graph elements
const nodes = [];
const edges = [];

if (netData) {
  // Use regex to extract transitions and places
  const transitions = netData.match(/\(.*?\)/g) || []; // Default to empty array if no matches
  const places = (netData.match(/places: \[.*?\]/)?.[0]?.replace('places: [', '').replace(']', '').split(',') || []).map(place => place.trim());

  // Add places as nodes
  places.forEach((place) => {
    if (place && !nodes.some((n) => n.data.id === place)) {
      nodes.push({ data: { id: place, label: place }, classes: 'place' });
    }
  });

  // Add transitions and edges
  transitions.forEach((transition) => {
    const cleanTransition = transition.replace(/[()]/g, '').split(',').map(item => item?.trim());

    // Ensure both the transition and the label are properly defined
    if (cleanTransition.length >= 2) {
      const [transitionData, label] = cleanTransition;
      const [placeSet, transitionId] = transitionData?.split('->')?.map(item => item?.trim()) || [];

      if (placeSet && transitionId) {
        // Add transition as a node
        nodes.push({ data: { id: transitionId, label: label ? label.replace(/'/g, '') : 'tau' }, classes: 'transition' });

        // Add edges connecting places to transitions
        placeSet.split(',').forEach((place) => {
          const trimmedPlace = place?.trim();
          if (trimmedPlace) {
            edges.push({
              data: { source: trimmedPlace, target: transitionId }
            });
          }
        });
      }
    }
  });
}

const cyContainer = ref(null); // Ref for Cytoscape container

// Initialize Cytoscape and render the graph
onMounted(() => {
  if (cyContainer.value && nodes.length > 0 && edges.length > 0) {
    const cy = cytoscape({
      container: cyContainer.value, // Reference to the DOM element

      elements: [
        // Add nodes and edges for the Petri net
        ...nodes,
        ...edges
      ],

      style: [
        {
          selector: '.place',
          style: {
            'background-color': '#3498db',
            'label': 'data(label)',
            'shape': 'ellipse',
            'width': 40,
            'height': 40,
            'text-valign': 'center',
            'color': '#fff',
            'font-size': '12px',
          }
        },
        {
          selector: '.transition',
          style: {
            'background-color': '#e74c3c',
            'label': 'data(label)',
            'shape': 'rectangle',
            'width': 20,
            'height': 20,
            'text-valign': 'center',
            'color': '#fff',
            'font-size': '10px',
          }
        },
        {
          selector: 'edge',
          style: {
            'width': 2,
            'line-color': '#2c3e50',
            'target-arrow-color': '#2c3e50',
            'target-arrow-shape': 'triangle',
            'curve-style': 'bezier',
            'label': 'data(label)',
            'font-size': '10px',
            'color': '#34495e',
          }
        }
      ],

      layout: {
        name: 'breadthfirst',
        animate: true,
        directed: true,
      }
    });
  }
});
</script>

<template>
  <div class="p-4">
    <!-- Inductive Miner Petri Net Visualization -->
    <h2 class="text-xl font-bold mb-4">Inductive Miner - Petri Net Visualization</h2>
    <div ref="cyContainer" class="h-96 w-full border border-gray-300"></div>
  </div>
</template>

<style>
/* Add any additional styles if necessary */
</style>
