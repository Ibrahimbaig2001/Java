package hospital_management.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate;

    public WebhookService() {
        this.restTemplate = new RestTemplate();
    }

    public void sendWebhook(String webhookUrl, Map<String, Object> payload) {

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        webhookUrl,
                        payload,
                        String.class
                );

        System.out.println("Webhook response: " + response.getStatusCode());
    }
}