package me.study.mas.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter
//        return (exchange, chain) -> {
//            final ServerHttpRequest request = exchange.getRequest();
//            final ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global Filter baseMessage : {}", config.getBaseMessage());
//            if (config.isPreLogger()) {
//                log.info("Global Filter Start : request id = {}", request.getId());
//            }
//
//            // Custom Post Filter
//            return chain.filter(exchange)
//                        .then(Mono.fromRunnable(() -> {
//                            if (config.isPostLogger()) {
//                                log.info("Global Filter End : response code = {}", response.getStatusCode());
//                            }
//                        }));
//        };

        // 동일한 구현
        return new OrderedGatewayFilter((exchange, chain) -> {
            final ServerHttpRequest request = exchange.getRequest();
            final ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage : {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("Logging Filter Start : request id = {}", request.getId());
            }

            // Custom Post Filter
            return chain.filter(exchange)
                        .then(Mono.fromRunnable(() -> {
                            if (config.isPostLogger()) {
                                log.info("Logging Filter End : response code = {}", response.getStatusCode());
                            }
                        }));
        }, Ordered.LOWEST_PRECEDENCE); // 필터 우선순위 : Lowest 하면 가장 마지막에 실행됨.
    }

    @Data
    public static class Config {
        // Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
