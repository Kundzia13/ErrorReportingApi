package pl.akademiakodu.reporting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.reporting.model.Comment;

public interface CommentRepository extends
        CrudRepository<Comment,Integer> {
}
