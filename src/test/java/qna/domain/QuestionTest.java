package qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        List<DeleteHistory> expected = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistories).isEqualTo(expected);
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() ->
            question.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        question.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answer Contents2"));
        assertThatThrownBy(() ->
            question.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
