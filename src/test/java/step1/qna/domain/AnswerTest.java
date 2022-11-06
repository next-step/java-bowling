package step1.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

public class AnswerTest {

    private Question Q1;

    private Answer A1;
    private Answer A2;

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("다른 사람의 답변이 있는 경우 삭제 시 예외처리")
    @Test
    void other_answer_delete_throw_exception() {
        Assertions.assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
