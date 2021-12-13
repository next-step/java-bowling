package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete() throws CannotDeleteException {
        Question question = new Question("t1", "c1").writeBy(UserTest.JAVAJIGI);
        assertThat(question.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @Test
    public void deleteFailed() {
        Question question = new Question("t1", "c1").writeBy(UserTest.SANJIGI);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .withMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
