package com.example.schedulingtasks.scheduler;

import com.example.schedulingtasks.data.Report;
import com.example.schedulingtasks.data.ReportRepository;
import com.example.schedulingtasks.data.ReportStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CronTriggerComponent {

    private final TaskScheduler taskScheduler;
    private final ReportRepository reportRepository;

    public CronTriggerComponent(final TaskScheduler taskScheduler, final ReportRepository reportRepository) {
        this.taskScheduler = taskScheduler;
        this.reportRepository = reportRepository;
//        scheduleTask();
    }


    public void scheduleTask() {
        final CronTrigger cronTrigger = new CronTrigger("0,30 * * ? * *");
        taskScheduler.schedule(generateReport(), cronTrigger);
    }


    private Runnable generateReport() {
        return () -> {
            final Report report = new Report("Report_" + UUID.randomUUID().toString(), ReportStatus.CREATED);
            reportRepository.save(report);
        };
    }

}


