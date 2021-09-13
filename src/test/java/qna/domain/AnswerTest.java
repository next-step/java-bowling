package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 목록 삭제 완료")
    void delete() {
        DeleteHistory deleteHistory = A1.delete();
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null , UserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(A1.isDeleted()).isTrue();
    }
}
