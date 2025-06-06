package com.xiaoli.lovemasteragent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeAgentApi;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentCloudReader;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import com.alibaba.cloud.ai.dashscope.rerank.DashScopeRerankModel;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: LoveAppRagCloudAdvisorConfig
 * @Description:  rag——Cloud 客户端实例化
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/6 15:00
 * @Version: 1.0
 **/
@Configuration
@Slf4j
public class LoveAppRagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String dashscopeApiKey;

    @Bean
    public Advisor loveAppRagCloudAdvisor(){
        DashScopeApi dashScopeApi = new DashScopeApi(dashscopeApiKey);
        final String KNOWLEDGE_INDEX = "Love-Master-Agent";
        DocumentRetriever documentRetriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .withIndexName(KNOWLEDGE_INDEX)
                        .build());
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .build();
    }
}
