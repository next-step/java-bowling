package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_성공_유저와_글쓴이_같음() throws CannotDeleteException {
        // given
        User loginUser = UserTest.JAVAJIGI;

        // when
        Q1.delete(loginUser);

        // then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_다른_사람이_쓴_글() {
        //given
        User loginUser = UserTest.JAVAJIGI;

        // then
        assertThatThrownBy(() -> {
            Q2.delete(loginUser);
        })
        .isInstanceOf(CannotDeleteException.class)
        .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

}
