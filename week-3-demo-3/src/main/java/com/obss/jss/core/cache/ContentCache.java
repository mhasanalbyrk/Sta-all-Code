package com.obss.jss.core.cache;

import com.obss.jss.model.content.Content;
import org.springframework.stereotype.Component;


public interface ContentCache {

    Content getContent(Long id);

    Content addContent(Content content);

    Content removeContent(Long id);

    void printCacheSize();
}
