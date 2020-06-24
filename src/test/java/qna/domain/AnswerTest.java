package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("delete 요청시 isDeleted가 true로 변경")
    @Test
    public void delete_true() {
        A1.delete();
        A2.delete();

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("recordDeleteHistory시 객체를 정상 생성함")
    @Test
    public void recordDeleteHistory_객체생성() {
        assertThatCode(() -> {
            A1.recordDeleteHistory(ContentType.ANSWER, LocalDateTime.now());
        }).doesNotThrowAnyException();
    }
}
