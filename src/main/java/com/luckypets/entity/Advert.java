package com.luckypets.entity;

import com.luckypets.entity.enums.AnimalType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@Entity
@Table(name = "advert")
public class Advert implements Serializable {//объявление
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    @Size(max = 63)
    private String title;

    @Column(name = "text")
    @Size(max = 255)
    private String text;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;//should not have setter

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "advert_animal_types",
            joinColumns = @JoinColumn(name = "advert_id"))
    @Column(name = "animal")
    private EnumSet<AnimalType> animalTypes;

    @OneToMany(mappedBy = "advert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AdvertComment> advertComments;


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EnumSet<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(EnumSet<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public List<AdvertComment> getAdvertComments() {
        return advertComments;
    }

    public void setAdvertComments(List<AdvertComment> advertComments) {
        this.advertComments = advertComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (id != advert.id) return false;
        if (!creationDate.equals(advert.creationDate)) return false;
        if (!title.equals(advert.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        if (creationDate != null) result = 31 * result + creationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
