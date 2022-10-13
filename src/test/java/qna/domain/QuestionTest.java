package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("작성자 본인이 질문을 삭제 상태로 변경할 수 있는지 테스트")
    public void setDeletedTest1(){
        Question tempQ1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertDoesNotThrow(() -> tempQ1.setDeleted(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("작성자가 아닌 유저가 질문을 삭제할 경우 예외가 발생하는지 테스트")
    public void setDeletedTest2(){
        Question tempQ1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertThrows(CannotDeleteException.class, () -> tempQ1.setDeleted(UserTest.SANJIGI));
    }

    @Test
    @DisplayName("질문과 답변글의 작성자가 동일할 때 작성자 본인이 질문을 삭제할 수 있는지 테스트")
    public void setDeletedTest3(){
        Question tempQ1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer tempA1 = new Answer(UserTest.JAVAJIGI, tempQ1, "Answers Contents1");
        tempQ1.addAnswer(tempA1);

        assertDoesNotThrow(() -> tempQ1.setDeleted(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("질문의 작성자와 다른 유저가 답변글을 달은 질문을 작성자 본인이 삭제할 때 예외가 발생하는지 테스트")
    public void setDeletedTest4(){
        Question tempQ1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer tempA2 = new Answer(UserTest.SANJIGI, tempQ1, "Answers Contents2");
        tempQ1.addAnswer(tempA2);

        assertThrows(CannotDeleteException.class, () -> tempQ1.setDeleted(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("질문을 삭제했을 때 질문과 연관 답글의 상태가 삭제 상태인지 테스트")
    public void setDeletedTest5() throws CannotDeleteException {
        Question tempQ2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        Answer tempA2 = new Answer(UserTest.SANJIGI, tempQ2, "Answers Contents2");
        tempQ2.addAnswer(tempA2);
        tempQ2.setDeleted(UserTest.SANJIGI);

        assertThat(tempQ2.isDeleted()).isTrue();
        tempQ2.getAnswers().getAnswers().forEach(answer->assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("질문을 삭제했을 때 반환되는 히스토리의 개수가 질문 + 답글 개수와 일치하는지 테스트")
    public void setDeletedTest6() throws CannotDeleteException {
        Question tempQ2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        Answer tempA2 = new Answer(UserTest.SANJIGI, tempQ2, "Answers Contents2");
        tempQ2.addAnswer(tempA2);

        assertThat(tempQ2.setDeleted(UserTest.SANJIGI)).hasSize(2);
    }

}
