package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문의 소유자인지 테스트")
    @Test
    void isOwner() {
        assertThatCode(() -> Q1.isOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("질문의 소유자가 아닐때 에러")
    @Test
    void isOwnerThrowException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Q1.isOwner(UserTest.JAVAJIGI));
    }

    @DisplayName("질문 삭제")
    @Test
    void delete() {
        assertThatCode(() -> Q1.delete()).doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isTrue();
    }
}
