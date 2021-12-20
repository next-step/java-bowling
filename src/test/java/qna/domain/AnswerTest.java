package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문 정상 삭제 확인")
    void delete() {
        assertThat(A1.isDeleted()).isFalse();

        // given & when
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    @DisplayName("질문자와 답변자가 다른경우 답변을삭제할수 없음.")
    void delete_exception() {
        Assertions.assertThrows(CannotDeleteException.class, () -> A2.delete(UserTest.JAVAJIGI));
    }
}
