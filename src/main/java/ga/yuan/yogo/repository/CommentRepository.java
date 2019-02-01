package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCoid(Long id);
}
