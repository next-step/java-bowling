package qna.domain.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deleteHistory.DeleteHistory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.question.Question.UNAUTHORIZED_QUESTION_MESSAGE;
import static qna.domain.question.answer.AnswerTest.A1;
import static qna.domain.question.answer.Answers.UNAUTHORIZED_ANSWER_MESSAGE;
import static qna.domain.user.UserTest.JAVAJIGI;
import static qna.domain.user.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    @DisplayName("질문에 답변을 추가한다")
    void shouldAddAnswers() {
        Q2.addAnswer(A1);
        assertThat(Q2.answers()).contains(A1);
    }

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 다르면 예외를 던진다")
    void shouldThrowWhenAnswerWriterNotEqualLoginUser() {
        Q2.addAnswer(A1);
        assertThatThrownBy(() -> Q2.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(UNAUTHORIZED_ANSWER_MESSAGE);
    }

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자가 다르면 예외를 던진다")
    void shouldThrowWhenQuestionWriterNotEqualLoginUser() {
        assertThatThrownBy(() -> Q2.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(UNAUTHORIZED_QUESTION_MESSAGE);
    }

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자, 답변 작성자가 같으면 모두 삭제한다")
    void shouldDeleteQuestionAndAnswer() throws CannotDeleteException {
        Q1.addAnswer(A1);
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertThat(deleteHistories.size()).isEqualTo(2);
    }
}
