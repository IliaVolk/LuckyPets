package com.luckypets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clinic")
public class Clinic implements Serializable {

    /*
    поля:
    #район для поиска по району
    #адрес клиники
    номер телефона
     */
    /*
    запросы:
    поиск клиник по району и типу\без типа
    по радиусу и типу\без типа

    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    @Size(max = 63)
    private String title;

    @OneToOne
    @JoinColumn(name = "lat_lng_id")
    private LatLng latLng;

    @Column(name = "description")
    @Size(max = 255)
    private String description;

    @Column(name = "district")
    @JsonIgnore
    private District district;

    @Column(name = "address")
    @Size(max = 60)
    private String address;
    //@JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "clinic_emails",
            joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "email")
    private Set<String> contactEmails;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "clinic_animal_types",
            joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "animal")
    private Set<AnimalType> animalTypes;

    @JsonIgnore
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("creationDate DESC ")
    private List<ClinicComment> comments;


    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Set<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(Set<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public List<ClinicComment> getComments() {
        return comments;
    }

    public void setComments(List<ClinicComment> comments) {
        this.comments = comments;
    }

    public Set<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(List<String> contactEmails) {
        this.contactEmails = new HashSet<>(
                contactEmails
        );
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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

    public double mark() {
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
                // ", contactEmails=" + contactEmails +
                ", latLng=" + latLng +
                //", mark='" + getMark() + "'" +
                //", animalTypes=" + animalTypes +
                '}';
    }
}
