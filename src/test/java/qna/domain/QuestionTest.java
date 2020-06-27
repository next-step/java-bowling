package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인유저와 글쓴이가 같으면 삭제 후 삭제기록을 반환한다.")
    @Test
    void success() {
        //when
        List<DeleteHistory> deleteHistories = Q1.deleteBy(UserTest.JAVAJIGI);

        //then
        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories.size()).isEqualTo(1)
        );
    }

    @DisplayName("로그인유저와 글쓴이가 다르면 익셉션")
    @Test
    void exception() {
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
