package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void addAnswer() {
        Answers answers = new Answers();
        answers.addAnswer(A1, QuestionTest.Q1);

        Assertions.assertThat(answers.contains(A1)).isTrue();
    }

    @Test
    void delete() {
        A1.delete();
        Assertions.assertThat(A1.isDeleted()).isTrue();
    }
}
