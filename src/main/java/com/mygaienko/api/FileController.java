package com.mygaienko.api;

import com.mygaienko.api.dto.DownloadFileResponse;
import com.mygaienko.api.dto.FileRequest;
import com.mygaienko.api.dto.FileResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/file")
public class FileController {

    private final ServletContext servletContext;

    @Autowired
    public FileController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public FileResponse uploadFile(@ModelAttribute FileRequest request) throws IOException {
        System.out.println(Arrays.toString(request.getFile().getBytes()));
//        System.out.println(Arrays.toString(request.getFile()));
        System.out.println(request.getSystemName());
        return new FileResponse("uploaded");
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

    @GetMapping(value = "/get", produces = APPLICATION_JSON_VALUE)
    public FileResponse get() {
        return new FileResponse("get");
    }

    @DeleteMapping(value = "/delete", produces = APPLICATION_JSON_VALUE)
    public FileResponse delete() {
        return new FileResponse("delete");
    }

}
