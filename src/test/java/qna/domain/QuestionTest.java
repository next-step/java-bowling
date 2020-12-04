package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void canNotDeleteTest_hasOtherUsersAnswers() throws CannotDeleteException {
        User loginUser = UserTest.JAVAJIGI;
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(loginUser))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void canNotDeleteTest_InvalidAuthority() throws CannotDeleteException {
        User invalidLoginUser = UserTest.SANJIGI;
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(invalidLoginUser))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }
}
