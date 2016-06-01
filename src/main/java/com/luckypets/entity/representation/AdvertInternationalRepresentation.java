package com.luckypets.entity.representation;

import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;

import java.util.Date;
import java.util.List;

public class AdvertInternationalRepresentation {
    public AdvertInternationalRepresentation(Advert advert, String[] animalTypes, String advertType) {
        this.animalTypes = animalTypes;
        this.advertType = advertType;
        setId(advert.getId());
        setText(advert.getText());
        setTitle(advert.getTitle());
        setCreationDate(advert.getCreationDate());
        setUser(advert.getUser());
    }


    private long id;

    private String title;

    private String text;

    private Date creationDate;//should not have setter

    private User user;

    private String[] animalTypes;

    private List<AdvertComment> advertComments;

    private String advertType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(String[] animalTypes) {
        this.animalTypes = animalTypes;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }
}
