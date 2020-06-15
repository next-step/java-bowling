package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {

    private final Answer answer1 = Fixture.of().getAnswer1();

    @DisplayName("답변자가 자신이 아니면 예외 반환")
    @Test
    public void verifyOwner() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer1.delete(Fixture.of().getSanjigi()));
    }

    @DisplayName("답변 삭제 시 삭제 상태(deleted)를 TRUE로 변경")
    @Test
    public void deleteState() {
        answer1.delete(Fixture.of().getJavajigi());

        assertThat(answer1.isDeleted()).isTrue();
    }
}
