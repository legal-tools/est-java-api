package com.catalyseit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Est {

    private String token;
    private String login;
    private String password;
    private String estUrl;
    private String callbackUrl;
    private RestTemplate restTemplate;

    public Est(String login, String password, String estUrl, String callbackUrl) {
        this.login = login;
        this.password = password;
        this.estUrl = estUrl;
        this.restTemplate = new RestTemplate();
    }

    private void authenticate() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", login);
        map.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HashMap> entity = new HttpEntity<>(map, headers);

        String url = estUrl + "/v1/authenticate";
        ResponseEntity<EstResponse> response = restTemplate.postForEntity(url, entity, EstResponse.class);
        this.token = response.getBody().getIdToken();
    }

    private HttpEntity<String> getBasicEntity() {
        authenticate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.token);
        return new HttpEntity<>("parameters", headers);
    }

    public EstResponse sendAgreement(String filename, List<EstRecipient> recipients, byte[] file, boolean autoSendEmailEnabled) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            final String url = estUrl + "/signing/v1/agreements";
            this.authenticate();

            MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();

            //Agreement
            Agreement agreementObj = new Agreement(filename, callbackUrl, recipients, autoSendEmailEnabled);
            String json = mapper.writeValueAsString(agreementObj);
            byte[] agreement = json.getBytes(StandardCharsets.UTF_8);
            HttpHeaders agreementHeader = new HttpHeaders();
            agreementHeader.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<byte[]> agreementHttpEntity = new HttpEntity<>(agreement, agreementHeader);

            //file
            HttpHeaders fileHeader = new HttpHeaders();
            fileHeader.setContentType(MediaType.APPLICATION_PDF);
            fileHeader.setContentDispositionFormData("pdf", "pdf");
            HttpEntity<byte[]> fileHttpEntity = new HttpEntity<>(file, fileHeader);

            //multipart
            multipartRequest.add("agreement", agreementHttpEntity);
            multipartRequest.add("pdf", fileHttpEntity);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + this.token);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<EstResponse> response = restTemplate.postForEntity(url, requestEntity, EstResponse.class);

            return response.getBody();
        }
        catch(HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            e.printStackTrace();
            throw e;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sendNextMailForAgreement(String id) {
        HttpEntity<String> entity = getBasicEntity();
        final String url = this.estUrl + "/signing/v1/agreements/" + id + "/send-mails";
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    public void cancelAgreement(String id) {
        HttpEntity<String> entity = getBasicEntity();
        final String url = this.estUrl + "/signing/v1/agreements/" + id;
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    }

    public byte[] getFile(String id) {
        HttpEntity<String> entity = getBasicEntity();
        final String url = this.estUrl + "/signing/v1/agreements/" + id + "/current-file";
        ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        return result.getBody();
    }

    public byte[] getProofFile(String id) {
        HttpEntity<String> entity = getBasicEntity();
        final String url = this.estUrl + "/signing/v1/agreements/" + id + "/proof";
        ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        return result.getBody();
    }
}
