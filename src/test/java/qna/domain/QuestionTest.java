package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 작성자가_아닐시_예외처리() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.delete(UserTest.SANJIGI));
    }

    @Test
    void 작성자가_아닌_답변_있을시_예외처리() {

    }

    @Test
    void 삭제성공시_deleted_변경_확인() {

    }
}
