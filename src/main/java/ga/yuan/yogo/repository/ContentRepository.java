package ga.yuan.yogo.repository;

import ga.yuan.yogo.model.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Page<Content> findAllByOrderByCreatedDesc(Pageable pageable);

    Optional<Content> findByCid(Long id);
}
