package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {


    @Test
    @DisplayName("답변 삭제")
    public void deleteSelf() {
        User user = new User();
        Answer answer = new Answer(user, new Question().writeBy(user), "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();

        DeleteHistory deleteHistory = answer.deleteSelf(deleteDate);

        DeleteHistory deleteHistoryExpected = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteDate);
        assertThat(deleteHistory).isEqualTo(deleteHistoryExpected);
    }
}
