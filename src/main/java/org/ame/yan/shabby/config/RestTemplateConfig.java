package org.ame.yan.shabby.config; // 替换为您的配置类所在的包名

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig { // 您可以给这个配置类取任何合适的名字

    @Bean
    public RestTemplate restTemplate() {
        // 在这里您可以对 RestTemplate 进行一些自定义配置，例如设置超时、拦截器等
        // 对于基础用法，直接返回一个新的实例即可
        RestTemplate restTemplate = new RestTemplate();

        // 示例：设置连接超时和读取超时 (可选)
        // org.springframework.http.client.SimpleClientHttpRequestFactory requestFactory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
        // requestFactory.setConnectTimeout(5000); // 连接超时时间，单位毫秒
        // requestFactory.setReadTimeout(5000);    // 读取超时时间，单位毫秒
        // restTemplate.setRequestFactory(requestFactory);

        // 示例：添加自定义的ClientHttpRequestInterceptor (可选)
        // restTemplate.getInterceptors().add(new MyCustomInterceptor());

        return restTemplate;
    }
}