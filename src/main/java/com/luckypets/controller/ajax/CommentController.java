package com.luckypets.controller.ajax;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;
import com.luckypets.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/clinics/{clinicId}/comments/{commentText}/{commentMark}", method = RequestMethod.POST)
    public ClinicComment addClinicComment(
            @PathVariable long clinicId,
            @PathVariable int commentMark,
            @PathVariable String commentText) {


        ClinicComment comment = new ClinicComment();
        comment.setMark(commentMark);
        comment.setText(commentText);

        commentService.saveClinicComment(clinicId, comment);

        return comment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clinics/{clinicId}/comments")
    public List<ClinicComment> getClinicComments(@PathVariable("clinicId") int clinicId) {
        return commentService.getClinicComments(clinicId);
    }

    @RequestMapping(value = "/adverts/{advertId}/comments/{commentText}", method = RequestMethod.POST)
    public AdvertComment addAdvertComment(
            @PathVariable("advertId") long advertId,
            @PathVariable String commentText) {
        AdvertComment comment = new AdvertComment();
        comment.setText(commentText);

        commentService.saveAdvertComment(advertId, comment);

        return comment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adverts/{advertId}/comments")
    public List<AdvertComment> getAdvertComments(@PathVariable int advertId) {
        return commentService.getAdvertComments(advertId);
    }
}
