package com.obss.jss.service;

import com.obss.jss.core.cache.ContentCache;
import com.obss.jss.model.content.ElearningContent;
import org.springframework.stereotype.Service;

@Service
public interface ElearningService {

    boolean createNewElearningContent(ElearningContent videoContent);

    ElearningContent deleteElearningContent(ElearningContent videoContent);

    String getElearningUrl(Long id);

    ContentCache getContentCache();

}
