<script setup>
import { ref, onMounted } from 'vue';
import cytoscape from 'cytoscape';

// Props received from the parent component
const props = defineProps({
  result: {
    type: Object,
    required: true,
  }
});

// Extracting the places, transitions, and arcs from the responseJson
const net = JSON.parse(props.result.responseJson).net;
const transitions = net.match(/\(.*?\)/g); // Extracting transitions
const places = net.match(/places: \[.*?\]/)[0].replace('places: [', '').replace(']', '').split(',');

// Prepare the graph elements from places, transitions, and arcs
const nodes = [];
const edges = [];

// Parse transitions
transitions.forEach((transition) => {
  const [placeSet, targetSet] = transition.replace(/[(){}]/g, '').split('->');

  const placesFrom = placeSet.split(',').map((p) => p.trim());
  const placesTo = targetSet.split(',').map((p) => p.trim());

  placesFrom.forEach((placeFrom) => {
    nodes.push({ data: { id: placeFrom, label: placeFrom }, classes: 'place' });
  });
  placesTo.forEach((placeTo) => {
    nodes.push({ data: { id: placeTo, label: placeTo }, classes: 'place' });
  });

  edges.push({
    data: { source: placesFrom[0], target: placesTo[0] },
  });
});

const cyContainer = ref(null); // Ref for the Cytoscape container

// Initialize Cytoscape and render the graph
onMounted(() => {
  const cy = cytoscape({
    container: cyContainer.value, // Reference to the DOM element for rendering

    elements: [
      // Add nodes and edges for the Petri net
      ...nodes,
      ...edges
    ],

    style: [
      {
        selector: '.place',
        style: {
          'background-color': '#f39c12',
          'label': 'data(label)',
          'shape': 'ellipse',
          'width': 30,
          'height': 30,
          'text-valign': 'center',
          'color': '#000',
          'font-size': '12px',
        }
      },
      {
        selector: '.transition',
        style: {
          'background-color': '#2ecc71',
          'label': 'data(label)',
          'shape': 'rectangle',
          'width': 20,
          'height': 20,
          'text-valign': 'center',
          'color': '#000',
          'font-size': '12px',
        }
      },
      {
        selector: 'edge',
        style: {
          'width': 2,
          'line-color': '#ccc',
          'target-arrow-color': '#ccc',
          'target-arrow-shape': 'triangle',
          'curve-style': 'bezier',
          'label': 'data(label)',
          'font-size': '10px',
          'color': '#555'
        }
      }
    ],

    layout: {
      name: 'dagre',
      animate: true
    }
  });
});
</script>

<template>
  <div class="p-4">
    <!-- Alpha Miner Petri Net Visualization -->
    <h2 class="text-xl font-bold mb-4">Alpha Miner - Petri Net Visualization</h2>
    <div ref="cyContainer" class="h-96 w-full border-2 border-gray-300"></div>
  </div>
</template>

<style>
/* Add any additional styles if necessary */
</style>
