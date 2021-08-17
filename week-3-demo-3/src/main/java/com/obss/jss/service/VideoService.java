package com.obss.jss.service;

import com.obss.jss.core.cache.ContentCache;
import com.obss.jss.model.content.Content;
import com.obss.jss.model.content.VideoContent;
import org.springframework.stereotype.Service;

@Service
public interface VideoService {
    Content createNewVideoContent(VideoContent videoContent);

    VideoContent deleteVideoContent(Long id);

    String getVideoUrl(Long id);

    void updateCurrentTime(Long id, Long duration);

    ContentCache getContentCache();
}
