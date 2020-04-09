package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void isDeletableWithLoginUserTest() {
        Q1.addAnswer(AnswerTest.A1);
        assertThatCode(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같지 않은 경우 삭제 불가능하다.")
    void isNotDeletableWithLoginUserTest() {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    void isDeletableWithAnswerNotExistTest() {
        assertThatCode(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("질문자와 답변글의 모든답변자가 같은 경우 삭제가 가능하다.")
    void isDeletableWithWriterSameAllAnswerTest() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        assertThatCode(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("질문자와 답변글의 모든답변자가 같지 않은 경우 삭제가 불가능하다.")
    void isNotDeletableWithWriterSameAllAnswerTest() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변들 삭제 테스트")
    void deleteAnswersTest() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);

        assertThatCode(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).doesNotThrowAnyException();

        assertThat(Q1.getAnswers().stream()
                .anyMatch(a -> a.isDeleted() == false)).isFalse();
    }
}
