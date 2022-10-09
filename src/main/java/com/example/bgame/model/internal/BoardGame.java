package com.example.bgame.model.internal;

import com.example.bgame.Utils;
import com.example.bgame.model.external.PrimaryDesigner;
import com.example.bgame.model.external.PrimaryPublisher;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
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
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String commentary;
    @Column(columnDefinition="TEXT")
    private String faq;
    private String imageUrl;
    private String publisher;
    private String designer;
    @ElementCollection
    private List<String> artists;

//    public BoardGame(String name, String priceUk, double pricePl, int publishedYear, int minPlayers, int maxPlayers, int minPlaytime, int maxPlaytime,
//     int minAge, String playtime, String description, String commentary, String faq, String imageUrl, PrimaryPublisher publisher,
//     PrimaryDesigner designer, ArrayList<String> artists) {
//    }

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
