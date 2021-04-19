package com.example.schedulingtasks.scheduler;

import com.example.schedulingtasks.data.Report;
import com.example.schedulingtasks.data.ReportRepository;
import com.example.schedulingtasks.data.ReportStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedRateComponent {

    private final TaskScheduler taskScheduler;
    private final ReportRepository reportRepository;

    public FixedRateComponent(final TaskScheduler taskScheduler, final ReportRepository reportRepository) {
        this.taskScheduler = taskScheduler;
        this.reportRepository = reportRepository;
//        scheduleTask();
    }

    public void scheduleTask() {
        taskScheduler.scheduleAtFixedRate(generateReport(), 5000L);
    }


    private Runnable generateReport() {
        return () -> {
            final List<Report> reports = reportRepository.findAllByStatus(ReportStatus.IN_PROGRESS);
            reports.forEach(report -> report.setStatus(ReportStatus.FAILED));
            reportRepository.saveAll(reports);
        };
    }
}


