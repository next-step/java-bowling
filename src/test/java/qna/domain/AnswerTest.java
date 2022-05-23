package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    private Answer answer;

    @BeforeEach
    public void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("답변이 삭제되면 삭제 상태 값이 true 된다.")
    public void delete() {
        assertThat(answer.deleted()).isFalse();
        answer.delete();
        assertThat(answer.deleted()).isTrue();
    }
}
