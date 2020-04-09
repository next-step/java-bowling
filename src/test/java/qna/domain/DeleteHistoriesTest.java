package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("question에 대한 deleteHistory 를 추가한다.")
    @Test
    void addDeleteHistory() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteHistoryForQuestion(question.getId(), question);
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
