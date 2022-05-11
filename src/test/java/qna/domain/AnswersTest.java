package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;


class AnswersTest {

    @Test
    void create() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers.getAnswers()).hasSize(2);
    }

    @Test
    void validateAnswerOwnerSuccessTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.validateAnswerOwner(UserTest.JAVAJIGI);
    }

    @Test
    void validateAnswerOwnerFailureTest() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.validateAnswerOwner(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}