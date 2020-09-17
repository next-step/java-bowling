package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @Test
    @DisplayName("소유자가 맞는지")
    public void isOwner() {
        assertThat(A1.isOwner(A2.getWriter())).isFalse();
    }

    @Test
    @DisplayName("삭제 됐는지")
    public void isDeleted() {
        assertThat(A1.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("삭제 테스트")
    public void delete() {
        assertThat(A2.delete()).isEqualTo(
                new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), LocalDateTime.now())
        );
    }

    @Test
    @DisplayName("삭제 테스트한 것 삭제 됐는지")
    public void afterDelete() {
        assertThat(A2.isDeleted()).isTrue();
    }
}
