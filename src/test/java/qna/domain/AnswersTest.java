package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {

    @Test
    @DisplayName("로그인 유저와 답변자가 일치할 경우 통과")
    void throwIfOwnerAndLoginUserNotEqual() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(
                new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"))
        );
        answers.checkLoginUserEqualWithAnswersOwners(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("로그인 유저와 답변자가 일치하지 않을 경우 CannotDeleteException")
    void throwCannotDeleteException() {
        Answers answers = new Answers(Arrays.asList(
                new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"))
        );

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.checkLoginUserEqualWithAnswersOwners(UserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}