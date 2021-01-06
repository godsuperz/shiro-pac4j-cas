package com.superz.websocketspringboot.config;

import com.superz.websocketspringboot.websocket.MyHandshakeInterceptor;
import com.superz.websocketspringboot.websocket.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author zhangchao
 * @date 2020/10/27 12:01
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/myHandler")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), myHandshakeInterceptor())
                .setAllowedOrigins("*").withSockJS().setSessionCookieNeeded(true);
    }

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public HandshakeInterceptor myHandshakeInterceptor() {
        return new MyHandshakeInterceptor();
    }
}
