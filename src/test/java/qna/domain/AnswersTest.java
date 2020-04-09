package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {
    private Answers answersA1;
    private Answers answersA2;
    private Answers answersA1AndA2;

    @BeforeEach
    void setUp() {
        answersA1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A1, AnswerTest.A1, AnswerTest.A1));
        answersA2 = new Answers(Arrays.asList(AnswerTest.A2, AnswerTest.A2, AnswerTest.A2, AnswerTest.A2));
        answersA1AndA2 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A1, AnswerTest.A2, AnswerTest.A2));
    }

    @DisplayName("삭제 유저가 답변들의 작성자일때 정상동작")
    @Test
    void removableSuccess() {
        assertThatCode(() -> answersA1.delete(UserTest.JAVAJIGI, new ArrayList<>())).doesNotThrowAnyException();
        assertThatCode(() -> answersA2.delete(UserTest.SANJIGI, new ArrayList<>())).doesNotThrowAnyException();
    }

    @DisplayName("삭제 유저가 답변들의 작성자가 아닐 때 throws CannotDeleteException")
    @Test
    void removableFail() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> answersA1AndA2.delete(UserTest.JAVAJIGI, new ArrayList<>()));
    }
}
