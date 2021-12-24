package qna.domain.deleteHistory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.deleteHistory.ContentType.ANSWER;
import static qna.domain.deleteHistory.ContentType.QUESTION;
import static qna.domain.question.QuestionTest.Q1;
import static qna.domain.question.answer.AnswerTest.A1;

class DeleteHistoryTest {

    @Test
    @DisplayName("질문을 삭제한다")
    void shouldDeleteQuestion() {
        DeleteHistory questionDeleteHistory = DeleteHistory.of(QUESTION, Q1);
        assertThat(questionDeleteHistory).isEqualTo(DeleteHistory.of(QUESTION, Q1));
    }

    @Test
    @DisplayName("답변을 삭제한다")
    void shouldDeleteAnswer() {
        DeleteHistory answerDeleteHistory = DeleteHistory.of(ANSWER, A1);
        assertThat(answerDeleteHistory).isEqualTo(DeleteHistory.of(ANSWER, A1));
    }
}