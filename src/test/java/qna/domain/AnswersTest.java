package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {
    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
    }

    @Test
    @DisplayName("삭제 예외")
    void delete_throw_exception() {
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    answers.delete(UserTest.JAVAJIGI);
                })
                .withMessage("답변을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제 성공")
    void delete() throws CannotDeleteException {
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));

        answers.delete(UserTest.JAVAJIGI);
    }
}