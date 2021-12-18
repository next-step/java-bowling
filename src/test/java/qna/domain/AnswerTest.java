package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 권한 유무(주인X) -> CannotDeleteException 반환")
    void checkAnswersException() {
        assertThatThrownBy(() -> A1.checkDeleteAuth(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 권한 유무(주인O) -> 정상")
    void checkAnswersOK() {
        Assertions.assertThatCode(() -> A1.checkDeleteAuth(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("답변 삭제 -> delete 상태값 확인, 히스토리 안에 저장 유무 확인")
    void saveHistory() {
        DeleteHistories histories = new DeleteHistories();
        A1.delete(histories);
        A2.delete(histories);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
        assertThat(histories.getHistories()).size().isEqualTo(2);
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
        assertThat(histories.getHistories()).contains(new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), LocalDateTime.now()));
    }

}
