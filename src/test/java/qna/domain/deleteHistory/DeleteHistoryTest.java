package qna.domain.deleteHistory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.question.answer.AnswerTest;
import qna.domain.question.QuestionTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteHistoryTest {

    @Test
    @DisplayName("질문을 삭제한다")
    void shouldDeleteQuestion() {
        DeleteHistory questionDeleteHistory = DeleteHistory.of(ContentType.QUESTION, QuestionTest.Q1);
        assertThat(questionDeleteHistory).isEqualTo(DeleteHistory.of(ContentType.QUESTION, QuestionTest.Q1));
    }

    @Test
    @DisplayName("답변을 삭제한다")
    void shouldDeleteAnswer() {
        DeleteHistory answerDeleteHistory = DeleteHistory.of(ContentType.ANSWER, AnswerTest.A1);
        assertThat(answerDeleteHistory).isEqualTo(DeleteHistory.of(ContentType.ANSWER, AnswerTest.A1));
    }
}