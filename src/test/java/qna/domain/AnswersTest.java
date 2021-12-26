package qna.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

public class AnswersTest {

    @DisplayName("Answers에 포함된 모든 Answer들의 작성자가 여러명인 경우 삭제할 수 없다.")
    @Test
    void cannotDeleteAnswersIfNotAuthor() {
        // given
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        // when
        ThrowableAssert.ThrowingCallable callable = () -> answers.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(callable)
                .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
