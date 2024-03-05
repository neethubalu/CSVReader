package com.gerimedica.reader.controller;

import com.gerimedica.reader.entity.DataModel;
import com.gerimedica.reader.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv/reader/")
@RequiredArgsConstructor
@Slf4j
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping("fetch/all")
    ResponseEntity<Page<DataModel>> fetchAllData(@RequestParam(required = false) String code,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        log.info("Received request for fetching all Data");
        Pageable pageableData = PageRequest.of(page, size);
        return ResponseEntity.ok().body(readerService.fetchAllData(pageableData));
    }

    @GetMapping("fetch/{code}")
    ResponseEntity<DataModel> fetchByCode(@PathVariable String code
    ) throws Exception {
        log.info("Received request for fetching all Data");
        return ResponseEntity.ok().body(readerService.fetchDataByCode(code));
    }

    @PostMapping("upload")
    ResponseEntity<String> uploadData(@RequestBody MultipartFile file
    ) throws Exception {
        log.info("Received request for uploading Data");
        return ResponseEntity.ok().body(readerService.uploadData(file));
    }

    @DeleteMapping("delete")
    ResponseEntity<String> deleteAllData(
    ) throws Exception {
        log.info("Received request for deleting Data");
        return ResponseEntity.ok().body(readerService.deleteAllData());
    }


}
