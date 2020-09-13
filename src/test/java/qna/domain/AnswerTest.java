package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        A1.delete(deleteHistories);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
