package qna.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("deleteProcess()를 통하여 삭제 처리를 진행할 수 있다")
    public void delete_process() throws Exception {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        User loginUser = UserTest.JAVAJIGI;
        //when
        //then
        assertDoesNotThrow(() -> question.deleteProcess(loginUser, new DeleteHistories()));
    }

    @Test
    @DisplayName("삭제 처리를 할 때 Qustion의 작성자가 다를 경우 익셉션이 발생한다")
    public void thrown_is_not_owner_question_when_delete_process() throws Exception {
        //given
        Q1.addAnswer(AnswerTest.A1);
        User loginUser = UserTest.SANJIGI;
        //when
        CannotDeleteException thrown = assertThrows(
            CannotDeleteException.class,
            () -> Q1.deleteProcess(loginUser, new DeleteHistories())
        );
        //then
        assertTrue(thrown.getMessage().contains("질문을 삭제할 권한이 없습니다."));
    }

    @Test
    @DisplayName("삭제 처리를 할 때 Answer의 작성자가 다를 경우 익셉션이 발생한다")
    public void thrown_is_not_owner_answer_when_delete_process() throws Exception {
        //given
        Q1.addAnswer(AnswerTest.A2);
        User loginUser = UserTest.JAVAJIGI;
        //when
        CannotDeleteException thrown = assertThrows(
            CannotDeleteException.class,
            () -> Q1.deleteProcess(loginUser, new DeleteHistories())
        );
        //then
        assertTrue(thrown.getMessage().contains("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."));
    }
}
