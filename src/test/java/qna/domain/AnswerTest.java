package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void setup() {
        answer1 = Answer.create(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = Answer.create(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }


    @Test
    @DisplayName("질문 정상 삭제 확인")
    void delete() {
        assertThat(answer1.isDeleted()).isFalse();

        // given & when
        DeleteHistory deleteHistory = answer1.delete(UserTest.JAVAJIGI);

        // then
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    @DisplayName("질문자와 답변자가 다른경우 답변을삭제할수 없음.")
    void delete_exception() {
        Assertions.assertThrows(CannotDeleteException.class, () -> answer2.delete(UserTest.JAVAJIGI));
    }
}
