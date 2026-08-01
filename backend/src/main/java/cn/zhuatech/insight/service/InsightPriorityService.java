/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.insight.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 按影响、置信度、紧迫度和数据质量对洞察进行可解释排序。 */
@Service
public class InsightPriorityService {
    public PriorityResult prioritize(PriorityRequest request) {
        double score = request.impact() * 0.35 + request.confidence() * 0.25 + request.urgency() * 0.20 + request.dataQuality() * 0.20;
        int roundedScore = (int) Math.round(score);
        List<String> cautions = new ArrayList<>();
        if (request.confidence() < 60) cautions.add("洞察置信度偏低");
        if (request.dataQuality() < 70) cautions.add("底层数据质量不足");
        if (request.sensitiveDecision()) cautions.add("涉及敏感决策，必须人工审批");
        String priority = request.sensitiveDecision() || roundedScore >= 85 ? "P0" : roundedScore >= 70 ? "P1" : roundedScore >= 50 ? "P2" : "P3";
        boolean reviewRequired = request.sensitiveDecision() || request.confidence() < 60 || request.dataQuality() < 70;
        return new PriorityResult(priority, roundedScore, reviewRequired, List.copyOf(cautions), reviewRequired ? "先完成证据复核再分发" : "进入洞察订阅与行动跟踪");
    }

    public record PriorityRequest(
        @NotBlank(message = "请输入洞察标题") String title,
        @DecimalMin("0.0") @DecimalMax("100.0") double impact,
        @DecimalMin("0.0") @DecimalMax("100.0") double confidence,
        @DecimalMin("0.0") @DecimalMax("100.0") double urgency,
        @DecimalMin("0.0") @DecimalMax("100.0") double dataQuality,
        boolean sensitiveDecision
    ) {}

    public record PriorityResult(String priority, int score, boolean reviewRequired, List<String> cautions, String nextAction) {}
}
