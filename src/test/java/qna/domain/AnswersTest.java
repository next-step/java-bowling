package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.a1;
import static qna.domain.AnswerTest.a2;

class AnswersTest {
    @Test
    void delete는_answers를_모두_삭제한다() {
        Answers answers = new Answers(List.of(a1(), a1()));

        answers.delete(UserTest.JAVAJIGI);
    }

    @Test
    void delete는_작성자와_사용자가_다를_경우_예외를_발생_시킨다() {
        Answers answers = new Answers(List.of(a1(), a2()));
        
        assertThatThrownBy(() -> {
            answers.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
