package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswersTest {

    Answers answers;
    Answer answerOne;
    Answer answerTwo;

    @BeforeEach
    void setUp() {
        answers = new Answers();
        answerOne = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerTwo = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

        answers.add(answerOne);
    }

    @Test
    @DisplayName("생성자 테스트")
    void sameAnswers() {
        assertAll(
                () -> assertThat(new Answers()).isEqualTo(new Answers()),
                () -> assertThat(new Answers(new ArrayList<>())).isEqualTo(new Answers())

        );
    }

    @Test
    @DisplayName("answer add 테스트")
    void add() {
        assertAll(
                () -> assertThat(answers.size()).isEqualTo(1),
                () -> assertThat(answers.answers()).contains(answerOne)
        );
    }

    @Test
    @DisplayName("답변 전체 삭제 테스트")
    void deleteAll() {
        DeleteHistories deleteHistories = answers.deleteAllAnswers(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(answerOne.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories.deleteHistories()).hasSize(1)
        );
    }

    @Test
    @DisplayName("답변 전체 삭제 예외 테스트")
    void deleteAllException() {
        answers.add(answerTwo);

        assertThatThrownBy(() -> answers.deleteAllAnswers(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.QUESTION_WITH_OTHERS_ANSWER);
    }

}
