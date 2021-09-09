package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    @Test
    @DisplayName("답변을 모두 삭제할 수 있다.")
    void deleteAnswersTest() throws CannotDeleteException {

        // given
        List<Answer> inputs = Arrays.asList(AnswerTest.A1);
        Answers answers = new Answers(inputs);
        User writer = UserTest.JAVAJIGI;
        List<DeleteHistory> expected = Arrays.asList(new DeleteHistory(AnswerTest.A1));

        // when
        List<DeleteHistory> results = answers.deleteAll(writer);

        // then
        assertThat(results).isEqualTo(expected);
    }

}