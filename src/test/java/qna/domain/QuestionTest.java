package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @DisplayName("로그인한 유저가 작성자가 아니면 예외를 발생한다.")
    @Test
    void throwCannotDeleteException() {
        User loginUser = new User();
        loginUser.setUserId("1L");
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(
                () -> Q1.delete(loginUser, LocalDateTime.now())
        );
    }

    @DisplayName("질문글을 삭제할 수 있다.")
    @Test
    void delete() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI, LocalDateTime.now());

        boolean actual = question.isDeleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("질문글을 삭제한 시간을 확인할 수 있다.")
    @Test
    void now() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        LocalDateTime expect = LocalDateTime.of(2019, 4, 11, 1, 52);
        DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI, expect);
        DeleteHistory deleteHistory = deleteHistories.getDeleteHistories().get(0);

        LocalDateTime actual = deleteHistory.getCreateDate();

        assertThat(actual).isEqualTo(expect);
    }
}
