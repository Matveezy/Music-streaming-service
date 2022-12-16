package com.racers.euphmusic.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

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

    private String password;

    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    private Integer balance;

    private String status;

    private String description;

    @ManyToMany(cascade = CascadeType.REMOVE)
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

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getPersons().add(this);
    }


}
