package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @Test
    @DisplayName("question deleteHistory 객체 생성 테스트")
    void createQuestionDeleteHistoryTest() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(DeleteHistory.of(QuestionTest.Q1)).isEqualTo(deleteHistory);
    }

    @Test
    @DisplayName("answer deleteHistory 객체 생성 테스트")
    void createAnswerDeleteHistoryTest() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(DeleteHistory.of(AnswerTest.A1)).isEqualTo(deleteHistory);
    }
}