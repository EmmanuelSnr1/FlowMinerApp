// src/services/NLPService.js

import RestService from './RestServices';

class NLPService extends RestService {
  constructor() {
    super('/api/nlp'); // Base URL for the NLP API
  }

  // Updated service method
// Updated service method
handleNLPQuery(query, processFileId, customError = 'Failed to handle NLP query') {
  const data = {
      query: query,
      processFileId: processFileId
  };
  // Send the data as the request body
  return this.genericPost('/query', data, {}, customError);
}



  testNLPService(customError = 'Failed to test NLP service') {
    return this.genericGet('/test', {}, customError);
  }
}

const nlpService = new NLPService();
export default nlpService;
