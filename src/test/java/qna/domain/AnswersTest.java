package qna.domain;

import org.junit.Before;
import org.junit.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    private Answers answers;

    @Before
    public void setUp() {
        answers = new Answers();
    }

    @Test
    public void delete_답변이_없는_경우_성공_확인() {
        assertThatCode(() -> answers.delete(AnswerTest.A1.getWriter()))
                .doesNotThrowAnyException();
    }

    @Test
    public void delete_답변자가_질문자와_모두_같은_경우_성공_확인() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);

        List<DeleteHistory> result = answers.delete(AnswerTest.A1.getWriter());
        assertThat(result).hasSize(3);
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
