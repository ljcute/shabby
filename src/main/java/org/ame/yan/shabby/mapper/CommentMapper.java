package org.ame.yan.shabby.mapper;

import org.ame.yan.shabby.model.dto.CommentAdminDTO;
import org.ame.yan.shabby.model.dto.CommentCountDTO;
import org.ame.yan.shabby.model.dto.CommentDTO;
import org.ame.yan.shabby.model.dto.ReplyDTO;
import org.ame.yan.shabby.entity.Comment;
import org.ame.yan.shabby.model.vo.CommentVO;
import org.ame.yan.shabby.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> listComments(@Param("current") Long current, @Param("size") Long size, @Param("commentVO") CommentVO commentVO);

    List<ReplyDTO> listReplies(@Param("commentIds") List<Integer> commentIdList);

    List<CommentDTO> listTopSixComments();

    Integer countComments(@Param("conditionVO") ConditionVO conditionVO);

    List<CommentAdminDTO> listCommentsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    List<CommentCountDTO> listCommentCountByTypeAndTopicIds(@Param("type") Integer type, @Param("topicIds") List<Integer> topicIds);

    CommentCountDTO listCommentCountByTypeAndTopicId(@Param("type") Integer type, @Param("topicId") Integer topicId);

}
