package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents2");

    @Test
    public void delete시_DeleteHistory를_정상적으로_반환하는지_검증() {
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI);

        DeleteHistory deleteHistory = A1.delete();

        assertThat(deleteHistory).isEqualTo(expected);
    }
}
