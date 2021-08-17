package com.obss.jss.service.impl;

import com.obss.jss.core.cache.ContentCache;
import com.obss.jss.model.content.ElearningContent;
import com.obss.jss.service.ElearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElearningServiceImpl implements ElearningService {

    private final ContentCache contentCache;

    @Autowired
    public ElearningServiceImpl(ContentCache contentCache) {
        this.contentCache = contentCache;
    }

    @Override
    public boolean createNewElearningContent(ElearningContent videoContent) {

        return false;
    }

    @Override
    public ElearningContent deleteElearningContent(ElearningContent videoContent) {
        return null;
    }

    @Override
    public String getElearningUrl(Long id) {
        return null;
    }

    @Override
    public ContentCache getContentCache() {
        return null;
    }
}
