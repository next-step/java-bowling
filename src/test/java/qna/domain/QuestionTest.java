package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문글의 작성자가 아닐 경우 질문글을 삭제할 수 없다는 예외를 발생시킨다")
    void deleteException_1() throws Exception {
        Assertions.assertThatThrownBy(() -> {
                    Q1.delete(UserTest.SANJIGI);
                }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 답변자가 작성 한 답변이 남아있으면 질문을 삭제할 수 없다")
    void deleteException_2() throws Exception {
        // given
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Q1.delete(UserTest.JAVAJIGI);
                }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변이 없는 상태이고, 편집자가 질문글의 작성자일 경우 삭제할 수 있다")
    void deleteSuccess_1() throws Exception {
        // given
        Assertions.assertThat(Q1.isDeleted()).isFalse();

        // when
        List<DeleteHistory> delete = Q1.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("편집자가 질문자이고, 질문의 질문자와 답변자가 같은 경우는 질문을 삭제할 수 있다")
    void deleteSuccess_2() throws Exception {
        // given
        Q1.addAnswer(AnswerTest.A1);

        // when
        List<DeleteHistory> delete = Q1.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(delete.size()).isEqualTo(2); // 삭제된 내역 질문 1개 + 답변 1개
    }

    @Test
    @DisplayName("질문글이 삭제되면 모든 답변글도 삭제 상태로 변경되어야 한다")
    void deleteSuccess_3() throws Exception {
        // given
        Q1.addAnswer(AnswerTest.A1);
        Assertions.assertThat(Q1.isAnswersAllDeleted()).isFalse();

        // when
        Q1.delete(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(Q1.isAnswersAllDeleted()).isTrue();
    }
}
