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

// Extract the directly follows graph edges from the result
const dfgEdges = ref(JSON.parse(props.result.responseJson).directly_follows_graph.edges);

const cyContainer = ref(null); // Ref for the Cytoscape container

// Initialize Cytoscape and render the graph
onMounted(() => {
  const cy = cytoscape({
    container: cyContainer.value, // Reference to the DOM element for rendering

    elements: [
      // Create nodes and edges from the DFG edges
      ...new Set(dfgEdges.value.map(edge => [
        { data: { id: edge.source, label: edge.source } },
        { data: { id: edge.target, label: edge.target } },
        { data: { source: edge.source, target: edge.target, label: edge.frequency } }
      ]).flat())
    ],

    style: [
      {
        selector: 'node',
        style: {
          'background-color': '#007BFF',
          'label': 'data(label)',
          'text-valign': 'center',
          'color': '#fff',
          'font-size': '12px',
          'text-outline-width': 2,
          'text-outline-color': '#007BFF'
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
      name: 'cose', // 'cose' layout is good for Directed Graphs
      animate: true
    }
  });
});
</script>

<template>
  <div class="p-4">
    <!-- Directly Follows Graph Visualization -->
    <h2 class="text-xl font-bold mb-4">Directly Follows Graph</h2>
    <div ref="cyContainer" class="h-96 w-full border-2 border-gray-300"></div>
  </div>
</template>

<style>
/* Add any additional styles if necessary */
</style>
