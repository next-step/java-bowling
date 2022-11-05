package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    void 작성자가_아닌_사람이_질문을_삭제할_시_예외가_발생한다() {
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 작성자가_질문을_삭제할_시_상태_삭제가_참이_된다() throws CannotDeleteException {
        Q1.delete(JAVAJIGI);

        assertThat(Q1).isEqualTo(new Question("title1", "contents1", true).writeBy(JAVAJIGI));
    }
}
