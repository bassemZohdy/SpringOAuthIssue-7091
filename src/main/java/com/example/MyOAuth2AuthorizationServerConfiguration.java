package com.example;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class MyOAuth2AuthorizationServerConfiguration extends OAuth2AuthorizationServerConfiguration {
	@Autowired
	private AccessTokenConverter accessTokenConverter;

	public MyOAuth2AuthorizationServerConfiguration(BaseClientDetails details,
			AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStoreProvider,
			AuthorizationServerProperties properties) {
		super(details, authenticationManager, tokenStoreProvider, properties);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		super.configure(endpoints);
		//suggested part without this the token generated is not jwt
		if (this.accessTokenConverter != null) {
			endpoints.accessTokenConverter(this.accessTokenConverter);
		}
	}

}
