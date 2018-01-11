package com.mygaienko;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygaienko.api.dto.DownloadFileResponse;
import com.mygaienko.api.dto.ErrorResponse;
import com.mygaienko.api.dto.FileResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public void testGetError() {
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:18080/file/getError", HttpMethod.GET, requestEntity, String.class);
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

    private void sendFile(final String fileName) throws URISyntaxException, IOException {
        URL resource = getClass().getResource(fileName);

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
//        fileRequest.add("file", new FileSystemResource(new File(resource.getPath())));
        byte[] content = Files.readAllBytes(Paths.get(resource.toURI()));
        fileRequest.add("file", new ByteArrayResource(content) {
            @Override
            public String getFilename() {
                return fileName;
            }
        });
        fileRequest.add("systemName", "sysName");

//        FileRequest fileRequest = new FileRequest();
//        fileRequest.setSystemName("sysName");
//        File file = new File(resource.toURI());
//        FileItem fileItem = new DiskFileItem("file", "pdf", true, fileName, (int) file.length(), file.getParentFile());
//        fileItem.getOutputStream();
//        fileRequest.setFile(new CommonsMultipartFile(fileItem));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:18080/file/upload", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testPostToMicroService() throws IOException, URISyntaxException {
        URL resource = getClass().getResource("/pdf.pdf");

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
        fileRequest.add("documentContent", new FileSystemResource(new File(resource.getPath())));
        fileRequest.add("systemName", "sysName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer d7599ba3-5b87-4b13-8eaa-eb5a7c38437b");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:9022/file", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testGenerateGenericDocument() throws IOException, URISyntaxException {
        Map<String, String> fileRequest = new HashMap<>();
        fileRequest.put("key1", "value1");
        fileRequest.put("key2", "value2");

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer 063e8e92-e237-4f2f-96e6-3a8ec3be9abc");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:9021/document/generateGeneric/infomationenFuerVerbraucherkrediteSecci", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testGetConcatenatedDocByDocumentId() throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer 063e8e92-e237-4f2f-96e6-3a8ec3be9abc");
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_PDF));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(
                "http://localhost:9021/document/concatenated/byDocId/72", HttpMethod.GET, requestEntity, byte[].class);

        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test
    public void testSimplePostToMicroService() throws IOException, URISyntaxException {
        URL resource = getClass().getResource("/pdf.pdf");

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
        fileRequest.add("file", new FileSystemResource(new File(resource.getPath())));
        fileRequest.add("systemName", "sysName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer d7599ba3-5b87-4b13-8eaa-eb5a7c38437b");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:9022/file/simple", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testPostSimple() throws IOException, URISyntaxException {
        URL resource = getClass().getResource("/pdf.pdf");
//        MultipartFile file = new MockMultipartFile("pdf.pdf", Files.readAllBytes(Paths.get(resource.toURI())));
        /*FileRequest fileRequest = new FileRequest("sysName", file);
        HttpEntity<FileRequest> requestEntity = new HttpEntity<>(fileRequest);*/
        String fileName1 = "fileName1";

        MultiValueMap<String, Object> fileRequest = new LinkedMultiValueMap<>();
//        fileRequest.add("file", new FileSystemResource(new File(resource.getPath())));
        fileRequest.add("file", new ByteArrayResource(Files.readAllBytes(Paths.get(resource.toURI()))){
            @Override
            public String getFilename() {
                return fileName1;
            }
        });
//        fileRequest.add("file", Files.readAllBytes(Paths.get(resource.toURI())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(fileRequest, headers);
        ResponseEntity<FileResponse> response = restTemplate.exchange(
                "http://localhost:18080/file/uploadSimple", HttpMethod.POST, requestEntity, FileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testDownload() throws IOException, URISyntaxException {
        ResponseEntity<DownloadFileResponse> response = restTemplate.exchange(
                "http://localhost:8080/file/download", HttpMethod.GET, null, DownloadFileResponse.class);

        System.out.println(response.getBody());
    }

    @Test
    public void testDownloadSimpleError() throws IOException, URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_PDF));
//        httpHeaders.setAccept(Collections.singletonList(MediaType.ALL));
//        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "http://localhost:18080/file/download/error", HttpMethod.GET, new HttpEntity<>(httpHeaders), byte[].class);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class);

            System.out.println("Deserialized errorResponse: " + errorResponse);
        }

    }

    @Test
    public void testDownloadSimple() throws IOException, URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_PDF));

        ResponseEntity<byte[]> response = restTemplate.exchange(
                "http://localhost:18080/file/download/simple", HttpMethod.GET, new HttpEntity<>(httpHeaders), byte[].class);

        System.out.println(response);
    }

    private class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            System.out.println("handled ololo");
        }
    }
}
