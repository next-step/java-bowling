package qna.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Nested
    class delete_메서드는 {

        @Test
        void 삭제처리후_삭제기록을_리턴한다() {
            DeleteHistory deleteHistory = A1.delete();

            assertThat(A1.isDeleted()).isTrue();
            assertThat(deleteHistory).isEqualTo(
                    new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now())
            );
        }
    }
}
