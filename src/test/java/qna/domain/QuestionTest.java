package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 질문자가_아니면_삭제_불가() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 답변_없으면_삭제_가능() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
