package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("본인이 작성한 게시물이 아니면 오류 발생")
    void checkDeleteQuestionPermission() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.checkDeleteQuestionPermission(UserTest.SANJIGI))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }
}
