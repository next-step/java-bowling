package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 요청한 유저가 답변글 작성 유저가 아닐 경우 Exception이 발생해야 한다.")
    void failByLoginAndAnswerIdDismatchTest() {

        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.deleteAnswer(UserTest.SANJIGI))
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("삭제가 가능한 상황이면 삭제 후 DeleteHistory를 반환한다.")
    void successDeleteAnswerTest() throws CannotDeleteException {

        // given
        User writer = QuestionTest.Q1.getWriter();
        DeleteHistory expected = new DeleteHistory(A1);

        // when
        DeleteHistory result = A1.deleteAnswer(writer);

        // then
        assertAll(
            () -> assertThat(result).isEqualTo(expected),
            () -> assertTrue(A1.isDeleted())
        );

    }

}
