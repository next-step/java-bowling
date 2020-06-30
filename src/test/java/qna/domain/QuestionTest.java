package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문자가 일치 하는지 확인")
    @Test
    public void writerTest() {
        // given
        Question question = Q1;
        User writer = UserTest.JAVAJIGI;

        // when
        boolean result = question.isOwner(writer);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("질문자와 삭제하려는 사용자가 다를 경우 삭제 불가능")
    @Test
    public void doNotDeleteTest() {
        // given
        Question question = Q1;
        User notWriter = UserTest.SANJIGI;

        // when & then
        assertThatThrownBy(() -> {
            question.delete(notWriter);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 삭제하려는 사용자와 같을 경우 삭제")
    @Test
    public void deleteTest() {
        // given
        Question question = Q1;
        User writer = UserTest.JAVAJIGI;

        // when
        question.delete(writer);

        // then
        assertThat(question.isDeleted()).isTrue();
    }


    @DisplayName("답글이 있는 질문일 때, 답변자와 작성자가 다른경우 삭제 불가능")
    @Test
    public void deleteContainAnswerButNotWriterTest() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);;
        User writer = UserTest.JAVAJIGI;
        Answer answer = AnswerTest.A2; // writer is SANJIGI
        question.addAnswer(answer);

        // when & then
        assertThatThrownBy(() -> {
            DeleteHistories histories = question.delete(writer);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답글이 없는 질문일 경우 삭제된 후 히스토리 확인")
    @Test
    public void deleteWithoutAnswerTest() {
        // given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        User writer = UserTest.JAVAJIGI;

        // when
        DeleteHistories histories = question.delete(writer);

        // then
        assertAll(
                () -> assertThat(histories.getHistories().size()).isEqualTo(1),
                () ->assertThat(histories.getHistories().get(0).isOwner(writer)),
                () ->assertThat(histories.getHistories().get(0).isContentType(ContentType.QUESTION))
        );
    }

    @DisplayName("답글이 있는 질문일 경우 삭제된 후 히스토리 확인")
    @Test
    public void deleteContainAnswerTest() {
        // given
        Question question = Q1;
        User writer = UserTest.JAVAJIGI;
        Answer answer = AnswerTest.A1; // writer is JAVAJIGI
        question.addAnswer(answer);

        // when
        DeleteHistories histories = question.delete(writer);

        // then
        assertAll(
                () -> assertThat(histories.getHistories().size()).isEqualTo(2),
                () -> assertThat(histories.getHistories().get(0).isOwner(writer)).isTrue(),
                () -> assertThat(histories.getHistories().get(0).isContentType(ContentType.QUESTION)).isTrue(),
                () -> assertThat(histories.getHistories().get(1).isOwner(writer)).isTrue(),
                () -> assertThat(histories.getHistories().get(1).isContentType(ContentType.ANSWER)).isTrue());
    }
}
