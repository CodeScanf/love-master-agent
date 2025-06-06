package com.xiaoli.lovemasteragent.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName: LoveAppDocumentLoader
  * @Description: 读取所有 Markdown 文档并转换为 Document 列表
  * @Auth: lxiaobin@gmail
  * @Date: 2025/6/5 17:50
  * @Version: 1.0
**/
@Component
@Slf4j
public class LoveAppDocumentLoader {
    private final ResourcePatternResolver resourcePatternResolver;

    public LoveAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns() {
        List<Document> documents = new ArrayList<>();
        try{
            Resource[] resources = resourcePatternResolver.getResources("classpath*:document/*.md");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeBlockquote(false)
                        .withIncludeCodeBlock(false)
                        .withAdditionalMetadata("fileName", filename)
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                documents.addAll(reader.get());

            }
        }catch (IOException e){
            log.error("加载markdown文件失败", e);
        }
        return documents;
    }
}
