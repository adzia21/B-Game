package com.example.bgame.model.internal.lists;

import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class BoardGameList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private EBoardGameNamesList namesList;
    @ManyToMany
    @JoinTable(name = "boardGameList_boardGame",
            joinColumns = @JoinColumn(name = "boardGameList_id"),
            inverseJoinColumns = @JoinColumn(name = "boardGame_id"))
    private List<BoardGame> boardGameList;

    public BoardGameList(EBoardGameNamesList namesList) {
        this.namesList = namesList;
        this.boardGameList = new ArrayList<>();
    }
}
