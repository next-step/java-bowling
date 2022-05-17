package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @DisplayName("질문 작성자가 아닌 유저가 질문을 삭제하려하면 예외가 발생한다.")
    @Test
    void checkDeletePermissions() {
        assertThatThrownBy(() -> Q1.checkDeletePermissions(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteAndAddHistories() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);
        DeleteHistories deleteHistories = new DeleteHistories();

        DeleteHistories addedDeleteHistories = question.deleteAndAddHistories(deleteHistories);

        assertAll(
            () -> assertThat(question.isDeleted()).isTrue(),
            () -> assertThat(answer1.isDeleted()).isTrue(),
            () -> assertThat(answer2.isDeleted()).isTrue(),
            () -> assertThat(addedDeleteHistories.size()).isEqualTo(3)
        );
    }
}
