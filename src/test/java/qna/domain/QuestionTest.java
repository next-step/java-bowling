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
            .isThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 작성자가_아닌_답변_있을시_예외처리() {
        Q1.addAnswer(AnswerTest.A2);
        Q1.addAnswer(AnswerTest.A1);

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
            .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 삭제성공시_deleted_변경_확인() throws CannotDeleteException {
        // given
        Q1.addAnswer(AnswerTest.A1);

        // when
        Q1.delete(UserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
    }

}
