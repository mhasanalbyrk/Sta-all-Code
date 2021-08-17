package com.obss.jss.service.impl;

import com.obss.jss.core.cache.ContentCache;
import com.obss.jss.model.content.Content;
import com.obss.jss.model.content.VideoContent;
import com.obss.jss.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {


    private final ContentCache contentCache;

    @Autowired
    public VideoServiceImpl(ContentCache contentCache) {
        this.contentCache = contentCache;
    }

    @Override
    public Content createNewVideoContent(VideoContent videoContent) {

        return contentCache.addContent(videoContent);
    }

    @Override
    public VideoContent deleteVideoContent(Long id) {

        return (VideoContent) contentCache.removeContent(id);
    }

    @Override
    public String getVideoUrl(Long id) {
        Content videoContent = contentCache.getContent(id);

        return "VIDEO-URL";
    }

    @Override
    public void updateCurrentTime(Long id, Long duration) {

    }

    @Override
    public ContentCache getContentCache() {

        return contentCache;
    }
}
