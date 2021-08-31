package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void Answer() {
        assertAll(() -> {
            assertThat(A1.isDeleted()).isTrue();
            assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
            assertThat(A1.question()).isEqualTo(QuestionTest.Q1);
        });
    }

    @Test
    void delete() {
        // Given
        A1.setDeleted(true);

        assertThat(A1.isDeleted()).isTrue();
    }

}
