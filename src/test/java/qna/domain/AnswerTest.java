package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void testDelete() {
        assertThat(A1.delete(UserTest.JAVAJIGI)).isTrue();
        assertThat(A1.delete(UserTest.SANJIGI)).isFalse();
        assertThat(A2.delete(UserTest.JAVAJIGI)).isFalse();
        assertThat(A2.delete(UserTest.SANJIGI)).isTrue();
    }
}
