package com.gerimedica.reader.service;

import com.gerimedica.reader.entity.DataModel;
import com.gerimedica.reader.exception.DatabaseException;
import com.gerimedica.reader.exception.EmptyFileException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReaderService {
    Page<DataModel> fetchAllData(Pageable pageableData) throws DatabaseException;

    String uploadData(MultipartFile file) throws IOException, EmptyFileException;

    String deleteAllData() throws DatabaseException;

    DataModel fetchDataByCode(String code) throws EmptyFileException;
}
