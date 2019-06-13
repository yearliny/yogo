package ga.yuan.yogo.controller;

import ga.yuan.yogo.model.entity.CommentDO;
import ga.yuan.yogo.model.enums.CommentStatusEnum;
import ga.yuan.yogo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/yg-admin")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @GetMapping("/commentStatus")
    public String commentStatus(@RequestParam Long id, @RequestParam CommentStatusEnum status) {
        Optional<CommentDO> comment = commentService.findById(id);
        comment.ifPresent(c -> c.setStatus(status));
        comment.ifPresent(commentService::save);
        return "success";
    }
}
