package qna.domain.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deleteHistory.ContentType;
import qna.domain.deleteHistory.DeleteHistory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.question.Question.UNAUTHORIZED_ANSWER_MESSAGE;
import static qna.domain.question.Question.UNAUTHORIZED_QUESTION_MESSAGE;
import static qna.domain.question.answer.AnswerTest.A1;
import static qna.domain.question.answer.AnswerTest.A2;
import static qna.domain.user.UserTest.JAVAJIGI;
import static qna.domain.user.UserTest.SANJIGI;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자가 같으면 true를 반환한다")
    void shouldReturnTrueWhenOwner() throws CannotDeleteException {
        boolean result = Q1.isOwner(JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자가 다르면 예외를 던진다")
    void shouldThrowWhenNotEqualLoginUser() {
        assertThatThrownBy(() -> Q1.isOwner(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(UNAUTHORIZED_QUESTION_MESSAGE);
    }

    @Test
    @DisplayName("로그인 한 사용자와 답변 작성자가 다르면 예외를 던진다")
    void shouldThrowWhenAnswerWriterNotEqualLoginUser() {
        Q1.addAnswer(A2);
        assertThatThrownBy(() -> Q1.isOwner(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(UNAUTHORIZED_ANSWER_MESSAGE);
    }

    @Test
    @DisplayName("질문에 답변을 추가한다")
    void shouldAddAnswers() {
        Q1.addAnswer(A1);
        assertThat(Q1.answers()).contains(A1);
    }

    @Test
    @DisplayName("질문을 삭제하면 관련된 답변도 삭제된다")
    void shouldDeleteQuestionAndAnswer() {
        Q1.addAnswer(A1);
        List<DeleteHistory> deleteHistories = Q1.delete();

        assertThat(deleteHistories).contains(DeleteHistory.of(ContentType.QUESTION, Q1));
        assertThat(deleteHistories).contains(DeleteHistory.of(ContentType.ANSWER, A1));
    }
}
