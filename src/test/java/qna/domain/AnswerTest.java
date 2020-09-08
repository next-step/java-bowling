package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제시 삭제 상태값 변경 확인")
    void deleteAnswerState() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제시 삭제 기록 생성")
    void deleteAnswerAndCreateDeleteHistory() {
        DeleteHistory deleteHistory = A1.delete();
        assertThat(deleteHistory).isNotNull();
    }

}
