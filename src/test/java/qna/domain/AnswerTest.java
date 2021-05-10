package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer 정보를 DeleteHistory로 반환")
    public void infoTest() {
        assertThat(A1.delete()).isEqualTo(DeleteHistory.createByAnswer(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("삭제가능한지 체크")
    void isPossibleToDelete() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.isPossibleToDelete(UserTest.SANJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
