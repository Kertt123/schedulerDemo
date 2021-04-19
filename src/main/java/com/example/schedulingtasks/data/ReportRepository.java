package com.example.schedulingtasks.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAllByStatus(final ReportStatus status);
}