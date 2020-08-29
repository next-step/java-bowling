package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.fixture.QuestionFixture;
import qna.fixture.UserFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    @DisplayName("답변이 잘 생성되는지 확인")
    @Test
    public void answerTest() {
        // given
        String answerContent = "Contents of Answer";
        User owner = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        Question question = QuestionFixture.make(1L, "Title", "Content", owner);

        // when
        Answer answer = new Answer(1L, owner, question, answerContent);

        // then
        assertAll(
                () -> assertThat(answer.isOwner(owner)).isTrue(),
                () -> assertThat(answer.getContents()).isEqualTo(answerContent),
                () -> assertThat(answer.isDeleted()).isFalse(),
                () -> assertThat(answer.getId()).isEqualTo(1L)
        );
    }

    @DisplayName("작성자와 답변이 다를 경우 삭제 할 때 예외 발생")
    @Test
    public void makeDeleteThrownTest() {
        // given
        String answerContent = "Contents of Answer";
        User owner = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        User other = UserFixture.make(2L, "userId2", "Password2", "Name2", "Email2");
        Question question = QuestionFixture.make(1L, "Title", "Content", owner);

        // when
        Answer answer = new Answer(1L, owner, question, answerContent);

        // then
        assertThatThrownBy(() -> answer.makeDeleted(other))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("답변을 삭제할 권한이 없습니다");
    }

    @DisplayName("작성자와 답변이 같을 경우 삭제")
    @Test
    public void makeDeleteTest() {
        // given
        String answerContent = "Contents of Answer";
        User owner = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        Question question = QuestionFixture.make(1L, "Title", "Content", owner);

        // when
        Answer answer = new Answer(1L, owner, question, answerContent);
        answer.makeDeleted(owner);

        // then
        assertThat(answer.isDeleted()).isTrue();
    }
}
