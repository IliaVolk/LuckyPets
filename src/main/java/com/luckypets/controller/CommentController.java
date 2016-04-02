package com.luckypets.controller;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;
import com.luckypets.service.BadRequestException;
import com.luckypets.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/clinic", method = RequestMethod.GET)
    public String addClinicComment(
            @RequestParam("clinic_id") long clinicId,
            @RequestParam("comment") ClinicComment comment,
            Model model) {
        try {
            commentService.saveClinicComment(clinicId, comment);
        } catch (BadRequestException e) {
            model.addAttribute("request_error", e.getMessage());
        }

        return "";
    }

    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public String addAdvertComment(
            @RequestParam("advert_id") long advertId,
            @RequestParam("comment") AdvertComment comment,
            Model model) {
        try {
            commentService.saveAdvertComment(advertId, comment);
        } catch (BadRequestException e) {
            model.addAttribute("request_error", e.getMessage());
        }
        return "";
    }
}
