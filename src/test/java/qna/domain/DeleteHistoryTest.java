package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class DeleteHistoryTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteHistoryCreateTest() {
        Answers answers = Answers.of(Arrays.asList(A1, A2), A1.getWriter());

        DeleteHistories deleteHistories = new DeleteHistories(Q1,answers);
        assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(3);
    }
}
