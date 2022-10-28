package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {

    @Test
    void 작성자_다를_경우_일괄_삭제_실패() {
        Answers answers = new Answers(List.of(A2, A2));

        assertThatThrownBy(() -> answers.deleteAll(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변_일괄_삭제() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1, A1));

        List<DeleteHistory> result = answers.deleteAll(JAVAJIGI);

        List<DeleteHistory> excepted = List.of(
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, null),
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, null));


        assertThat(result).isEqualTo(excepted);
    }
}