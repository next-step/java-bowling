package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.fixture.UserFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QuestionTest {
    @DisplayName("질문이 잘 생성 되는지 확인")
    @Test
    public void questionTest() {
        // given
        Long questionId = 1L;
        String title = "Title";
        String contents = "Contents";
        User writer = UserFixture.make(1L, "userId", "Password", "Name", "Email");

        // when
        Question question = new Question(questionId, title, contents).writeBy(writer);

        // then
        assertAll(
                () -> assertThat(question.getId()).isEqualTo(questionId),
                () -> assertThat(question.getTitle()).isEqualTo(title),
                () -> assertThat(question.getContents()).isEqualTo(contents),
                () -> assertThat(question.isOwner(writer)).isTrue(),
                () -> assertThat(question.isDeleted()).isFalse()
        );
    }

    @DisplayName("질문자와 삭제하려는 사용자가 같을 경우 삭제")
    @Test
    public void makeDeletedTest() {
        // given
        Long questionId = 1L;
        String title = "Title";
        String contents = "Contents";
        User writer = UserFixture.make(1L, "userId", "Password", "Name", "Email");

        // when
        Question question = new Question(questionId, title, contents).writeBy(writer);

        // then
        assertDoesNotThrow(() -> question.makeDeleted(writer));
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 삭제하려는 사용자가 다를 경우 예외 발생")
    @Test
    public void makeDeletedThrownTest() {
        // given
        Long questionId = 1L;
        String title = "Title";
        String contents = "Contents";
        User writer = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        User other = UserFixture.make(2L, "userId", "Password", "Other name", "Other email");

        // when
        Question question = new Question(questionId, title, contents).writeBy(writer);

        // then
        assertThatThrownBy(() -> question.makeDeleted(other))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }
}
