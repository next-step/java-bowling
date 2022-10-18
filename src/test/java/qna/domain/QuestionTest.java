package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.answer;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete() throws CannotDeleteException {
        Question question = question();
        Answer answer = answer();
        question.addAnswer(answer);

        List<DeleteHistory> result = question.delete(UserTest.JAVAJIGI);

        List<DeleteHistory> expected = List.of(
                new DeleteHistory(ContentType.QUESTION, 100L, UserTest.JAVAJIGI, null),
                new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, null));
        assertThat(result).isEqualTo(expected);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    private Question question() {
        return (Question) new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI).setId(100L);
    }
}
