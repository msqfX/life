package com.dlion.life.admin.notify;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务状态变更通知
 *
 * @author 李正元
 * @date 2019/10/7
 */
@Component
public class DingTalkNotify extends AbstractStatusChangeNotifier {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate = new RestTemplate();

    private static final String DEFAULT_MESSAGE = "*#{instance.registration.name}* (#{instance.id}) is *#{event.statusInfo.status}**";

    private Expression message;

    private final String dingTalkMessageUrl = "https://oapi.dingtalk.com/robot/send?access_token=%s";

    @Value("${dingTalk.accessToken}")
    private String accessToken;


    public DingTalkNotify(InstanceRepository repositpry) {
        super(repositpry);
        final SpelExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
        this.message = EXPRESSION_PARSER.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {
        String url = String.format(dingTalkMessageUrl, accessToken);
        return Mono.fromRunnable(() -> restTemplate.postForEntity(url, createMessage(instanceEvent, instance), Void.class));
    }

    private HttpEntity<Map<String, Object>> createMessage(InstanceEvent event, Instance instance) {

        Map<String, Object> messageJson = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("title", "服务告警");
        params.put("text", this.getMessage(event, instance));

        messageJson.put("msgtype", "text");
        messageJson.put("content", params);
        messageJson.put("isAtAll", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(messageJson, headers);
    }

    private String getMessage(InstanceEvent event, Instance instance) {

        Map<String, Object> root = new HashMap<>();
        root.put("event", event);
        root.put("instance", instance);
        root.put("lastStatus", getLastStatus(event.getInstance()));

        StandardEvaluationContext context = new StandardEvaluationContext(root);
        context.addPropertyAccessor(new MapAccessor());

        return message.getValue(context, String.class);
    }

}
