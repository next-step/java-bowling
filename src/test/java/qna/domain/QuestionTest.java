package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제할 수 없다")
    void invalidOwner() throws CannotDeleteException {
        User sanjigi = UserTest.SANJIGI;

        assertThrows(CannotDeleteException.class, () -> Q1.delete(sanjigi));
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제가능하다")
    void validOwner() throws CannotDeleteException {
        User javajigi = UserTest.JAVAJIGI;

        assertDoesNotThrow( () -> Q1.delete(javajigi));
        assertTrue(Q1.isDeleted());
    }

    @Test
    @DisplayName("답글이 없는 경우 삭제가능하다")
    void noAnswerDeleteSuccess() throws CannotDeleteException {
        User javajigi = UserTest.JAVAJIGI;

        List<DeleteHistory> deleteHistories = assertDoesNotThrow(() -> Q1.delete(javajigi));
        assertThat(deleteHistories).hasSize(1);
        assertThat(deleteHistories).element(0)
                .hasFieldOrPropertyWithValue("contentType", ContentType.QUESTION)
                .hasFieldOrPropertyWithValue("contentId", Q1.getId())
                .hasFieldOrPropertyWithValue("deletedBy", javajigi);
        assertTrue(Q1.isDeleted());
    }

    @Test
    @DisplayName("질문자와 답글의 작성자가 다른 경우 삭제가 불가능하다")
    void invalidAnswerOwner() {
        User javajigi = UserTest.JAVAJIGI;
        Q1.addAnswer(AnswerTest.A2);

        assertThrows(CannotDeleteException.class, () -> Q1.delete(javajigi));
    }

    @Test
    @DisplayName("질문자와 모든 답글의 작성자가 같은 경우 삭제가능하다")
    void validAnswerOwner() {
        User javajigi = UserTest.JAVAJIGI;
        Answer answer = AnswerTest.A1;
        Q1.addAnswer(answer);

        List<DeleteHistory> deleteHistories = assertDoesNotThrow(() -> Q1.delete(javajigi));

        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories).element(0)
                .hasFieldOrPropertyWithValue("contentType", ContentType.QUESTION)
                .hasFieldOrPropertyWithValue("contentId", Q1.getId())
                .hasFieldOrPropertyWithValue("deletedBy", javajigi);

        assertThat(deleteHistories).element(1)
                .hasFieldOrPropertyWithValue("contentType", ContentType.ANSWER)
                .hasFieldOrPropertyWithValue("contentId", answer.getId())
                .hasFieldOrPropertyWithValue("deletedBy", javajigi);

        assertTrue(Q1.isDeleted());
        assertTrue(answer.isDeleted());
    }


}
