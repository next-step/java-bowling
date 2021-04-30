package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제 권한")
    void delete_authorized_exception() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    Q1.delete(UserTest.SANJIGI);
                })
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제 성공")
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        Q2.delete(UserTest.SANJIGI);
    }
}
