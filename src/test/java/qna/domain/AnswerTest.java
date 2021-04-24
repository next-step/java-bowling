package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void checkIsOwner_다른_사람이_쓴_댓글() throws Exception {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            A1.checkIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            A2.checkIsOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteAnswer_같은_사람이_쓴_댓글() throws Exception {
        // given
        Optional<DeleteHistory> expectDeleteHistoryA1 = Optional.of(new DeleteHistory(A1, UserTest.JAVAJIGI));
        Optional<DeleteHistory> expectDeleteHistoryA2 = Optional.of(new DeleteHistory(A2, UserTest.SANJIGI));
        // when
        Optional<DeleteHistory> resultDeleteHistoryA1 = A1.deleteAnswer(UserTest.JAVAJIGI);
        Optional<DeleteHistory> resultDeleteHistoryA2 = A2.deleteAnswer(UserTest.SANJIGI);
        // then
        Assertions.assertThat(resultDeleteHistoryA1).isEqualTo(expectDeleteHistoryA1);
        Assertions.assertThat(resultDeleteHistoryA2).isEqualTo(expectDeleteHistoryA2);
    }

    @Test
    public void deleteAnswer_다른_사람이_쓴_댓글() {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            A1.checkIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            A2.checkIsOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
