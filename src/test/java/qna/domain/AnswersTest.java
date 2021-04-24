package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private Answers singleAnswers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A1));
    private Answers mixedAnswers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    @DisplayName("본인의 댓글들은 삭제 할 수 있다")
    @Test
    void answersDeleteTest() {
        List<DeleteHistory> expectDeleteHistories = Arrays.asList(
                AnswerTest.A1.delete(UserTest.JAVAJIGI),
                AnswerTest.A1.delete(UserTest.JAVAJIGI));
        assertThat(expectDeleteHistories).isEqualTo(singleAnswers.deleteBy(UserTest.JAVAJIGI));
    }

    @DisplayName("타인의 댓글은 삭제 할 수 없다")
    @Test
    void answersDeleteExceptionTest() {
        assertThatThrownBy(() -> mixedAnswers.deleteBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
