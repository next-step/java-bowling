package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;
import qna.domain.mock.TestAnswer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    public void delete_성공() {
        TestAnswer testAnswer = new TestAnswer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertAll(
                () -> assertThatCode(() -> testAnswer.delete(UserTest.JAVAJIGI))
                        .doesNotThrowAnyException(),
                () -> assertThat(testAnswer.isDeleted()).isTrue()
        );
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        User loginUser = UserTest.JAVAJIGI;

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A2.delete(loginUser))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        TestAnswer testAnswer = new TestAnswer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertAll(
                () -> assertThatCode(() -> testAnswer.delete(UserTest.JAVAJIGI))
                        .doesNotThrowAnyException(),
                () -> assertThat(testAnswer.isDeleted()).isTrue()
        );
    }
}
