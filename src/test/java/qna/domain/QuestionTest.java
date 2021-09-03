package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("글의 상태를 삭제로 바꾼다.")
    @Test
    void delete() throws CannotDeleteException {

        assertThat(Q1.isDeleted()).isFalse();

        Q1.deleteByUser(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("글쓴이가 아니면 에러가 발생한다.")
    @Test
    void validWriterException() {
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("글을 삭제하면 삭제기록을 반환한다.")
    @Test
    void successDeleted() throws CannotDeleteException {
        assertThat(Q1.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(ArrayList.class);
        assertThat(Q2.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(ArrayList.class);
    }

    @DisplayName("글을 삭제하면 답변도 같이 삭제된다.")
    @Test
    void deleteWithAnswer() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        Q1.deleteByUser(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @DisplayName("글을 삭제할때, 본인이 작성하지 않은 답변이 있으면 에러가 발생한다.")
    @Test
    void deleteWithAnswerException() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(
                () -> Q1.deleteByUser(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);

    }
}
