package com.example.xiyouji.quiz.dto;

import com.example.xiyouji.type.Language;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class QuizDto {

    @Data
    public static class QuizRequestDto {
        private Language language;

        @Builder
        public QuizRequestDto(Language language) {
            this.language = language;
        }
    }

    @Data
    public static class QuizResponseDto {
        private String quizContent;

        private List<String> characterType;

        private Object result;

        @Builder
        public QuizResponseDto(String quizContent, List<String> characterType, Object result) {
            this.quizContent = quizContent;
            this.characterType = characterType;
            this.result = result;
        }
    }
}
