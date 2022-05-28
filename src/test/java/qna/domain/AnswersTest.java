package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    void 답변_일괄_삭제() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2");
        List<Answer> answers = List.of(answer1, answer2);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer2.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        assertThat(new Answers(answers).delete(UserTest.JAVAJIGI)).isEqualTo(deleteHistories);
    }

    @Test
    void 작성자가_다를_경우_일괄_삭제_실패() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2");
        List<Answer> answers = List.of(answer1, answer2);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.SANJIGI, LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer2.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        assertThatThrownBy(() -> new Answers(answers).delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
