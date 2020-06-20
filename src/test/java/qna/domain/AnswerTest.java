package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 삭제하면 답변 기록을 반환하고 검증한다.")
    void deleteByUser() throws CannotDeleteException {
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());

        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
        assertThat(deleteHistory).isEqualTo(expected);
    }

    @Test
    @DisplayName("자기 자신이 아닌 다른 유저가 답변을 삭제하면 예외를 발생한다.")
    void deleteByUserException(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.delete(A2.getWriter()));
    }
}
