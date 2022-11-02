package step1.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

import java.util.Arrays;

class AnswersTest {

    @DisplayName("여러 답변 중 하나라도 다른 사람이 쓴 답변이 있으면 예외 처리")
    @Test
    void several(){
        Answers answers = Answers.from(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        Assertions.assertThatThrownBy(() -> answers.validateAnswersAuthentication(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
