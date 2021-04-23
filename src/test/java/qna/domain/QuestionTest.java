package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.entity.DeleteHistory;
import qna.domain.entity.Question;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 생성")
    public void questionCreate() {
        String title = "title3";
        String contents = "contents3";

        Question question = new Question(title, contents).writeBy(UserTest.JAVAJIGI);
        assertThat(question.equals(new Question(title, contents).writeBy(UserTest.JAVAJIGI))).isTrue();
    }

    @Test
    @DisplayName("질문 생성자 확인")
    public void questionCreateUser() throws CannotDeleteException {
        assertThat(Q1.deleteAuthCheck(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 권한 없음")
    public void questionDeleteNoAuth() {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class).hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }


    @Test
    @DisplayName("질문 삭제")
    public void questionDelete() throws CannotDeleteException {

        Q1.addAnswer(AnswerTest.A1);

        List<DeleteHistory> delete = Q1.delete(UserTest.JAVAJIGI);

        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));

        assertThat(delete.equals(deleteHistories)).isTrue();
    }


}
