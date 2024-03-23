package com.example.xiyouji.story.vo;


import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private Characters characters;

    private String storyTitle;

    @OneToMany(mappedBy = "story" , orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Content> storyContent;

    @Enumerated
    private Language language;

    public StoryDto.StoryResponseDto toStoryResponseDto() {
        return StoryDto.StoryResponseDto.builder()
                .storyContent(storyContent.stream()
                        .map(Content::getStoryStr)
                        .toList())
                .build();
    }

    @Builder
    public Story(Long id, Characters characters, String storyTitle, List<Content> storyContent, Language language) {
        this.id = id;
        this.characters = characters;
        this.storyTitle = storyTitle;
        this.storyContent = storyContent;
        this.language = language;
    }
}
