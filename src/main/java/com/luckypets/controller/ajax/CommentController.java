package com.luckypets.controller.ajax;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;
import com.luckypets.service.BadRequestException;
import com.luckypets.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/clinics", method = RequestMethod.POST)
    public String addClinicComment(
            @RequestParam("clinic_id") long clinicId,
            @Valid ClinicComment comment,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //notify about errors
            return "some error";
        }
        try {
            commentService.saveClinicComment(clinicId, comment);
        } catch (BadRequestException e) {
            return e.getMessage();
        }

        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clinics")
    public List<ClinicComment> getClinicComments(@RequestParam int clinicId) {
        return commentService.getClinicComments(clinicId);
    }

    @RequestMapping(value = "/adverts", method = RequestMethod.POST)
    public String addAdvertComment(
            @RequestParam("advert_id") long advertId,
            @Valid AdvertComment comment,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //notify about errors
            return "some error";
        }
        try {
            commentService.saveAdvertComment(advertId, comment);
        } catch (BadRequestException e) {
            return e.getMessage();
        }
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adverts")
    public List<AdvertComment> getAdvertComments(@RequestParam int advertId) {
        return commentService.getAdvertComments(advertId);
    }
}
