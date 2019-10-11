package com.dlion.life.punch.component;

import com.alibaba.fastjson.JSONObject;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.annotation.Login;
import com.dlion.life.common.constant.ResultConstant;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/10/8
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserApi userApi;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            Login annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
            if (annotation != null) {
                String token = request.getHeader("token");
                if (!validateUser(token)) {
                    response.setStatus(HttpStatus.SC_UNAUTHORIZED);

                    Map<String, Object> result = new HashMap<>(2);
                    result.put("code", ResultConstant.ERROR);
                    result.put("msg", "用户身份认证失败！");

                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();

                    Object json = JSONObject.toJSON(result);
                    writer.write(json.toString());
                    logger.error("Access denied: {}，token: {}", request.getRequestURI(), token);

                    return false;
                }
            }
        }

        return true;
    }

    private boolean validateUser(String token) {
        if (token == null) {
            return false;
        }

        try {
            User user = userApi.getUserByToken(token);
            return !Objects.isNull(user);
        } catch (Exception e) {
            // invalid token
            return false;
        }
    }

}


