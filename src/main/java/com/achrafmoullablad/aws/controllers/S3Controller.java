package com.achrafmoullablad.aws.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.achrafmoullablad.aws.services.S3Service;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = this.s3Service.uploadFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] data = this.s3Service.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(data);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        List<String> files = this.s3Service.listFiles();
        return ResponseEntity.ok(files);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    	this.s3Service.deleteFile(fileName);
        return ResponseEntity.ok("File deleted successfully: " + fileName);
    }
}