package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A4;
import static qna.domain.QuestionTest.Q2;

public class DeleteHistoryTest {

    @Test
    void ofQuestion() {
        DeleteHistory deleteHistory = DeleteHistory.ofQuestion(Q2);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now()));
    }

    @Test
    void ofAnswer() {
        DeleteHistory deleteHistory = DeleteHistory.ofAnswer(A4);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A4.getId(), A4.getWriter(), LocalDateTime.now()));
    }
}
