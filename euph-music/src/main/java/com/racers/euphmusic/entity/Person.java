package com.racers.euphmusic.entity;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"roles", "posts", "followers", "followTo", "loadedAudios", "savedAudios"})
@Builder
@Table(schema = "s312762")
public class Person {

    // TODO: 15.12.2022
//    - сделать запрос на количество подписчиков и на кого подписан, чтоб сразу не дергать лист

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(updatable = false)
    private String password;

    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Generated(GenerationTime.ALWAYS)
    private Integer balance;

    private String status;

    private String description;

    private String image;

    @ManyToMany
    @JoinTable(
            name = "role_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_follow",
            joinColumns = @JoinColumn(name = "follow_to_person_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_person_id")
    )
    private List<Person> followers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_follow",
            joinColumns = @JoinColumn(name = "follower_person_id"),
            inverseJoinColumns = @JoinColumn(name = "follow_to_person_id")
    )
    private List<Person> followTo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_audio",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> loadedAudios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "save_audio",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> savedAudios;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "nravlik",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> audiosSettedNravlik;

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getPersons().add(this);
    }

    public void addAudio(Audio audio) {
        this.getLoadedAudios().add(audio);
        audio.getAuthors().add(this);
    }

    public void saveAudio(Audio audio) {
        this.getSavedAudios().add(audio);
        audio.getSavedBy().size();
        audio.getSavedBy().add(this);
    }

    public void addPost(Post post) {
        post.setPerson(this);
        this.getPosts().add(post);
    }
}