package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @Test
    @DisplayName("답변이 완전히 삭제되지 않고 삭제 상태로만 변경된다.")
    void softDelete() {
        //given
        final Answer answer = TestFixture.A1;

        //when
        answer.delete(TestFixture.JAVAJIGI);

        //then
        assertThat(answer.isDeleted()).isTrue();
    }
}
