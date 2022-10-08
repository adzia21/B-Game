package com.example.bgame.model.external;

import com.example.bgame.Utils;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Game{
    private String id;
    private String handle;
    private String url;
    private String edit_url;
    private String name;
    private String price;
    private String price_ca;
    private String price_uk;
    private String price_au;
    private double msrp;
    private ArrayList<Msrp> msrps;
    private String discount;
    private int year_published;
    private int min_players;
    private int max_players;
    private ArrayList<Integer> player_counts;
    private int min_playtime;
    private int max_playtime;
    private int min_age;
    private String description;
    private String commentary;
    private String faq;
    private String thumb_url;
    private String image_url;
    private Object matches_specs;
    private ArrayList<Object> specs;
    private ArrayList<Mechanic> mechanics;
    private ArrayList<Category> categories;
    private ArrayList<Publisher> publishers;
    private ArrayList<Designer> designers;
    private PrimaryPublisher primary_publisher;
    private PrimaryDesigner primary_designer;
    private ArrayList<Object> developers;
    private ArrayList<Object> related_to;
    private ArrayList<Object> related_as;
    private ArrayList<String> artists;
    private ArrayList<Object> names;
    private String rules_url;
    private int amazon_rank;
    private String official_url;
    private int comment_count;
    private int num_user_ratings;
    private double average_user_rating;
    private double weight_amount;
    private String weight_units;
    private double size_height;
    private double size_depth;
    private String size_units;
    private ArrayList<HistoricalLowPrice> historical_low_prices;
    private boolean active;
    private int num_user_complexity_votes;
    private double average_learning_complexity;
    private double average_strategy_complexity;
    private int visits;
    private int lists;
    private int mentions;
    private int links;
    private int plays;
    private int rank;
    private String type;
    private String sku;
    private String upc;
    private String isbn;
    private ArrayList<Object> video_links;
    private String availability_status;
    private int num_distributors;
    private int trending_rank;
    private int listing_clicks;
    private boolean is_historical_low;
    private ArrayList<String> skus;
    private ArrayList<SkuObject> sku_objects;
    private String players;
    private String playtime;
    private String msrp_text;
    private String price_text;
    private ArrayList<String> tags;
    private Images images;
    private String description_preview;
    private double cs_rating;
    private Object sell_sheet_url;
    private Object store_images_url;

}
