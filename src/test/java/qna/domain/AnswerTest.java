package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제")
    public void deleteSelf() {
        LocalDateTime deleteDate=LocalDateTime.now();
        DeleteHistory deleteHistory = A1.deleteSelf(deleteDate);

        DeleteHistory deleteHistoryExpected = new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, deleteDate);
        assertThat(deleteHistory).isEqualTo(deleteHistoryExpected);
    }
}
