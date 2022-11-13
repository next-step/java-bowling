package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.QuestionTest.*;

public class AnswersTest {

    @Test
    void deleteAnswers() throws CannotDeleteException {
        Answers answers = Q1.getAnswers();

        Q1.addAnswer(A1);
        answers.delete(UserTest.JAVAJIGI, new DeleteHistories());
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void deleteAnswers_error() {
        Answers answers = Q1.getAnswers();

        Q1.addAnswer(A2);
        Assertions.assertThatThrownBy(
                () -> answers.delete(UserTest.JAVAJIGI, new DeleteHistories())
        ).isInstanceOf(CannotDeleteException.class);
    }
}
