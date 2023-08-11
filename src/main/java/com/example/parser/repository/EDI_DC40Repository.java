package com.example.parser.repository;

import com.example.parser.entity.EDI_DC40;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EDI_DC40Repository extends JpaRepository<EDI_DC40, String> {


    @Query(value = "SELECT edi.* FROM edi_dc40 edi INNER JOIN e1wpa01 e1 ON edi.rcvprn = e1.filiale WHERE e1.mhdhb < 50", nativeQuery = true)
    List<EDI_DC40> findAllMhdhbLessThan50();

}
