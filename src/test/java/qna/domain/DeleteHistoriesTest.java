package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {
    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories(Collections.EMPTY_LIST);
    }

    @DisplayName("Question Delete 생성")
    @Test
    void addQuestionDelete() {
        DeleteHistories deleteHistories = this.deleteHistories.addQuestion(QuestionTest.Q1);

        assertThat(deleteHistories.getDeleteHistories()).extracting("contentType").contains(ContentType.QUESTION);
    }

    @DisplayName("Answer Delete 생성")
    @Test
    void addAnswerDelete() {
        DeleteHistories deleteHistories = this.deleteHistories.addAnswer(AnswerTest.A1);

        assertThat(deleteHistories.getDeleteHistories()).extracting("contentType").contains(ContentType.ANSWER);
    }
}