package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        // given
        DeleteHistory expected = new DeleteHistory(
                ContentType.ANSWER,
                answer.getId(),
                answer.getWriter(),
                LocalDateTime.now());

        // when
        DeleteHistory deleteHistory = answer.delete(UserTest.JAVAJIGI);

        // then
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(expected);
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
