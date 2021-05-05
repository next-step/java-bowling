package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class AnswersTest {

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있으면 오류 발생")
    void checkAnswerInNotOwnerTest() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.checkAnswerInNotOwner(UserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
