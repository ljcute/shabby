package org.ame.yan.shabby.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopAndFeaturedArticlesDTO {

    private ArticleCardDTO topArticle;

    private List<ArticleCardDTO> featuredArticles;
}
