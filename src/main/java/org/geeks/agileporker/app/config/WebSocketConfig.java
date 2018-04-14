package org.geeks.agileporker.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	
	@Value("${websocket.origins}")
	private String[] origins;

	@Value("${messagebroker.host}")
	private String relayHost;
	@Value("${messagebroker.port}")
	private int relayPort;
	@Value("${messagebroker.username}")
	private String clientLogin;
	@Value("${messagebroker.password}")
	private String clientPasscode;
	
	// @Override
	// public void configureMessageBroker(MessageBrokerRegistry config) {
	// config.enableSimpleBroker("/topic");
	// config.setApplicationDestinationPrefixes("/app");
	// }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins(origins).withSockJS();
    }
    
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableStompBrokerRelay("/exchange")
				.setAutoStartup(true)
				.setRelayHost(relayHost)
				.setRelayPort(relayPort)
				.setClientLogin(clientLogin)
				.setClientPasscode(clientPasscode);
	}

}