package qna.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    private Answer answer1;
    private Answer answer2;
    private Answer answer3;

    @Before
    public void setUp() {
        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        answer3 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");
    }

    @Test
    public void isOwner_참() {
        final Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void isOwner_거짓() {
        final Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    public void delete() {
        final Answers answers = new Answers(Arrays.asList(answer1, answer2));

        final List<DeleteHistory> deleteHistories = answers.delete();

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