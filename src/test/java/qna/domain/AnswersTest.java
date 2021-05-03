package qna.domain;

import org.junit.Before;
import org.junit.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    private Answer answer1;
    private Answer answer2;

    @Before
    public void setUp() {
        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    public void delete() throws CannotDeleteException {
        final Answers answers = new Answers(Arrays.asList(answer1, answer2));

        final List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        verify(deleteHistories);
    }

    private void verify(List<DeleteHistory> deleteHistories) {
        final List<DeleteHistory> expected = Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now())
        );

        assertThat(deleteHistories).isEqualTo(expected);
    }
}