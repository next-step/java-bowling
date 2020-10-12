package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
    public static final Answer A4 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents4");

    @DisplayName("답변 삭제 테스트")
    @Test
    void delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        A2.delete(deleteHistories);
        assertThat(A2.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isFalse();
    }
}
