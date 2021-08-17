package com.obss.jss.controller;

import com.obss.jss.model.content.Content;
import com.obss.jss.model.content.VideoContent;
import com.obss.jss.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping("/createVideo")
    public ResponseEntity<VideoContent> createVideo(@RequestParam("name") String name) {

        Content videoContent = new VideoContent(name);
        videoService.createNewVideoContent((VideoContent) videoContent);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<VideoContent> responseEntity = new ResponseEntity<VideoContent>((VideoContent) videoContent, headers, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public VideoContent getVideo(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            return (VideoContent) videoService.getContentCache().getContent(Long.parseLong(id));
        } catch (ResponseStatusException e) {

            return null;
        }
    }

    @DeleteMapping("/{id}}")
    public VideoContent DeleteVideo(@PathVariable("id") String id) {

        videoService.deleteVideoContent(Long.parseLong(id));
        return null;
    }

//    @GetMapping(value = "/{id}")
//    public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
//        try {
//            Foo resourceById = RestPreconditions.checkFound(service.findOne(id));
//
//            eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
//            return resourceById;
//        }
//        catch (MyResourceNotFoundException exc) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
//        }
//    }
}
