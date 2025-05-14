package org.ame.yan.shabby.service;

import org.ame.yan.shabby.model.dto.CommentAdminDTO;
import org.ame.yan.shabby.model.dto.CommentDTO;
import org.ame.yan.shabby.model.dto.ReplyDTO;
import org.ame.yan.shabby.entity.Comment;
import org.ame.yan.shabby.model.vo.CommentVO;
import org.ame.yan.shabby.model.vo.ConditionVO;
import org.ame.yan.shabby.model.dto.PageResultDTO;
import org.ame.yan.shabby.model.vo.ReviewVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    PageResultDTO<CommentDTO> listComments(CommentVO commentVO);

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();

    PageResultDTO<CommentAdminDTO> listCommentsAdmin(ConditionVO conditionVO);

    void updateCommentsReview(ReviewVO reviewVO);

}
