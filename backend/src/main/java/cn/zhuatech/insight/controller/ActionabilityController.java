/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.insight.controller;import cn.zhuatech.insight.common.ApiResponse;import cn.zhuatech.insight.service.ActionabilityService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/insight/insights/actionability") public class ActionabilityController{private final ActionabilityService service;/**
                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                        */
public ActionabilityController(ActionabilityService service){this.service=service;}/**
                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                           */
@PostMapping ApiResponse<ActionabilityService.Result> evaluate(@Valid @RequestBody ActionabilityService.Request r){return ApiResponse.ok(service.evaluate(r));}}
