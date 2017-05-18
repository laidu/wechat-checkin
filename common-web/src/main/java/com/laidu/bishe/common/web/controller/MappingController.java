package com.laidu.bishe.common.web.controller;

import com.laidu.bishe.common.web.result.JSONResult;
import com.laidu.bishe.common.web.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenwen on 16/11/26.
 */
@Controller
@RequestMapping("/mappings/")
@Slf4j
public class MappingController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Value("${server.context-path:/}")
    private String path;


    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    @ResponseBody
    public Result list() {

        List<String> basicPermission = new ArrayList<>();

        List<HashMap<String, String>> urlList = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HashMap<String, String> hashMap = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                hashMap.put("url", url);
            }
            hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            hashMap.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type != null && type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                hashMap.put("type", type); // 方法名
            }
            urlList.add(hashMap);


            StringBuilder stringBuilder = new StringBuilder();

            if (hashMap.get("type").equalsIgnoreCase("get")){
                stringBuilder.append("r");
            }else {
                stringBuilder.append("w");
            }

            if (!StringUtils.isEmpty(path) && path.length() > 1){
                if (path.startsWith("/")){
                    stringBuilder.append(path.replaceAll("/",":"));
                }else {
                    stringBuilder.append(":").append(path.replaceAll("/",":"));
                }
            }

            String url = hashMap.get("url");

            String[] params = url.split("/");

            for(String param : params){
                if (StringUtils.isEmpty(param)) continue;

                if (!stringBuilder.toString().endsWith(":")) {
                    stringBuilder.append(":");
                }

                if (param.startsWith("{")){
                    stringBuilder.append("*");
                }else {
                    stringBuilder.append(param);
                }
            }

            System.out.println((stringBuilder.toString()));
        }

        log.info("urlList = {}",urlList);

        log.info("basicPermission = {}",basicPermission);

        return JSONResult.ok();
    }


}
