package com.example.xiyouji.story.vo;

import com.example.xiyouji.store.UploadFile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class StoryContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Story story;

    @Column(nullable = false)
    private String content;

    private Integer paragraphNum;

    @Builder
    public StoryContent(Long id, Story story, String content, Integer paragraphNum) {
        this.id = id;
        this.story = story;
        this.content = content;
        this.paragraphNum = paragraphNum;
    }
}
