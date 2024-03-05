package com.gerimedica.reader.service;

import com.gerimedica.reader.entity.DataModel;
import com.gerimedica.reader.exception.DatabaseException;
import com.gerimedica.reader.exception.EmptyFileException;
import com.gerimedica.reader.repository.DataRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static com.gerimedica.reader.utility.ApiConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReaderServiceImpl implements ReaderService {
    private final DataRepository dataRepository;

    @Override
    public Page<DataModel> fetchAllData(Pageable pageableData) throws DatabaseException {
        try {
            return dataRepository.findAll(pageableData);
        } catch (Exception e) {
            throw new DatabaseException("");
        }
    }

    @Override
    public String uploadData(MultipartFile file) throws IOException, EmptyFileException {
        if (file.isEmpty()) {
            throw new EmptyFileException(EMPTY_RESPONSE);
        }
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            ColumnPositionMappingStrategy<DataModel> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(DataModel.class);
            strategy.setColumnMapping(SOURCE, CODELISTCODE, CODE, DISPLAYVALUE, LONGDESCRIPTION, FROMDATE, TODATE, SORTINGPRIORITY);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            CsvToBean<DataModel> csvToBean = new CsvToBeanBuilder<DataModel>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            Iterator<DataModel> dataModelIterator = csvToBean.iterator();
            while (dataModelIterator.hasNext()) {
                DataModel dataModel = dataModelIterator.next();
                dataRepository.save(dataModel);
            }
            return SUCCESS_RESPONSE;
        }
    }

    @Override
    public String deleteAllData() throws DatabaseException {
        try {
            dataRepository.deleteAll();
        } catch (Exception e) {
            throw new DatabaseException("");
        }
        return DELETE_RESPONSE;
    }

    @Override
    public DataModel fetchDataByCode(String code) throws EmptyFileException {
        return dataRepository.findById(code).orElseThrow(() -> new EmptyFileException(""));
    }
}
