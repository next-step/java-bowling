package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문자와 작성자가 동일하지 않으면 삭제 권한이 없음 -> CannotDeleteException 반환")
    void checkQuestionException() {
        assertThatThrownBy(() -> Q1.checkDeleteAuth(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 작성자가 동일하면 삭제 권한 가짐")
    void checkQuestionOK() {
        assertThatCode(() -> Q1.checkDeleteAuth(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

}
