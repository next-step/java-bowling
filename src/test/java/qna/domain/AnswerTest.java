package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("정상 삭제 테스트")
    @Test
    void delete() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();

        A2.delete();
        assertThat(A2.isDeleted()).isTrue();
    }
}
