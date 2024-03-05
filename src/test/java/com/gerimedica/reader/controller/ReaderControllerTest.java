package com.gerimedica.reader.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class ReaderControllerTest extends IntegrationTest {

    @Test
    void fetchAll() throws Exception {
        mvc.perform((get("/csv/reader/fetch/all")
                        .contentType(MediaType.APPLICATION_JSON)
                ))
                .andExpect(status().isOk());
    }


    @Test
    void upload() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "data.csv",
                MediaType.TEXT_PLAIN_VALUE,
                "test,csv,content".getBytes()
        );

        mvc.perform(
                        multipart("/csv/reader/upload")
                                .file(file)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Successfully uploaded file")));
    }

    @Test
    void fetchByCode() throws Exception {
        mvc.perform((post("/csv/reader/fetch/Type")
                        .contentType(MediaType.APPLICATION_JSON)
                ))
                .andExpect(status().isInternalServerError());
    }


}
