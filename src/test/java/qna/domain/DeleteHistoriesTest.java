package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {
    private Question question;
    private Answer answer;
    DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        deleteHistories = new DeleteHistories(question);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("question에 대한 deleteHistory 를 추가한다.")
    @Test
    void addDeleteHistoryForQuestion() {
        deleteHistories.addDeleteHistoryForQuestion();
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }

    @DisplayName("answers 대한 deleteHistory 를 추가한다.")
    @Test
    void addDeleteHistoryForAnswers() {
        deleteHistories.addDeleteHistoryForAnswers();
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
