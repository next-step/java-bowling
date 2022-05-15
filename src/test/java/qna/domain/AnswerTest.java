package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void createTest() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "TestContents");

        assertThat(answer).isNotNull();
    }

    @Test
    void deleteTest() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "TestContents");

        assertThat(answer.isDeleted()).isFalse();

        A1.delete();

        assertThat(A1.isDeleted()).isTrue();
    }

}
