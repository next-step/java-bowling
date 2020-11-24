package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AnswersTest {
    private Answer A1;
    private Answer A2;
    private Answers answers;

    @BeforeEach
    void setUp() {
        A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        answers = new Answers();
        answers.add(A1);
        answers.add(A2);
    }

    @Test
    @DisplayName("추가된 answer 가 toString 통해 나타나야 한다.")
    void add() {
        assertThat(answers.toString())
                .isEqualTo("[Answer [id=null, writer=User [userId=javajigi, password=password, name=name, email=javajigi@slipp.net], contents=Answers Contents1],"
                        + " Answer [id=null, writer=User [userId=javajigi, password=password, name=name, email=javajigi@slipp.net], contents=Answers Contents2]]");
    }

    @Test
    @DisplayName("다른 유저의 answer 가 있으면, CannotDeleteException 이 발생한다.")
    void checkDeletable() {
        assertAll(
                () -> assertDoesNotThrow(() -> answers.checkDeletable(UserTest.JAVAJIGI)),
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> answers.checkDeletable(UserTest.SANJIGI))
                        .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertDoesNotThrow(() -> answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "SANJIGI Contents"))),
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> answers.checkDeletable(UserTest.JAVAJIGI))
                        .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("delete 후에는 모든 answer 의 isDeleted 가 true 가 되어야 한다.")
    void delete() {
        assertAll(
                () -> assertThat(A1.isDeleted())
                        .isFalse(),
                () -> assertThat(A2.isDeleted())
                        .isFalse(),
                () -> assertDoesNotThrow(() -> answers.delete(UserTest.JAVAJIGI)),
                () -> assertThat(A1.isDeleted())
                        .isTrue(),
                () -> assertThat(A2.isDeleted())
                        .isTrue()
        );
    }
}
