package org.gdg.zipte_gdg.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    private static final int TIMEOUT_MS = 60000;
    private String serpUrl = "http://localhost:8000";

    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MS)
            .doOnConnected(conn -> conn
                    .addHandlerLast(new ReadTimeoutHandler(TIMEOUT_MS))
                    .addHandlerLast(new WriteTimeoutHandler(TIMEOUT_MS))
            )
            .responseTimeout(Duration.ofSeconds(TIMEOUT_MS));

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(serpUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
    }
}
