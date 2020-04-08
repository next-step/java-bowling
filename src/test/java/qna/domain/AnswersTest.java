package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @DisplayName("Answers 생성 테스트")
    @Test
    public void generateAnswersTest() {
        assertThatCode(Answers.newInstance(AnswerTest.A1, AnswerTest.A2)).doesNotThrowAnyException();
    }

    @DisplayName("Answers 전부 삭제 테스트")
    @Test
    public void generateAnswersTest() {
        Answers answers = Answers.newInstance(AnswerTest.A1, AnswerTest.A1);
        assertThatCode(answers.deleteAll(UserTest.JAVAJIGI)).doesNotThrowAnyException();

        Answers cannotDeleteAnswers = Answers.newInstance(AnswerTest.A1, AnswerTest.A2);
        assertThatThrownBy(answers.deleteAll(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
