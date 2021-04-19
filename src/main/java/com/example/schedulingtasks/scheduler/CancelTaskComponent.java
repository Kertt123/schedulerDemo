package com.example.schedulingtasks.scheduler;

import com.example.schedulingtasks.data.ReportRepository;
import com.example.schedulingtasks.data.ReportStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class CancelTaskComponent {

    private final Map<Long, ScheduledFuture<?>> tasks;
    private final TaskScheduler taskScheduler;
    private final ReportRepository reportRepository;

    public CancelTaskComponent(final TaskScheduler taskScheduler, final ReportRepository reportRepository) {
        this.taskScheduler = taskScheduler;
        this.reportRepository = reportRepository;
        tasks = new HashMap<>();
        scheduleTask();
    }

    public void scheduleTask() {
        final ScheduledFuture<?> scheduledFutureTask1 = taskScheduler.scheduleAtFixedRate(markReportAsDone(1L), 5000L);
        final ScheduledFuture<?> scheduledFutureTask2 = taskScheduler.scheduleAtFixedRate(markReportAsDone(2L), 5000L);

        tasks.put(1L, scheduledFutureTask1);
        tasks.put(2L, scheduledFutureTask2);
    }

    private Runnable markReportAsDone(final Long reportId) {
        return () -> {
            System.out.printf("Checking status of report with id: %d%n", reportId);
            reportRepository.findById(reportId)
                    .ifPresent(report -> {
                        if(report.getStatus() == ReportStatus.IN_PROGRESS){
                            report.setStatus(ReportStatus.DONE);
                            reportRepository.save(report);
                            tasks.get(reportId).cancel(true);
                            System.out.printf("Report with id: %d marked as DONE%n", reportId);
                        }
                    });
        };
    }

}


