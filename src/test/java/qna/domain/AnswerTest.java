package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람의 작성한 답변은 삭제할 수 없다.")
    public void deleteAnswerByOther() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .withFailMessage("다른 사람이 쓴 답변은 삭제할 수 없습니다.");
    }
}
