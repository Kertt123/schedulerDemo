package com.example.schedulingtasks.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    private LocalDateTime createDate;

    protected Report() {
    }

    public Report(final String name, final ReportStatus status) {
        this.name = name;
        this.status = status;
        createDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(final ReportStatus status) {
        this.status = status;
    }
}