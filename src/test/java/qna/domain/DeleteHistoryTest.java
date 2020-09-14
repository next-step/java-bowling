package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @Test
    void questionOf() {
        DeleteHistory deleteHistory = DeleteHistory.questionOf(QuestionTest.Q1);
        assertThat(deleteHistory)
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    void answerOf() {
        DeleteHistory deleteHistory = DeleteHistory.answerOf(AnswerTest.A1);
        assertThat(deleteHistory)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }
}