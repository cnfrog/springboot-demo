package cn.ypjalt.springboot.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// 在容器中加入自己定义的ErrorAttributes
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    // 返回的 map 就是页面和 json 能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("author", "ypj");
        Object ext = webRequest.getAttribute("ext", 1);
        map.put("ext", ext);
        return map;
    }
}
