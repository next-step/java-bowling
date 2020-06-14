package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private Answers A1;
    private Answers A2;

    @BeforeEach
    void setup() {
        A1 = new Answers(Arrays.asList(AnswerTest.createAnswer(UserTest.JAVAJIGI, QuestionTest.createQuestion(UserTest.JAVAJIGI))));
        A2 = new Answers(Arrays.asList(AnswerTest.createAnswer(UserTest.SANJIGI, QuestionTest.createQuestion(UserTest.SANJIGI))));
    }

    @Test
    @DisplayName("답변자와 삭제하는 유저가 동일한 경우")
    void deleteTest() {
        assertThatCode(() -> A1.delete(UserTest.JAVAJIGI, new ArrayList<>()))
                .doesNotThrowAnyException();
        assertThatCode(() -> A2.delete(UserTest.SANJIGI, new ArrayList<>()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("답변자와 삭제자가 다른 경우 Exception")
    void notAvailableDeletedTest() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.delete(UserTest.JAVAJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
    }
}