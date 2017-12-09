package com.mygaienko.api;

import com.mygaienko.api.dto.FileRequest;
import com.mygaienko.api.dto.FileResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public FileResponse uploadFile(@ModelAttribute FileRequest request) throws IOException {
        System.out.println(Arrays.toString(request.getFile().getBytes()));
        System.out.println(request.getSystemName());
        return new FileResponse("uploaded");
    }

    @PostMapping(value = "/uploadSimple", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public FileResponse uploadFileSimple(MultipartFile file) throws IOException {
        System.out.println(Arrays.toString(file.getBytes()));
        return new FileResponse("uploadedSimple");
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
