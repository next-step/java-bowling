package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("작성자와 로그인한 사용자가 다를 경우 예외를 던진다.")
    public void Given_CheckOwner_Then_Throw() {
        assertThatThrownBy(() -> Q1.softDelete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 같을 경우 예외를 던지지 않는다.")
    public void Given_CheckOwner_Then_NotThrow() {

        assertThatNoException().isThrownBy(() -> Q1.softDelete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("isDeleted 필드가 true 로 업데이트된다.")
    public void Given_SoftDelete_Then_Deleted() {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.softDelete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }
}
