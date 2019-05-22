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
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDO save(CommentDO comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentCounterVO countComment() {
        CommentCounterVO counter = new CommentCounterVO();
        counter.setApproved(commentRepository.countByStatus(CommentStatusEnum.APPROVE));
        counter.setHold(commentRepository.countByStatus(CommentStatusEnum.HOLD));
        counter.setSpam(commentRepository.countByStatus(CommentStatusEnum.SPAM));
        counter.setTrash(commentRepository.countByStatus(CommentStatusEnum.TRASH));
        return counter;
    }

    @Override
    public Page<CommentDO> listRecentComment(Set<CommentStatusEnum> status, Pageable pageable) {
        return commentRepository.findAllByStatusInOrderByCreatedDesc(status, pageable);
    }

    @Override
    public Page<CommentDO> listRecentComment(CommentStatusEnum statusEnum, Pageable pageable) {
        Set<CommentStatusEnum> status = Set.of(statusEnum);
        return commentRepository.findAllByStatusInOrderByCreatedDesc(status, pageable);
    }

}