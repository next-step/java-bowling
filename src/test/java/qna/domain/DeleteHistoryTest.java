package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @Test
    @DisplayName("Question 삭제 기록 정적팩토리 메서드 테스트")
    void questionDeleteHistory() {
        // given
        final long id = 25L;
        final long questionId = 10L;
        final DeleteHistory expected = new DeleteHistory(id, ContentType.QUESTION, questionId, TestFixture.JAVAJIGI, LocalDateTime.now());

        // when
        DeleteHistory questionDeleteHistory = DeleteHistory.createQuestionHistory(id, questionId, TestFixture.JAVAJIGI);

        // then
        assertThat(questionDeleteHistory).isEqualTo(expected);
    }

    @Test
    @DisplayName("Answer 삭제 기록 정적팩토리 메서드 테스트")
    void answerDeleteHistory() {
        // given
        final long id = 25L;
        final long answerId = 100L;
        final DeleteHistory expected = new DeleteHistory(id, ContentType.ANSWER, answerId, TestFixture.JAVAJIGI, LocalDateTime.now());

        // when
        DeleteHistory answerDeleteHistory = DeleteHistory.createAnswerHistory(id, answerId, TestFixture.JAVAJIGI);

        // then
        assertThat(answerDeleteHistory).isEqualTo(expected);
    }
}
