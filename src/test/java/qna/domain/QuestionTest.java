package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    @DisplayName("질문이 완전히 삭제되지 않고 삭제 상태로만 변경된다.")
    void softDelete() {
        // given
        final Question question = new Question("제목", "내용");

        // when
        question.delete();

        // then
        assertThat(question.isDeleted()).isTrue();
    }
}
