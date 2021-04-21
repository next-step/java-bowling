package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;
import qna.exception.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    Answers answers;
    Answer answer;

    @BeforeEach
    void setUp() {
        answers = new Answers();
        answer = TestFixture.A1;

        answers.add(answer);
    }

    @Test
    @DisplayName("기본생성자로 생성된 Answers는 서로 같은 상태다.")
    void create() {
        assertThat(new Answers()).isEqualTo(new Answers());
    }

    @Test
    @DisplayName("기본생성자로 생성된 Answers와 빈 List로 생성한 Answers는 서로 같은 상태다.")
    void create_with_emptyList() {
        assertThat(new Answers(new ArrayList<>())).isEqualTo(new Answers());
    }

    @Test
    @DisplayName("answers에 answer를 추가할 수 있다.")
    void add() {
        assertAll(
                () -> assertThat(answers.size()).isEqualTo(1),
                () -> assertThat(answers.answers()).contains(answer)
        );
    }

    @Test
    @DisplayName("답변 전체 삭제를 한다.")
    void deleteAll() {
        // given
        // when
        final DeleteHistories deleteHistories = answers.deleteAll(TestFixture.JAVAJIGI);

        // then
        assertAll(
                () -> assertThat(answer.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories.deleteHistories()).hasSize(1)
        );
    }

    @Test
    @DisplayName("답변 전체 삭제시 다른 사용자가 답변한 경우 예외를 던진다.")
    void deleteAllFailsByDifferentUser() {
        // given
        answers.add(TestFixture.A2);

        // when
        // then
        assertThatThrownBy(() -> answers.deleteAll(TestFixture.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.DIFFERENT_USERS_ANSWER_CONTAINED);
    }
}
