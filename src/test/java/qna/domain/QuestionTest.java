package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 삭제 할 권한이 있을 때 정상 동작")
    @Test
    void isOwner() {
        assertThatCode(() -> Q1.checkRemovable(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> Q2.checkRemovable(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("질문의 삭제할 권한이 없을때 throws CannotDeleteException")
    @Test
    void isOwnerThrowException() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> Q1.checkRemovable(UserTest.SANJIGI));
    }

    @DisplayName("질문 삭제")
    @Test
    void delete() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();

        Q2.delete();
        assertThat(Q2.isDeleted()).isTrue();
    }
}
