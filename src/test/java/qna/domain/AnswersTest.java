package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    private Answer answer;
    private Answer otherAnswer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        otherAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @DisplayName("답변자 객체를 생성할 수 있다.")
    @Test
    void create() {
        List<Answer> answerList = Arrays.asList(answer, otherAnswer);

        Answers answers = new Answers(answerList);

        assertThat(answers.getAnswers()).containsExactly(answer, otherAnswer);
    }
}