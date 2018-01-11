package com.mygaienko.api;

import com.mygaienko.api.dto.DownloadFileResponse;
import com.mygaienko.api.dto.ErrorResponse;
import com.mygaienko.api.dto.FileRequest;
import com.mygaienko.api.dto.FileResponse;
import com.mygaienko.api.dto.GenerateFileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/file")
@Api(value="file storage", description="file storage description")
@Slf4j
public class FileController {

    private final ServletContext servletContext;

    @Autowired
    public FileController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "upload file", response = FileResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful upload"),
            @ApiResponse(code = 403, message = "Not found")
    })
    public FileResponse uploadFile(@ModelAttribute FileRequest request) throws IOException {
        System.out.println(Arrays.toString(request.getFile().getBytes()));
//        System.out.println(Arrays.toString(request.getFile()));
        System.out.println(request.getSystemName());
        return new FileResponse("uploaded");
    }

    @PostMapping(value = "/generate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "generate file", response = FileResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful generating"),
            @ApiResponse(code = 404, message = "Not Found custom")
    })
    public FileResponse generateFile(@RequestBody GenerateFileRequest request) {
        log.info("Generating " + request);
        return new FileResponse("generated");
    }

    @PostMapping(value = "/uploadSimple", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public FileResponse uploadFileSimple(MultipartFile file) throws IOException {
        System.out.println(Arrays.toString(file.getBytes()));
        return new FileResponse("uploadedSimple");
    }

    @GetMapping(value = "/download", produces = APPLICATION_JSON_VALUE)
    public DownloadFileResponse download() throws IOException {
        byte[] fileBytes = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("pdf.pdf"));
        return new DownloadFileResponse("fileName", fileBytes);
    }

    @GetMapping(value = "/download/{error}", produces = {APPLICATION_PDF_VALUE, APPLICATION_JSON_VALUE})
    public byte[] downloadSimple(@PathVariable String error) throws IOException {
        if ("ERROR".equalsIgnoreCase(error)) {
            throw new RuntimeException("custom error ololo");
        }

        return IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("pdf.pdf"));
    }

    @GetMapping(value = "/get", produces = APPLICATION_JSON_VALUE)
    public FileResponse get() {
        return new FileResponse("get");
    }

    @GetMapping(value = "/getError", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse getWithError() {
        return new ErrorResponse("service1", "errorMsg");
    }

    @DeleteMapping(value = "/delete", produces = APPLICATION_JSON_VALUE)
    public FileResponse delete() {
        return new FileResponse("delete");
    }

}
