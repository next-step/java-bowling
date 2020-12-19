package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 유저가 본인이 아닐 경우 예외 처리")
    void checkLoginUserSame() {
        assertThrows(CannotDeleteException.class,
                () -> Q1.checkLoginUser(UserTest.SANJIGI));
    }

    @Test
    @DisplayName("delete 표시하기")
    void setDeletedTrue() {
        Question question = Q1.setDeletedTrue();
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제하기")
    void delete() throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, UserTest.JAVAJIGI.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));

        DeleteHistories delete = Q1.delete(UserTest.JAVAJIGI);
        assertThat(delete.getFirstUser()).isEqualTo(deleteHistories.getFirstUser());
    }
}
