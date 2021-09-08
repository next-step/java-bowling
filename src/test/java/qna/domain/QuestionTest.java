package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A3;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 삭제")
    void deleteQuestion() throws CannotDeleteException {
        Q1.deleteQuestion(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 다른 작성자 삭제")
    void deleteQuestionAnotherUser() {
        assertThatThrownBy(() ->
            Q1.deleteQuestion(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
    @Test
    @DisplayName("질문자, 답변 작성자 같음")
    void deleteQuestionAndAnswerSameUser() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.deleteQuestion(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자, 답변 작성자 다름")
    void deleteQuestionAndAnswerAnotherUser() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() ->
                Q1.deleteQuestion(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 히스토리 생성")
    void createDeleteHistory() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A3);
        List<DeleteHistory> deleteHistories = Q1.deleteQuestion(UserTest.JAVAJIGI);

        assertThat(deleteHistories.contains(
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()))
                && deleteHistories.contains(
                        new DeleteHistory(ContentType.ANSWER, A3.getId(), A3.getWriter(), LocalDateTime.now()))
                && deleteHistories.contains(
                        new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())))
                .isTrue();
    }
}
