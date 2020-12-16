package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 테스트")
    void testDelete() {
        Answer sample = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "ABCD");

        assertThat(sample.isDeleted()).isFalse();

        sample.delete();

        assertThat(sample.isDeleted()).isTrue();
    }
}
