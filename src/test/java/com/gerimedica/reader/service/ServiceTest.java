package com.gerimedica.reader.service;

import com.gerimedica.reader.entity.DataModel;
import com.gerimedica.reader.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

 class ServiceTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private ReaderServiceImpl readerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAirports() throws Exception {
        Page<DataModel> mockPage = mock(Page.class);
        when(dataRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        Page<DataModel> result = readerService.fetchAllData(Pageable.unpaged());

        assertNotNull(result);
        verify(dataRepository, times(1)).findAll(any(Pageable.class));
    }
}
