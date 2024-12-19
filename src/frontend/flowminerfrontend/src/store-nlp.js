// src/stores/nlpStore.js

import { defineStore } from 'pinia';
import nlpService from './services/NLPService';

export const useNLPStore = defineStore('nlpStore', {
  state: () => ({
    queryStatus: null, // Holds the response data after making the NLP query
    loading: false,    // Boolean to indicate if the request is in progress
    error: null,       // Holds any error message from the request
  }),
  
  actions: {
    // Action to handle the NLP query
    async handleNLPQuery(query, processFileId) {
      this.loading = true; // Set loading to true when starting the request
      this.error = null;   // Clear any previous errors
      try {
        // Call the service method to send the query and get the response
        const data = await nlpService.handleNLPQuery(query, processFileId);
        this.queryStatus = data; // Store the response in the state
      } catch (error) {
        // If there's an error, store the error message
        this.error = error.message;
      } finally {
        // Set loading to false when the request is completed
        this.loading = false;
      }
    },

    // Test service action (optional for debugging the service connection)
    async testNLPService() {
      this.loading = true; // Set loading to true
      try {
        // Call the test service method
        const data = await nlpService.testNLPService();
        this.queryStatus = data; // Store the response
      } catch (error) {
        // If there's an error, store the error message
        this.error = error.message;
      } finally {
        // Set loading to false when the request is completed
        this.loading = false;
      }
    },

    // Action to clear errors if needed
    clearError() {
      this.error = null;
    },

    // Optional: Reset the query status if needed in the future
    resetQueryStatus() {
      this.queryStatus = null;
    }
  },
});
