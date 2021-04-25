package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
        DeleteHistory expectDeleteHistoryA1 = new DeleteHistory(A1, UserTest.JAVAJIGI);
        DeleteHistory expectDeleteHistoryA2 = new DeleteHistory(A2, UserTest.SANJIGI);
        // when
        DeleteHistory resultDeleteHistoryA1 = A1.deleteAnswer(UserTest.JAVAJIGI);
        DeleteHistory resultDeleteHistoryA2 = A2.deleteAnswer(UserTest.SANJIGI);
        // then
        Assertions.assertThat(resultDeleteHistoryA1).isEqualTo(expectDeleteHistoryA1);
        Assertions.assertThat(resultDeleteHistoryA2).isEqualTo(expectDeleteHistoryA2);
        Assertions.assertThat(A1.isDeleted()).isTrue();
        Assertions.assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    public void deleteAnswer_다른_사람이_쓴_댓글() {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            A1.deleteAnswer(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            A2.deleteAnswer(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    public void deleteAnswers_질문자가_같은_경우() throws Exception {
        // given
        DeleteHistories expectDeleteAnswers = new DeleteHistories();
        expectDeleteAnswers.add(new DeleteHistory(AnswerTest.A1, UserTest.JAVAJIGI));
        expectDeleteAnswers.add(new DeleteHistory(AnswerTest.A1, UserTest.JAVAJIGI));
        Answers answers = new Answers(Arrays.asList(A1, A1));

        // when
        DeleteHistories resultDeleteAnswers = answers.deleteAnswers(UserTest.JAVAJIGI);
        // then
        assertThat(resultDeleteAnswers).isEqualTo(expectDeleteAnswers);
    }

    @Test
    public void deleteAnswers_질문이_없는_경우() throws Exception {
        // given
        Answers answers = new Answers();

        // when
        DeleteHistories resultDeleteAnswers = answers.deleteAnswers(UserTest.JAVAJIGI);

        // then
        assertThat(resultDeleteAnswers.isEmpty()).isTrue();
    }

}
