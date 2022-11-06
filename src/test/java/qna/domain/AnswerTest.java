package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void answer_정상_삭제() {
        A1.delete();

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A1.deleteHistory()).isPresent();
    }

    @Test
    public void answer이_삭제되지_않은_상태라면_deleteHistory_empty() {
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A1.deleteHistory()).isEmpty();
    }
}
