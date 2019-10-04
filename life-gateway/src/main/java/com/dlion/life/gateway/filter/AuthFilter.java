package com.dlion.life.gateway.filter;

import com.dlion.life.gateway.auth.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局过滤器
 *
 * @author 李正元
 * @date 2019-06-08
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        if (StringUtils.contains(request.getURI().toString(), "/api/user/getWxUserInfo")) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");
        ServerHttpResponse response = exchange.getResponse();

        //TODO 接口权限校验
        if (!tokenUtil.verify(token)) {

            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String, Object> result = new HashMap<>(2);
                result.put("code", HttpStatus.UNAUTHORIZED.value());
                result.put("msg", "用户身份认证失败！");

                byte[] data = mapper.writeValueAsBytes(result);
                DataBuffer buffer = response.bufferFactory().wrap(data);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                response.getHeaders().add("Content-type", "application/json;charset=UTF-8");

                return response.writeWith(Mono.just(buffer));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
