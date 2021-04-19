package com.example.schedulingtasks.annotation;

import com.example.schedulingtasks.data.Report;
import com.example.schedulingtasks.data.ReportRepository;
import com.example.schedulingtasks.data.ReportStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
@ConditionalOnProperty(value = "scheduling")
public class ScheduledComponent {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final ReportRepository reportRepository;

    public ScheduledComponent(final ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

//    @Scheduled(fixedDelay = 5000, initialDelay = 25000)
//    public void checkStatus() {
//        final List<Report> reports = reportRepository.findAllByStatus(ReportStatus.IN_PROGRESS);
//        reports.forEach(report -> report.setStatus(ReportStatus.FAILED));
//        reportRepository.saveAll(reports);
//    }

//    @Scheduled(fixedRate = 2000, initialDelay = 25000)
//    public void generateReport() {
//        final Report report = new Report("Report_" + UUID.randomUUID().toString(), ReportStatus.CREATED);
//        reportRepository.save(report);
//    }

//    @Async
//    @Scheduled(fixedRate = 2000)
//    public void ratedTaskAsync() throws InterruptedException {
//        System.out.println("Task start date: " + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
//        Thread.sleep(3000);
//        System.out.println("Task end date: " + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
//    }

//    @Scheduled(cron = "0,30 * * ? * *")
//    public void generateReportCron() {
//        final Report report = new Report("Report_" + UUID.randomUUID().toString(), ReportStatus.CREATED);
//        reportRepository.save(report);
//    }
}
