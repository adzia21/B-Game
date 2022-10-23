package com.example.bgame.model.internal.comments;

import com.example.bgame.model.internal.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;
    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    @Column(columnDefinition = "TEXT")
    @Size(min=10, max = 1600)
    private String content;
    private Long superiorCommentId;

    public Comment(User creator, String content) {
        this.creator = creator;
        this.content = content;
    }
}
