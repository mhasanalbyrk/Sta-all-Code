package com.obss.jss.core.cache.impl;

import com.obss.jss.core.cache.ContentCache;
import com.obss.jss.model.content.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryContentCache implements ContentCache {
    public static final Logger LOGGER = LoggerFactory.getLogger(InMemoryContentCache.class);

    private final Map<Long, Content> contentMap = new ConcurrentHashMap<>();

    @Override
    public Content getContent(Long id) {
        if (contentMap.get(id) == null) {
            LOGGER.info("Content not found:{}", id);
            return null;
        } else {
            LOGGER.info("Content returned id:{}", id);
            return contentMap.get(id);
        }


    }

    @Override
    public Content addContent(Content content) {
        int id = (int) (Math.random() * 1000);

        if (content != null) {
            content.setId((long) id);
            contentMap.put(Integer.toUnsignedLong(id), content);
            LOGGER.info("Content put into cache id:{}", content.getId());
            return content;

        } else {

            LOGGER.info("Content is null:{}", "");
            return null;
        }
    }

    @Override
    public Content removeContent(Long id) {
        if (contentMap.remove(id) == null) {
            LOGGER.info("Content does not exists id:{}", id);
        } else {
            contentMap.remove(id);
            LOGGER.info("Content removed id:{}", id);
        }

        return null;
    }

    @Override
    public void printCacheSize() {

        System.out.println("Cache size is " + contentMap.size());
    }
}
