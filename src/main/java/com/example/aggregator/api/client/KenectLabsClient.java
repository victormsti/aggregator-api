package com.example.aggregator.api.client;

import com.example.aggregator.api.client.configuration.KenectLabsClientConfig;
import com.example.aggregator.api.client.response.ContactIntegrationBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KenectLabsClient", url = "${feign.kenect-labs.url}", configuration = KenectLabsClientConfig.class)
public interface KenectLabsClient {

    @GetMapping("contacts")
    ContactIntegrationBaseResponse getContacts(@RequestParam(value = "page", defaultValue = "1") Integer page);
}
