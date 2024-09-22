// src/services/ProcessResourceService.js

import RestService from './RestServices';

class ProcessResourceService extends RestService {
  constructor() {
    super('/api/resource'); // Set the base URL for all endpoints related to process resources
  }

  listFiles(customError = 'Failed to list files') {
    return this.genericGet('/files', {}, customError);
  }

  processFile(fileName, personalTag, customError = 'Failed to process file') {
    const params = { fileName, personalTag };
    return this.genericPost('/process', {}, params, customError);
  }

  getAllProcessFiles(customError = 'Failed to retrieve process files') {
    return this.genericGet('/process-files', {}, customError);
  }

  getProcessFile(id, customError = 'Failed to retrieve process file') {
    return this.genericGet(`/process-files/${id}`, {}, customError);
  }

  deleteProcessFile(id, customError = 'Failed to delete process file') {
    return this.genericDelete(`/process-files/${id}`, {}, customError);
  }

  getDiscoveryResults(fileId, customError = 'Failed to retrieve discovery results') {
    return this.genericGet(`/process-files/${fileId}/discovery-results`, {}, customError);
  }

  getDiscoveryResult(id, customError = 'Failed to retrieve discovery result') {
    return this.genericGet(`/discovery-results/${id}`, {}, customError);
  }

  getPerformanceResults(fileId, customError = 'Failed to retrieve performance results') {
    return this.genericGet(`/process-files/${fileId}/performance-results`, {}, customError);
  }

  getFrequencyResults(fileId, customError = 'Failed to retrieve frequency results') {
    return this.genericGet(`/process-files/${fileId}/frequency-results`, {}, customError);
  }

  getFrequencyResult(id, customError = 'Failed to retrieve frequency result') {
    return this.genericGet(`/frequency-results/${id}`, {}, customError);
  }

  getAllBottleneckResults(fileId, customError = 'Failed to retrieve bottleneck results') {
    return this.genericGet(`/process-files/${fileId}/bottleneck-results`, {}, customError);
  }

  getAllUtilizationResults(fileId, customError = 'Failed to retrieve utilization results') {
    return this.genericGet(`/process-files/${fileId}/utilization-results`, {}, customError);
  }

  getUtilizationResult(id, customError = 'Failed to retrieve utilization result') {
    return this.genericGet(`/utilization-results/${id}`, {}, customError);
  }
}

const processResourceService = new ProcessResourceService();
export default processResourceService;
