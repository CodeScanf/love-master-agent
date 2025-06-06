# Love_Master_Agent
## 初始化项目
- 项目初始化，引入依赖。
- 创建项目结构
- 在demo.invoke文件中尝试四种大模型调用方式包括
    - Http调用
    - LangChain
    - Sdk
    - SpringAI
## 初始调用大模型
- 创建`SYSTEM_PROMPT`
- 初始化`chatClient`
- 实现初始化基于内存的对话记忆
- 实现初始化基于文件的对话记忆
- 实现自定义日志输出
## 实现local rag和cloud rag
- 实现local rag
  - 实现[LoveAppDocumentLoader.java](src/main/java/com/xiaoli/lovemasteragent/rag/LoveAppDocumentLoader.java)读取所有`Markdown`文档并转换为`Document`列表
  - 实现[LoveAppVectorStoreConfig.java](src/main/java/com/xiaoli/lovemasteragent/rag/LoveAppVectorStoreConfig.java)初始化向量数据库
  - 创建方法测试
- 实现cloud rag
  - 在阿里云百炼客户端创建知识库
  - 在本地创建[LoveAppRagCloudAdvisorConfig.java](src/main/java/com/xiaoli/lovemasteragent/rag/LoveAppRagCloudAdvisorConfig.java)实例化客户端
  - 创建方法测试