package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @DisplayName("로그인 유저가 아닌 다른 사람이 작성한 답변이 있을경우 예외를 반환한다.")
    @Test
    void checkWriter_예외() {
        Answers answers1 = new Answers(Arrays.asList(AnswerTest.A1));
        Answers answers2 = new Answers(Arrays.asList(AnswerTest.A2));

        assertThatThrownBy(() -> answers1.checkWriter(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> answers2.checkWriter(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 작성한 답변만 있을경우 true 를 반환한다.")
    @Test
    void checkWriter() throws CannotDeleteException {
        Answers answers1 = new Answers(Arrays.asList(AnswerTest.A1));
        Answers answers2 = new Answers(Arrays.asList(AnswerTest.A2));

        assertThat(answers1.checkWriter(UserTest.JAVAJIGI)).isTrue();
        assertThat(answers2.checkWriter(UserTest.SANJIGI)).isTrue();
    }
}
