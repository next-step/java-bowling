package qna.domain;

import org.junit.Before;
import org.junit.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;

    @Before
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        final List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        verify(deleteHistories);
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        final List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verify(deleteHistories);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            final Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
            question.addAnswer(answer);

            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verify(List<DeleteHistory> deleteHistories) {
        final List<DeleteHistory> expected = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

        assertThat(deleteHistories).isEqualTo(expected);
    }
}
