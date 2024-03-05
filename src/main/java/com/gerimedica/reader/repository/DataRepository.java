package com.gerimedica.reader.repository;

import com.gerimedica.reader.entity.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<DataModel, String> {

}
