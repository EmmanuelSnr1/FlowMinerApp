// src/services/NLPService.js

import RestService from './RestServices';

class NLPService extends RestService {
  constructor() {
    super('/api/nlp'); // Base URL for the NLP API
  }

  handleNLPQuery(query, processFileId, customError = 'Failed to handle NLP query') {
    const params = { query, processFileId };
    return this.genericPost('/query', {}, params, customError);
  }

  testNLPService(customError = 'Failed to test NLP service') {
    return this.genericGet('/test', {}, customError);
  }
}

const nlpService = new NLPService();
export default nlpService;
