package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;
import qna.exception.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.")
    @Test
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 예외가 발생한다.")
    @Test
    void deleteException() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("삭제된 질문과 답글의 정보를 리스트로 받을 수 있다.")
    @Test
    void deleteHistories() throws CannotDeleteException {
        Question question = new Question(1L, "title1", "contents1");
        question.writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        DeleteHistory deleteHistory0 = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now());
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).containsExactly(deleteHistory0, deleteHistory1, deleteHistory2);
    }

    @DisplayName("답글이 없는 경우 질문만 삭제된다.")
    @Test
    void deleteQuestion() throws CannotDeleteException {
        Question question = new Question(1L, "title1", "contents1");
        question.writeBy(UserTest.JAVAJIGI);

        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).containsExactly(deleteHistory);
    }
}
