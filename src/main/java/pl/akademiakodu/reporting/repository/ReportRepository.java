package pl.akademiakodu.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.reporting.model.entities.Report;
import pl.akademiakodu.reporting.model.entities.Status;

public interface ReportRepository extends
        JpaRepository<Report, Integer> {

    Iterable<Report> findByStatus(Status status);
}
