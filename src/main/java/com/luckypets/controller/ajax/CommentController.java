package com.luckypets.controller.ajax;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;
import com.luckypets.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/clinics/{clinicId}/comments", method = RequestMethod.POST)
    public ClinicComment addClinicComment(
            @PathVariable long clinicId,
            @RequestBody ClinicComment comment) {

        commentService.saveClinicComment(clinicId, comment);

        return comment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clinics/{clinicId}/comments")
    public List<ClinicComment> getClinicComments(@PathVariable("clinicId") int clinicId) {
        return commentService.getClinicComments(clinicId);
    }


    @RequestMapping(value = "/adverts/{advertId}/comments",
            method = RequestMethod.POST)
    public AdvertComment addAdvertComment(
            @PathVariable("advertId") long advertId,
            @RequestBody AdvertComment comment) {

        commentService.saveAdvertComment(advertId, comment);

        return comment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adverts/{advertId}/comments")
    public List<AdvertComment> getAdvertComments(@PathVariable int advertId) {
        return commentService.getAdvertComments(advertId);
    }
}
