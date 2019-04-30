package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.CommentDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentDO, Long> {
}
