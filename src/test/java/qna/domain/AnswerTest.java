package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 히스토리를 생성할 수 있다.")
    @Test
    void canCreateDeleteHistory() {
        assertThat(A1.createDeleteHistory()).isInstanceOf(DeleteHistory.class);
        assertThat(A1.isDeleted()).isTrue();
    }
}
