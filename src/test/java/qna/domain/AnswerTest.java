package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer 정보를 DeleteHistory로 반환")
    public void infoTest() {
        assertThat(A1.info()).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
