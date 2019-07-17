package pl.akademiakodu.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.reporting.model.entities.Comment;

public interface CommentRepository extends
        JpaRepository<Comment,Integer> {
}
