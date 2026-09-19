/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.insight.config;
import cn.zhuatech.insight.model.*; import cn.zhuatech.insight.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {/**
                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                              */
@Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository records,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
 OperatingUnit biz=units.save(new OperatingUnit("INS-BIZ","经营分析域","财务与销售",5000)),mkt=units.save(new OperatingUnit("INS-MKT","营销分析域","电商事业部",4000)),scm=units.save(new OperatingUnit("INS-SCM","供应链分析域","供应链中心",3000));
 WorkRecord a=records.save(new WorkRecord("INS-260801-084","TOPIC-PROFIT-EAST","华东区域毛利率下降归因分析",biz,42,38,2,LocalDate.now().plusDays(1),WorkRecord.Status.RUNNING,"SEMANTIC-V3.2")); WorkRecord b=records.save(new WorkRecord("INS-260801-071","TOPIC-NPI-FUNNEL","电商渠道新品转化漏斗分析",mkt,36,36,0,LocalDate.now(),WorkRecord.Status.COMPLETED,"FUNNEL-V2.1")); WorkRecord c=records.save(new WorkRecord("INS-260801-106","TOPIC-INVENTORY","库存周转异常门店定位",scm,54,32,6,LocalDate.now().plusDays(2),WorkRecord.Status.RELEASED,"METRIC-V4.0"));
 resources.saveAll(List.of(new ResourceRegister("SEM-BIZ-001","经营分析语义层",biz,ResourceRegister.Status.RUNNING,96),new ResourceRegister("SEM-MKT-002","营销增长语义层",mkt,ResourceRegister.Status.RUNNING,92),new ResourceRegister("SEM-SCM-003","供应链语义层",scm,ResourceRegister.Status.ALARM,76)));
 reviews.saveAll(List.of(new ReviewRecord("CHK-260801-032",a,"结论准确性",8,1,ReviewRecord.Result.PENDING,"林衡"),new ReviewRecord("CHK-260801-011",b,"口径一致性",36,0,ReviewRecord.Result.PASSED,"陆乔"),new ReviewRecord("CHK-260731-018",c,"数据质量",24,4,ReviewRecord.Result.FAILED,"林衡")));
 String demo=encoder.encode("Demo@2026"); users.saveAll(List.of(new UserAccount("operator",demo,"陆乔",UserAccount.Role.DOMAIN_USER,"INS-BIZ"),new UserAccount("planner",demo,"林衡",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"顾清",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));};}}
