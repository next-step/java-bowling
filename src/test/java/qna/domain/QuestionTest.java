package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void isDeletableWithLoginUserTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.deleteWithValidation(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같지 않은 경우 삭제 불가능하다.")
    void isNotDeletableWithLoginUserTest() {
        assertThatThrownBy(
                () -> Q1.deleteWithValidation(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    void isDeletableWithAnswerNotExistTest() throws CannotDeleteException {
        Q1.deleteWithValidation(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든답변자가 같은 경우 삭제가 가능하다.")
    void isDeletableWithWriterSameAllAnswerTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.deleteWithValidation(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든답변자가 같지 않은 경우 삭제가 불가능하다.")
    void isNotDeletableWithWriterSameAllAnswerTest() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(
                () -> Q1.deleteWithValidation(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
