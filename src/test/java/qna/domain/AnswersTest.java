package qna.domain;


import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answers AS1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
    public static final List<DeleteHistory> AS1DELETE = Arrays.asList(new DeleteHistory(AnswerTest.A1, UserTest.JAVAJIGI), new DeleteHistory(AnswerTest.A1, UserTest.SANJIGI));

    @Test
    void 동일한작성자() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        answers.areOwner(UserTest.JAVAJIGI);
    }

    @Test
    void 다른작성자가있으면_예외() {
        assertThatThrownBy(() -> {
            AS1.areOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 모두삭제() {
        List<DeleteHistory> result = AS1.deleteAll();
        assertThat(result).containsAll(AS1DELETE);
    }
}
