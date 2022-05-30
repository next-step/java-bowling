package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = Answer.of(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = Answer.of(2L,UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete_성공() throws Exception {
        assertThat(A1.isDeleted()).isFalse();
        DeleteHistory deleteHistory = A1.deleted(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            A2.deleted(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
