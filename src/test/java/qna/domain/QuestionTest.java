package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    // 삭제할 수 있는지 확인

    @Test
    @Order(1)
    @DisplayName("작성자 본인이 질문을 삭제 상태로 변경할 수 있는지 테스트")
    public void setDeletedTest1(){
        assertDoesNotThrow(() -> Q1.setDeleted(UserTest.JAVAJIGI));
    }

    @Test
    @Order(2)
    @DisplayName("작성자가 아닌 유저가 질문을 삭제할 경우 예외가 발생하는지 테스트")
    public void setDeletedTest2(){
        assertThrows(CannotDeleteException.class, () -> Q1.setDeleted(UserTest.SANJIGI));
    }

    @Test
    @Order(3)
    @DisplayName("질문과 답변글의 작성자가 동일할 때 작성자 본인이 질문을 삭제할 수 있는지 테스트")
    public void setDeletedTest3(){
        Q1.addAnswer(AnswerTest.A1);
        assertDoesNotThrow(() -> Q1.setDeleted(UserTest.JAVAJIGI));
    }

    @Test
    @Order(4)
    @DisplayName("질문의 작성자와 다른 유저가 답변글을 달은 질문을 작성자 본인이 삭제할 때 예외가 발생하는지 테스트")
    public void setDeletedTest4(){
        Q1.addAnswer(AnswerTest.A2);
        assertThrows(CannotDeleteException.class, () -> Q1.setDeleted(UserTest.JAVAJIGI));
    }

}
