package com.example.schedulingtasks.scheduler;

import com.example.schedulingtasks.data.Report;
import com.example.schedulingtasks.data.ReportRepository;
import com.example.schedulingtasks.data.ReportStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FixedDelayComponent {

    private final TaskScheduler taskScheduler;
    private final ReportRepository reportRepository;

    public FixedDelayComponent(final TaskScheduler taskScheduler, final ReportRepository reportRepository) {
        this.taskScheduler = taskScheduler;
        this.reportRepository = reportRepository;
//        scheduleTask();
    }

    public void scheduleTask() {
        taskScheduler.scheduleWithFixedDelay(generateReport(), 5000L);
        taskScheduler.scheduleWithFixedDelay(generateReport2(), 6000L);
    }


    private Runnable generateReport() {
        return () -> {
            final Report report = new Report("Report_1_" + UUID.randomUUID().toString(), ReportStatus.CREATED);
            reportRepository.save(report);
        };
    }

    private Runnable generateReport2() {
        return () -> {
            final Report report = new Report("Report_2_" + UUID.randomUUID().toString(), ReportStatus.CREATED);
            reportRepository.save(report);
        };
    }
}


