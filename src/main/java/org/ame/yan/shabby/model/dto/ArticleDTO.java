package org.ame.yan.shabby.model.dto;

import org.ame.yan.shabby.entity.Tag;
import org.ame.yan.shabby.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Integer id;

    private UserInfo author;

    private String categoryName;

    private String articleCover;

    private String articleTitle;

    private String articleContent;

    private Integer isTop;

    private Integer isFeatured;

    private Integer isDelete;

    private Integer status;

    private List<Tag> tags;

    private Integer viewCount;

    private Integer type;

    private String originalUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private ArticleCardDTO preArticleCard;

    private ArticleCardDTO nextArticleCard;

}
