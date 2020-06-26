package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("delete 권한 성공")
    @Test
    public void checkOwner() {
        assertThatCode(() -> Q1.checkOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> Q2.checkOwner(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("delete 권한 실패")
    @Test
    public void checkOwnerFail() {
        assertThatThrownBy(() -> {
            Q1.checkOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            Q2.checkOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제상태")
    @Test
    public void deleteOk() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        Q1.delete(deleteHistories, UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
        Q2.delete(deleteHistories, UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

}
