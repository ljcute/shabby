package org.ame.yan.shabby.strategy;

import org.ame.yan.shabby.model.dto.ArticleSearchDTO;

import java.util.List;

public interface SearchStrategy {

    List<ArticleSearchDTO> searchArticle(String keywords);

}
