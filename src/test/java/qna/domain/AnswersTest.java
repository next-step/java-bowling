package qna.domain;

import org.junit.Before;
import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Answers answers;

    @Before
    public void setUp() {
        answers = new Answers();
    }

    @Test
    public void delete_불가_다른_사람이_쓴_답변존재_확인() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> {
            answers.checkAnswersDeletable(UserTest.SILPAE);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
