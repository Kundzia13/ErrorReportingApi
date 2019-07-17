package pl.akademiakodu.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.reporting.model.entities.Report;

public interface ReportRepository extends
        JpaRepository<Report, Integer> {

}
