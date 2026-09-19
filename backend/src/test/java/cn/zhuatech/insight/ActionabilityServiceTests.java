/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.insight;import cn.zhuatech.insight.service.ActionabilityService;import org.junit.jupiter.api.Test;import java.math.*;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ActionabilityServiceTests{private final ActionabilityService s=new ActionabilityService();/**
                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                 */
@Test void executesStrongOwnedInsight(){var r=s.evaluate(new ActionabilityService.Request(95,90,2,true,true,new BigDecimal("100000")));assertEquals("EXECUTE",r.status());}/**
                                                                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                            */
@Test void parksWeakStaleInsight(){var r=s.evaluate(new ActionabilityService.Request(20,20,100,false,false,BigDecimal.ZERO));assertEquals("PARK",r.status());}}
