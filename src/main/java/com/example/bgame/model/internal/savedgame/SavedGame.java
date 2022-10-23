package com.example.bgame.model.internal.savedgame;

import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.comments.Comment;
import com.example.bgame.model.internal.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SavedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 60)
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreatedDate
    private Date creationDate;

    @Column(columnDefinition = "TEXT")
    @Size(min = 10, max = 600)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> players;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    //TODO PHOTO
    //TODO EDITED - HISTORY

    @ManyToOne //TODO - check if user can have more than one the same game in their board
    @JoinColumn(name = "board_game_id")
    private BoardGame boardGame;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comments_to_description",
            joinColumns = @JoinColumn(name = "comments"),
            inverseJoinColumns = @JoinColumn(name = "description"))
    private List<Comment> comments;

}
