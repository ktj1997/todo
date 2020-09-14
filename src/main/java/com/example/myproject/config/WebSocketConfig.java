package com.example.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
    웹소켓 --> 웹 브라우저와 서버간의 양방향, 전이중적, 지속적인 연결의 특징을 갖는 프로토콜
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
   /*WebSocketMessageBrokerConfigurer(웹소켓 서버를 활성화 하는데 사용)
     *stomp --> SimpleTextOrientedMessageProtocol --> 데이터 교환의 형식과 규칙을 정의하는 메세징 프로토콜
     *WebSocket은 프로토콜의 일종 --> 정확한 구현내용 등은 표현이 되어있지 않기 때문에, STOMP를 이용
    */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        //클라이언트가 웹소켓 서버에 연결하는데 사용하는 웹 소켓 엔드포인트를 등록합니다
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        //엔드포인트 구성에 withSocketJs활용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        registry.setApplicationDestinationPrefixes("/app");
        // URL /app 으로 시작되는 메세지가 mesage-handling methods로 라우팅 되어야한다는 것을 명시합니다.
        //메세지를 수신하는 handler의 prefix설정

        registry.enableSimpleBroker("/topic");
        //인메모리 브로커
        // URL /topic 으로 시작되는 메세지가 메세지 브로커로 라우팅되도록 정의합니다.
        //메세지브로커는 특정주제를 구독한 연결된 모든 클라이언트에게 메세지를 broadcast합니다. (broadcast는 송신호스트가 연결된 모든 호스트에 메세지를 전송하는방식)
    }

}