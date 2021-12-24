package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswersTest {
    @Test
    public void 다른_작성자_답변글_존재_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
            answers.getDeleteHistories(UserTest.JAVAJIGI, QuestionTest.Q1, UserTest.DEFAULT_TIME);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_같은_답변글() throws CannotDeleteException {
        DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        DeleteHistory answerHistory = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);

        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        assertThat(answers.getDeleteHistories(UserTest.JAVAJIGI, QuestionTest.Q1, UserTest.DEFAULT_TIME))
                .isEqualTo(Arrays.asList(questionHistory, answerHistory));
    }
}