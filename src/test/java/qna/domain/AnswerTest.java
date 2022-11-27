package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("질문작성자와 답변작성자가 다를 때, 답변과 질문 삭제 안되는 테스트 ")
    public void ownerExceptionTest() throws Exception {

        assertThatThrownBy(() -> {
            A1.checkDelete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}
