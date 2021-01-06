package com.superz.websocketspringboot.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangchao
 * @date 2020/10/27 15:03
 */
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {

    private final Map<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    public void sendMessage(String message) {
        SESSION_MAP.values().forEach(socketSession -> {
            try {
                socketSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSION_MAP.put(session.getId(), session);
        log.info("=== afterConnectionEstablished start === ");
        log.info("=== session id: {}", session.getId());
        log.info("=== session open: {}", session.isOpen());
        log.info("=== session uri: {}", session.getUri());
        log.info("=== session principal: {}", session.getPrincipal());
        log.info("=== afterConnectionEstablished end === ");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("=== handleMessage start === ");
        log.info("=== session id: {}", session.getId());
        log.info("=== session open: {}", session.isOpen());
        log.info("=== session uri: {}", session.getUri());
        log.info("=== session principal: {}", session.getPrincipal());
        log.info("=== message payload: {}", message.getPayload());
        log.info("=== handleMessage end === ");
        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        SESSION_MAP.remove(session.getId());
        log.info("=== handleTransportError start === ");
        log.info("=== session id: {}", session.getId());
        log.info("=== session open: {}", session.isOpen());
        log.info("=== session uri: {}", session.getUri());
        log.info("=== session principal: {}", session.getPrincipal());
        log.info("=== handleTransportError end === ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSION_MAP.remove(session.getId());
        log.info("=== afterConnectionClosed start === ");
        log.info("=== session id: {}", session.getId());
        log.info("=== session open: {}", session.isOpen());
        log.info("=== session uri: {}", session.getUri());
        log.info("=== session principal: {}", session.getPrincipal());
        log.info("=== afterConnectionClosed end === ");
        log.info("afterConnectionClosed: === " + closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
