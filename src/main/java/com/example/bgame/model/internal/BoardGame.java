package com.example.bgame.model.internal;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name cannot be blank")
    @NonNull
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
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String commentary;
    @Column(columnDefinition = "TEXT")
    private String faq;
    private String imageUrl;
    private String publisher;
    private String designer;
    @ElementCollection
    private List<String> artists;

    public void setPricePl(String pricePl) {
        if (pricePl.isEmpty() || pricePl.isBlank()) {
            double price = Double.parseDouble(priceUk) * 4.5; //TODO find some info about saving to properties one value (maybe API later)
            this.pricePl = Double.toString(price);
        } else {
            this.pricePl = pricePl;
        }
    }
}
