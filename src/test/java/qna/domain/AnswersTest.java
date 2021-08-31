package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

public class AnswersTest {

    @Test
    void answersIsNotSameWiter() throws CannotDeleteException {
        List<Answer> answers = new ArrayList<>();
        answers.add(A1);
        answers.add(A2);

        Answers answer = Answer.of(answers);

        assertThatThrownBy(
                () -> answer.delete(JAVAJIGI)

        ).isInstanceOf(CannotDeleteException.class);

    }
}
