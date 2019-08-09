package com.example.pload2.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.pload2.model.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.pload2.payload.Response;
import com.example.pload2.services.FileStorageService;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(dbFile.getFileName())
            .toUriString();

        return new Response(dbFile.getFileName(), fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }

    //to get all files
    @GetMapping("/files")
    public List<DBFile> getFiles(){
        return fileStorageService.getFiles();
    }

    @GetMapping(value = "/videosrc/{id}", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource videoSource(@PathVariable("id") String id) {
        DBFile dbFile = fileStorageService.getFile(id);
        return new FileSystemResource(new File("./uploads/"+dbFile.getFileName()));
    }
}
