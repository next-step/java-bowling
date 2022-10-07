package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.entity.Answer;
import qna.domain.entity.Question;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp(){
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer =  new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
    }
    @Test
    @DisplayName("작성자와 삭제할 사람이 일치하면 삭제")
    void delete_when_writer_equal_delete_user() {
        assertThatNoException()
                .isThrownBy(() -> answer.delete(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("작성자와 삭제할 사람이 일치하지 않으면 CannotDeleteException")
    void delete_when_writer_differ_delete_user() {
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
