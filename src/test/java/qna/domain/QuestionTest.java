package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_질문삭제권한_예외() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete_본인글삭제() {
        assertThat(Q2.isDeleted()).isFalse();
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void delete_삭제이력() {
        assertThat(Q1.delete(UserTest.JAVAJIGI).get(0)).isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

}
