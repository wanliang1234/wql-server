package com.wql.project.two.tyfhz;

import com.wql.project.two.yc.ExceptionResponse;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

//com.wql.project.two这个地址下全部监听到 @RestControllerAdvice是一个组合注解，由@ControllerAdvice、@ResponseBody组成。携带此注解的类型被视为控制器advice
@RestControllerAdvice(basePackages = "com.wql.project.two")
//当配置中心的配置发生变化时，带有@RefreshScope注解的Bean会自动刷新，以反映新的配置信息。
@RefreshScope
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        boolean isIntercept = true;

        /**
         * 方法的名称（通过 getName() 方法获取）。
         * 方法的返回类型（通过 getReturnType() 方法获取）。
         * 方法的参数类型（通过 getParameterTypes() 方法获取）。
         * 方法的修饰符（通过 getModifiers() 方法获取）。
         * 方法抛出的异常类型（通过 getExceptionTypes() 方法获取）。
         * 方法的注解（通过 getAnnotations() 方法获取）。
         * 方法所在的类（通过 getDeclaringClass() 方法获取）。
         */
        Method method = returnType.getMethod();

        /**
         * method.getReturnType().isAssignableFrom(String.class): 这个条件检查方法的返回类型是否是String类或其子类。如果是，这个条件为真。
         * method.getName().startsWith("test"): 这个条件检查方法的名称是否以"test"开头。如果是，这个条件为真。
         */
        if (method.getReturnType().isAssignableFrom(String.class) && method.getName().startsWith("test")) {
            isIntercept = false;
        }

        return isIntercept;
    }

    /**
     * 返回结果包装
     *
     * @param body                controller返回的数据
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof R) {
            return body;
        }

        if (body instanceof Boolean) {
            boolean result = (boolean) body;
            return new BaseResponse<Boolean>(result);
        }

        if (body instanceof ExceptionResponse) {
            return new BaseResponse<>(((ExceptionResponse) body).getCode(), ((ExceptionResponse) body).getMsg());
        }

        return new BaseResponse<>(body);
    }
}
