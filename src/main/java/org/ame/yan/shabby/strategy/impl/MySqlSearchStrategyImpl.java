package org.ame.yan.shabby.strategy.impl;

import org.ame.yan.shabby.entity.Article;
import org.ame.yan.shabby.mapper.ArticleMapper;
import org.ame.yan.shabby.model.dto.ArticleSearchDTO;
import org.ame.yan.shabby.strategy.SearchStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.ame.yan.shabby.constant.CommonConstant.*;
import static org.ame.yan.shabby.enums.ArticleStatusEnum.PUBLIC;

@Service("mySqlSearchStrategyImpl")
public class MySqlSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleSearchDTO> searchArticle(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            return new ArrayList<>();
        }
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, FALSE)
                .eq(Article::getStatus, PUBLIC.getStatus())
                .and(i -> i.like(Article::getArticleTitle, keywords)
                        .or()
                        .like(Article::getArticleContent, keywords)));
        return articles.stream().map(item -> {
                    boolean isLowerCase = true;
                    String articleContent = item.getArticleContent();
                    int contentIndex = item.getArticleContent().indexOf(keywords.toLowerCase());
                    if (contentIndex == -1) {
                        contentIndex = item.getArticleContent().indexOf(keywords.toUpperCase());
                        if (contentIndex != -1) {
                            isLowerCase = false;
                        }
                    }
                    if (contentIndex != -1) {
                        int preIndex = contentIndex > 15 ? contentIndex - 15 : 0;
                        String preText = item.getArticleContent().substring(preIndex, contentIndex);
                        int last = contentIndex + keywords.length();
                        int postLength = item.getArticleContent().length() - last;
                        int postIndex = postLength > 35 ? last + 35 : last + postLength;
                        String postText = item.getArticleContent().substring(contentIndex, postIndex);
                        if (isLowerCase) {
                            articleContent = (preText + postText).replaceAll(keywords.toLowerCase(), PRE_TAG + keywords.toLowerCase() + POST_TAG);
                        } else {
                            articleContent = (preText + postText).replaceAll(keywords.toUpperCase(), PRE_TAG + keywords.toUpperCase() + POST_TAG);
                        }
                    } else {
                        return null;
                    }
                    isLowerCase = true;
                    int titleIndex = item.getArticleTitle().indexOf(keywords.toLowerCase());
                    if (titleIndex == -1) {
                        titleIndex = item.getArticleTitle().indexOf(keywords.toUpperCase());
                        if (titleIndex != -1) {
                            isLowerCase = false;
                        }
                    }
                    String articleTitle;
                    if (isLowerCase) {
                        articleTitle = item.getArticleTitle().replaceAll(keywords.toLowerCase(), PRE_TAG + keywords.toLowerCase() + POST_TAG);
                    } else {
                        articleTitle = item.getArticleTitle().replaceAll(keywords.toUpperCase(), PRE_TAG + keywords.toUpperCase() + POST_TAG);
                    }
                    return ArticleSearchDTO.builder()
                            .id(item.getId())
                            .articleTitle(articleTitle)
                            .articleContent(articleContent)
                            .build();
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
