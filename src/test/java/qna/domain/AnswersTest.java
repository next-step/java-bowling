package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answers 객체 생성")
    void fromTest() {
        Answers answers = Answers.from(Arrays.asList(A1, A2));
        Assertions.assertThat(answers.getSize()).isEqualTo(2);
    }
}