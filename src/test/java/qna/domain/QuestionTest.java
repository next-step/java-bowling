package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른사람의 글은 삭제할수 없다.")
    public void checkLoginUser() {
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            Q1.checkLoginUser(UserTest.SANJIGI);

            fail("삭제권한 에러가 발생해야 한다.");
        });
    }

    @Test
    @DisplayName("질문을 삭제하면 상태값이 바뀐다.")
    public void setDelete() {
        Q1.setDeleted(true);
        Q2.setDeleted(true);

        Assertions.assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(Q2.isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("삭제된 질문을 저장한다.")
    public void deleteHistory() throws CannotDeleteException {
        List<DeleteHistory> deleteHistory = Q1.deleteQuestion(UserTest.JAVAJIGI);
        assertThat(deleteHistory.size()).isNotZero();
    }

}
