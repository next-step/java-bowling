package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_다른_사람이_쓴_글() {
        Question question = new Question(1,"test","content").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI,1);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_같음() throws CannotDeleteException {
        Question question = new Question(1,"test","content").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI,1);
        assertThat(question.isDeleted()).isTrue();
    }

}
