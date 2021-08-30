package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("글의 글쓴이인지 검사하는 테스트")
    void isOwnerElseThrowTest() {
        assertThatThrownBy(() -> Q1.isOwnerElseThrow(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("글이 타인의 답변을 가지고있으면 예외를 발생시키는 테스트")
    void hasOnlyOwnAnswersElseThrowTest() {
        Q1.addAnswer(new Answer(UserTest.SANJIGI, Q1, "test answer for Q1"));

        assertThatThrownBy(Q1::hasOnlyOwnAnswersElseThrow)
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("글을 삭제하는 테스트")
    void deleteWithAnswers() {
        Q1.deleteWithAnswers();

        assertThat(Q1.isDeleted()).isTrue();
    }
}
