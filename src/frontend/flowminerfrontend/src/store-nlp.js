// src/stores/nlpStore.js

import { defineStore } from 'pinia';
import nlpService from './services/NLPService';

export const useNLPStore = defineStore('nlpStore', {
  state: () => ({
    queryStatus: null,
    loading: false,
    error: null,
  }),
  actions: {
    async handleNLPQuery(query, processFileId) {
      this.loading = true;
      try {
        const data = await nlpService.handleNLPQuery(query, processFileId);
        this.queryStatus = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async testNLPService() {
      this.loading = true;
      try {
        const data = await nlpService.testNLPService();
        this.queryStatus = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    clearError() {
      this.error = null;
    },
  },
});
