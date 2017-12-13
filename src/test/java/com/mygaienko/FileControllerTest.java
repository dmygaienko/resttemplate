package com.mygaienko;

import com.fasterxml.jackson.databind.JsonNode;
import com.mygaienko.api.dto.DownloadFileResponse;
import com.mygaienko.api.dto.FileResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;

public class FileControllerTest {

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGet() {
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/get", HttpMethod.GET, null, FileResponse.class);
        System.out.println(response.getBody());
    }

    @Test
    public void testDelete() {
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/delete", HttpMethod.DELETE, null, FileResponse.class);
        System.out.println(response.getBody());
    }

    @Test
    public void testPost() throws IOException, URISyntaxException {
        sendFile("/pdf.pdf");
    }

    @Test
    public void testPostBigFile() throws IOException, URISyntaxException {
        sendFile("/oversize_pdf.pdf");
    }

    private void sendFile(String fileName) {
        URL resource = getClass().getResource(fileName);

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
        fileRequest.add("file", new FileSystemResource(new File(resource.getPath())));
        fileRequest.add("systemName", "sysName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/upload", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testPostSimple() throws IOException, URISyntaxException {
        URL resource = getClass().getResource("/pdf.pdf");
//        MultipartFile file = new MockMultipartFile("pdf.pdf", Files.readAllBytes(Paths.get(resource.toURI())));
        /*FileRequest fileRequest = new FileRequest("sysName", file);
        HttpEntity<FileRequest> requestEntity = new HttpEntity<>(fileRequest);*/

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
        fileRequest.add("file", new FileSystemResource(new File(resource.getPath())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/uploadSimple", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testDownload() throws IOException, URISyntaxException {
        ResponseEntity<DownloadFileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/download", HttpMethod.GET, null, DownloadFileResponse.class);

        System.out.println(response.getBody());
    }
}
