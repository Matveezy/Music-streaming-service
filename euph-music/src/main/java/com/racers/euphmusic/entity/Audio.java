package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"authors", "savedBy"})
@EqualsAndHashCode(of = "id")
@Table(schema = "s312762")
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String text;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @ManyToMany
    @JoinTable(
            name = "author_audio",
            joinColumns = @JoinColumn(name = "audio_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Person> authors;

    @ManyToMany
    @JoinTable(
            name = "save_audio",
            joinColumns = @JoinColumn(name = "audio_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> savedBy;
}
