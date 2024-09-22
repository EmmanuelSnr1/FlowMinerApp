// src/stores/processResourceStore.js

import { defineStore } from 'pinia';
import processResourceService from './services/ProcessMiningService';

export const useProcessResourceStore = defineStore('processResourceStore', {
  state: () => ({
    files: [],
    processFiles: [],
    discoveryResults: [],
    performanceResults: [],
    frequencyResults: [],
    bottleneckResults: [],
    utilizationResults: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchFiles() {
      this.loading = true;
      try {
        const data = await processResourceService.listFiles();
        this.files = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchProcessFiles() {
      this.loading = true;
      try {
        const data = await processResourceService.getAllProcessFiles();
        this.processFiles = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async processFile(fileName, personalTag) {
      this.loading = true;
      try {
        const data = await processResourceService.processFile(fileName, personalTag);
        this.processFiles.push(data);
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async deleteProcessFile(id) {
      this.loading = true;
      try {
        await processResourceService.deleteProcessFile(id);
        this.processFiles = this.processFiles.filter(file => file.id !== id);
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchDiscoveryResults(fileId) {
      this.loading = true;
      try {
        const data = await processResourceService.getDiscoveryResults(fileId);
        this.discoveryResults = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchPerformanceResults(fileId) {
      this.loading = true;
      try {
        const data = await processResourceService.getPerformanceResults(fileId);
        this.performanceResults = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchFrequencyResults(fileId) {
      this.loading = true;
      try {
        const data = await processResourceService.getFrequencyResults(fileId);
        this.frequencyResults = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchBottleneckResults(fileId) {
      this.loading = true;
      try {
        const data = await processResourceService.getAllBottleneckResults(fileId);
        this.bottleneckResults = data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async fetchUtilizationResults(fileId) {
      this.loading = true;
      try {
        const data = await processResourceService.getAllUtilizationResults(fileId);
        this.utilizationResults = data;
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
