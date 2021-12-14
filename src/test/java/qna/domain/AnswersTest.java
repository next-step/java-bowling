package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    void deleteAll() {
        Answers answers = Answers.from(
                Arrays.asList(
                        new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                        new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2")));

        List<Answer> answerList = answers.value();

        answerList.forEach(answer -> assertThat(answer.isDeleted()).isFalse());
        answers.deleteAll();

        assertThat(answerList.size()).isGreaterThan(0);
        answerList.forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

}
