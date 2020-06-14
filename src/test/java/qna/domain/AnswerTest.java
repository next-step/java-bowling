package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    private final Answer answer1 = Fixture.of().getAnswer1();

    @DisplayName("답변자가 자신이면 true 반환")
    @Test
    public void isOwner() {
        assertThat(answer1.isOwner(Fixture.of().getJavajigi())).isTrue();
    }

    @DisplayName("답변 삭제 시 삭제 상태(deleted)를 TRUE로 변경")
    @Test
    public void deleteState() {
        answer1.delete();

        assertThat(answer1.isDeleted()).isTrue();
    }
}
