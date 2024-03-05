package com.gerimedica.reader.entity;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DataModel {
    @CsvBindByName()
    private String source;
    @CsvBindByName()
    private String codeListCode;
    @Id
    @Column(unique = true)
    @CsvBindByName()
    private String code;
    @CsvBindByName()
    private String displayValue;
    @CsvBindByName()
    private String longDescription;
    @CsvBindByName(required = false)
    @CsvDate("mm-DD-yyyy")
    private Date fromDate;
    @CsvBindByName(required = false)
    @CsvDate("mm-DD-yyyy")
    private Date toDate;
    @CsvBindByName(required = false)
    private String sortingPriority;
}
