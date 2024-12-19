// src/queryresults/resultsMap.js
import ProcessOverviewResult from './ProcessOverviewResult.vue';
import BottleneckAnalysisResult from './BottleneckAnalysisResult.vue';
import CaseDurationAnalysisResult from './CaseDurationAnalysisResult.vue';
import ResourceUtilizationResult from './ResourceUtilizationResult.vue';
import FrequencyAnalysisResult from './FrequencyAnalysisResult.vue';
import ProcessDiscoveryAlpha from './ProcessDiscoveryAlpha.vue';
import ProcessDiscoveryInductive from './ProcessDiscoveryInductive.vue';
import ProcessDiscoveryHeuristics from './ProcessDiscoveryHeuristics.vue';
import ProcessDiscoveryBPMNInductive from './ProcessDiscoveryBPMNInductive.vue';
import ProcessDiscoveryDFG from './ProcessDiscoveryDFG.vue';




import DefaultResult from './DefaultResult.vue'; // Fallback for undefined query types

const resultsMap = {
  'process overview': ProcessOverviewResult,
  'performance analysis - bottleneck': BottleneckAnalysisResult,
  'performance analysis - case duration': CaseDurationAnalysisResult,
  'performance analysis - resource utilization': ResourceUtilizationResult,
  'performance analysis - frequency': FrequencyAnalysisResult,

  'process discovery - dfg': ProcessDiscoveryDFG,
  'process discovery - alpha': ProcessDiscoveryAlpha,
  'process discovery - inductive': ProcessDiscoveryInductive,
  'process discovery - heuristics': ProcessDiscoveryHeuristics,
  'process discovery - bpmn inductive': ProcessDiscoveryBPMNInductive,
  
  'default': DefaultResult // A default component in case the queryType is not mapped
};

export default resultsMap;
