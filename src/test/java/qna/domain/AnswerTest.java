package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(100L, UserTest.JAVAJIGI, QuestionTest.Q2, "JPA Answer");
    }

    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우, 답변 삭제 불가능 해야 한다")
    @Test
    void delete_differentUser() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answer.delete(UserTest.SANJIGI))
            .withMessageContaining("답변을 삭제할 권한이 없습니다");
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우, 답변만 삭제 가능 해야 한다")
    @Test
    void delete_sameUser() throws CannotDeleteException {
        assertThat(answer.isDeleted()).isFalse();

        final DeleteHistory deleteHistory = answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(
            new DeleteHistory(ContentType.ANSWER, 100L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }


}
