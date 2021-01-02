package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제하면 삭제 flag 만 false 로 바꾼다")
    @Test
    void delete(){
        Answer answer = new Answer(1L, UserTest.SEHAN, QuestionTest.Q1, "content");
        answer.delete(UserTest.SEHAN);
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("삭제하면 DeleteHistory 객체를 return 한다")
    @Test
    void deleteHistory(){
        Answer answer = new Answer(1L, UserTest.SEHAN, QuestionTest.Q1, "content");
        assertThat(answer.delete(UserTest.SEHAN)).isEqualTo(
                DeleteHistory.ofAnswer(1L, UserTest.SEHAN)
        );
    }

}
