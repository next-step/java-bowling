package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 유저와 질문자가 일치하지 않을 경우 CannotDeleteException")
    void throwCannotDeleteException() {

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q2.checkLoginUserEqualWithQuestionOwner(UserTest.JAVAJIGI))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }
}
