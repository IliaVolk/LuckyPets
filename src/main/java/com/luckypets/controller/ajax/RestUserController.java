package com.luckypets.controller.ajax;


import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;
import com.luckypets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("ajax/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User createNewUserAccount(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "adverts/{beginIndex}/{count}")
    public List<Advert> getAdvertsForUser(@PathVariable int beginIndex, @PathVariable int count) {
        return userService.getAdverts(beginIndex, count);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adverts/comments/{beginIndex}/{count}")
    public List<AdvertComment> getAdvertCommentsForUser(
            @PathVariable int beginIndex, @PathVariable int count) throws IOException {

        List<AdvertComment> comments = userService.getComments(beginIndex, count);


        return comments;
    }


}
