package com.example.bgame.model.internal;

import com.example.bgame.Utils;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board_game")
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String priceUk;
    private String pricePl;
    private int publishedYear;
    private int minPlayers;
    private int maxPlayers;
    private int minPlaytime;
    private int maxPlaytime;
    private String playtime;
    private int minAge;
    private String description;
    private String commentary;
    private String faq;
    private String imageUrl;
    private String publisher;
    private String designer;
    @ElementCollection
    private List<String> artists;

    public void setDescription(String description) {
        this.description = Utils.removeHtmlCode(description);
    }

    public void setCommentary(String commentary) {
        this.commentary = Utils.removeHtmlCode(commentary);
    }

    public void setFaq(String faq) {
        this.faq = Utils.removeHtmlCode(faq);
    }
}
