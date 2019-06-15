package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.model.vo.CommentCounterVO;
import ga.yuan.yogo.repository.CommentRepository;
import ga.yuan.yogo.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<CommentDO> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public CommentDO save(CommentDO comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentCounterVO countComment() {
        return new CommentCounterVO(
                commentRepository.countByStatus(CommentStatusEnum.HOLD),
                commentRepository.countByStatus(CommentStatusEnum.APPROVE),
                commentRepository.countByStatus(CommentStatusEnum.SPAM),
                commentRepository.countByStatus(CommentStatusEnum.TRASH));
    }

    @Override
    public Page<CommentDO> listRecentComment(Set<CommentStatusEnum> status, Pageable pageable) {
        return commentRepository.findAllByStatusInOrderByCreatedDesc(status, pageable);
    }

}
