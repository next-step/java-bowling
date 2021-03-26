package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 테스트")
    @Test
    void delete() {
        Answer answer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();
        DeleteHistory expect = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteDate);
        DeleteHistory actual = answer.delete(deleteDate);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expect);
    }

}
