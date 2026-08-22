/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.insight.ai;
import org.springframework.stereotype.Component; import java.util.Map;
public interface AiProvider { AiResult execute(String prompt,Map<String,String> context); record AiResult(String provider,String answer,Map<String,Object> evidence){} }
@Component class DemoAiProvider implements AiProvider { public AiResult execute(String prompt,Map<String,String> context){return new AiResult("demo-insight-provider","演示模式已生成指标查询计划与经营洞察，生产环境请替换 AiProvider。",Map.of("metrics",8,"confidence",0.93,"promptLength",prompt.length()));} }
