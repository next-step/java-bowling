package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    private Answer answer;

    @BeforeEach
    public void setUp() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @Test
    @DisplayName("답변이 삭제되면 삭제 상태 값이 true 된다.")
    public void delete() {
        answer.delete();
        assertThat(answer.deleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 없는 질문이 삭제되었을때 삭제이력은 질문만 포함한다.")
    public void deleteHistory() {
        answer.delete();
        assertThat(answer.deleteHistory()).isEqualTo(new DeleteHistory(ContentType.ANSWER, 11L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
