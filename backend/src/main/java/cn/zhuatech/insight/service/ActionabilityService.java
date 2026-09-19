/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.insight.service;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class ActionabilityService{/**
                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                            */
public Result evaluate(Request r){int score=(int)Math.round(r.confidence()*.35+r.businessImpact()*.35+Math.max(0,100-r.dataAgeHours())*.1+(r.ownerAssigned()?10:0)+(r.recommendedActionDefined()?10:0));score=Math.max(0,Math.min(100,score));List<String> gaps=new ArrayList<>();if(r.confidence()<70)gaps.add("补充验证数据或反事实分析");if(r.dataAgeHours()>24)gaps.add("刷新洞察数据");if(!r.ownerAssigned())gaps.add("指定行动责任人");if(!r.recommendedActionDefined())gaps.add("定义可执行动作和验收指标");String status=score>=80&&r.estimatedValue().signum()>0?"EXECUTE":score>=55?"REFINE":"PARK";if(gaps.isEmpty())gaps.add("洞察具备行动条件，可进入价值跟踪");return new Result(score,status,gaps);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@DecimalMin("0") @DecimalMax("100") double confidence,@DecimalMin("0") @DecimalMax("100") double businessImpact,@Min(0) int dataAgeHours,@NotNull Boolean ownerAssigned,@NotNull Boolean recommendedActionDefined,@NotNull @DecimalMin("0") BigDecimal estimatedValue){}/**
                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                */
public record Result(int actionabilityScore,String status,List<String> gaps){} }
