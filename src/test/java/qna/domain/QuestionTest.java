package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @DisplayName("글의 상태를 삭제로 바꾼다.")
    @Test
    void delete() throws CannotDeleteException {
        assertThat(Q1.deleteByUser(UserTest.JAVAJIGI).isDeleted()).isTrue();
        assertThat(Q2.deleteByUser(UserTest.SANJIGI).isDeleted()).isTrue();
    }

    @DisplayName("글쓴이가 아니면 에러가 발생한다.")
    @Test
    void validWriterException() {
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
