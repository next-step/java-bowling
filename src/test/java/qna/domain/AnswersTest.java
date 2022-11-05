package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    @Test
    void 질문자와_답변자가_다른_경우_삭제_불가() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문자와_답변자가_같은_경우_삭제_가능() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);

        answers.delete(UserTest.JAVAJIGI);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }
}
