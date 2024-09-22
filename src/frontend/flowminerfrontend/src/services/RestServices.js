// src/services/RestService.js

import axios from 'axios';

class RestService {
  constructor(baseURL) {
    this.apiClient = axios.create({
      baseURL,
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }

  async genericGet(endpoint, params = {}, customError = 'Error occurred during GET request') {
    try {
      const response = await this.apiClient.get(endpoint, { params });
      return response.data;
    } catch (error) {
      console.error(customError, error);
      throw new Error(customError);
    }
  }

  async genericPost(endpoint, data = {}, params = {}, customError = 'Error occurred during POST request') {
    try {
      const response = await this.apiClient.post(endpoint, data, { params });
      return response.data;
    } catch (error) {
      console.error(customError, error);
      throw new Error(customError);
    }
  }

  async genericPut(endpoint, data = {}, params = {}, customError = 'Error occurred during PUT request') {
    try {
      const response = await this.apiClient.put(endpoint, data, { params });
      return response.data;
    } catch (error) {
      console.error(customError, error);
      throw new Error(customError);
    }
  }

  async genericDelete(endpoint, params = {}, customError = 'Error occurred during DELETE request') {
    try {
      const response = await this.apiClient.delete(endpoint, { params });
      return response.data;
    } catch (error) {
      console.error(customError, error);
      throw new Error(customError);
    }
  }

  // Add other methods like genericPatch if needed
}

export default RestService;
