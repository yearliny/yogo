package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepository extends JpaRepository<CommentDO, Long> {
    Long countByStatus(CommentStatusEnum status);

    Long countByOwner(UserDO user);

    Page<CommentDO> findAllByStatusInOrderByCreatedDesc(Set<CommentStatusEnum> status, Pageable pageable);
}
