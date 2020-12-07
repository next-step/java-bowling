package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;


public class AnswersTest {
    @Test
    public void addAnswerTest() {
        Answers answers = new Answers();
        int sizeBefore = answers.size();
        answers.addAnswer(QuestionTest.Q1, AnswerTest.A1);
        int sizeAfter = answers.size();

        assertThat(sizeAfter - sizeBefore).isEqualTo(1);
    }

    @Test
    public void deleteAllTest() throws CannotDeleteException {
        Answers answers = new Answers(AnswerTest.A1, AnswerTest.A3);

        answers.deleteAll(UserTest.JAVAJIGI);
    }
}
