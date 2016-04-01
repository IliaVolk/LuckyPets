package com.luckypets.entity;

import com.luckypets.entity.enums.AnimalType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

@Entity
@Table(name = "clinic")
public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    @Size(max = 63)
    private String title;

    //private LatLng latLng;

    @Column(name = "description")
    @Size(max = 255)
    private String description;

    @ElementCollection
    @CollectionTable(name = "clinic_emails",
            joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "email")
    private List<String> contactEmails;

    @ElementCollection
    @CollectionTable(name = "clinic_animal_types",
            joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "animal")
    private EnumSet<AnimalType> animalTypes;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClinicComment> comments;


    public EnumSet<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(EnumSet<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public List<ClinicComment> getComments() {
        return comments;
    }

    public void setComments(List<ClinicComment> comments) {
        this.comments = comments;
    }

    public List<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(List<String> contactEmails) {
        this.contactEmails = contactEmails;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clinic clinic = (Clinic) o;

        if (id != clinic.id) return false;
        if (!title.equals(clinic.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        return result;
    }

    public double getMark() {
        double mark = 0;
        for (ClinicComment comment : comments) {
            mark += comment.getMark();
        }
        mark /= comments.size();
        return mark;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contactEmails=" + contactEmails +
                //", comments=" + comments +
                ", mark='" + getMark() + "'" +
                '}';
    }
}
