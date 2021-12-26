package qna.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다른사람이 작성한 Answer를 삭제할 수 없다.")
    @Test
    void cannotDeleteAnswerIfNotAuthor() {
        // given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        ThrowableAssert.ThrowingCallable callable = () -> answer.delete(UserTest.SANJIGI);

        // then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(callable)
                .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("자신이 작성한 Answer를 삭제할 수 있다.")
    @Test
    void canDeleteAnswer() {
        // given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        answer.delete(UserTest.JAVAJIGI);

        // then
        assertThat(answer.isDeleted()).isTrue();
    }
}
