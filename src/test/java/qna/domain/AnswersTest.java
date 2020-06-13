package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    private Answer answer;
    private Answer otherAnswer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        otherAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @DisplayName("답변 리스트를 반환")
    @Test
    public void getAnswers() {
        List<Answer> answerList = Arrays.asList(answer, otherAnswer);

        assertThat(Answers.of(answerList).getAnswers())
                .containsExactly(answer, otherAnswer);
    }
}
