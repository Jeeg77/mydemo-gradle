package com.example.demo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.http.Header;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUri;

    @Value("${spring.elasticsearch.username:#{null}}")
    private String username;

    @Value("${spring.elasticsearch.password:#{null}}")
    private String password;

    @Bean
    ElasticsearchClient elasticsearchClient() {
        HttpHost host = parseHttpHost(elasticsearchUri);

        RestClientBuilder builder = RestClient.builder(host);

        // Se username e password sono definiti, vengono utilizzati per l'autenticazione.
        if (username != null && password != null) {
            builder.setDefaultHeaders(new Header[]{
                    new BasicHeader("Authorization", basicAuthHeader(username, password))
            });
        }

        RestClient restClient            = builder.build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    private HttpHost parseHttpHost(String uri) {
       
        String withoutScheme = uri.replaceFirst("^https?://", "");
        String[] parts       = withoutScheme.split(":");
        String host          = parts[0];
        int port             = parts.length > 1 ? Integer.parseInt(parts[1]) : 9200;
        String scheme        = uri.startsWith("https") ? "https" : "http";

        return new HttpHost(host, port, scheme);
    }

    private String basicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
}
