package qna.global.utils;

import org.junit.jupiter.api.Test;
import qna.domain.AnswerTest;
import qna.domain.QuestionTest;
import qna.domain.UserTest;
import qna.global.exception.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QnaValidationTest {

    @Test
    void delete_글작성자와_로그인유저가_다르면_삭제_불가능_확인() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    QnaValidation.validateQuestionOwner(UserTest.SANJIGI, QuestionTest.Q1);
                });
    }

    @Test
    void delete_글_작성자와_답변_작성자가_다르면_삭제_불가능_확인() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    QnaValidation.validateAnswer(UserTest.SANJIGI, Arrays.asList(AnswerTest.A1));
                });
    }
}
