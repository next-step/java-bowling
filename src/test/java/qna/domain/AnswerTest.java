package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람의 답변을 삭제할 수 없다.")
    public void 다른사람의_답변을_삭제할_수_없다() {
    	assertThatThrownBy(() -> A1.deleteByOthers(UserTest.SANJIGI))
    	.isInstanceOf(CannotDeleteException.class);
    }

}
