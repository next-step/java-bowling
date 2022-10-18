package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.answer;

class AnswersTest {

    @Test
    void delete() throws CannotDeleteException {
        Answer answer = answer();
        Answers answers = new Answers(List.of(answer));

        List<DeleteHistory> result = answers.delete(UserTest.JAVAJIGI);

        List<DeleteHistory> expected = List.of(new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, null));
        assertThat(result).isEqualTo(expected);
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void add() {
        Answers answers = new Answers();

        answers.add(answer());

        assertThat(answers).isEqualTo(new Answers(List.of(answer())));
    }
}
