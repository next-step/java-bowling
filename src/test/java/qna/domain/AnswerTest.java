package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제가 잘 되는지 확인")
    @Test
    public void deleteTest() {
        // when
        A1.delete();
        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("삭제 시 History가 잘 반환되는지 확인")
    @Test
    public void deleteHistorytest() {
        // when
        DeleteHistory history = A1.delete();

        // then
        assertAll(
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertThat(history.isContentType(ContentType.ANSWER)).isTrue(),
                () -> assertThat(history.isOwner(UserTest.JAVAJIGI)).isTrue()
        );
    }
}
