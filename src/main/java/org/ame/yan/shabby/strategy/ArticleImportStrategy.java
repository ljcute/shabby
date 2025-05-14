package org.ame.yan.shabby.strategy;

import org.springframework.web.multipart.MultipartFile;

public interface ArticleImportStrategy {

    void importArticles(MultipartFile file);

}
