package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = TestFixture.A1;
    }

    @Test
    @DisplayName("답변이 완전히 삭제되지 않고 삭제 상태로만 변경된다.")
    void softDelete() {
        //given
        //when
        answer.delete(TestFixture.JAVAJIGI);

        //then
        assertThat(answer.isDeleted()).isTrue();
    }


    @Test
    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우 삭제가 가능하다.")
    void deleteOwnedAnswer() {
        //given
        //when
        answer.delete(TestFixture.JAVAJIGI);

        //then
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 예외가 발생한다.")
    void deleteAnswerByDifferentUser() {
        assertThatThrownBy(() -> answer.delete(TestFixture.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.NO_DELETE_PERMISSION);
    }

    @Test
    @DisplayName("삭제가 가능하면 참을 반환한다.")
    void isDeletable() {
        assertThat(answer.isDeletable(TestFixture.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("삭제가 불가능하면 거짓을 반환한다.")
    void isNotDeletable() {
        assertThat(answer.isDeletable(TestFixture.SANJIGI)).isFalse();
    }
}
