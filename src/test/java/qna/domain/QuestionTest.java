package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    void 같은지_테스트() throws CannotDeleteException {
        Question question = Q1.isOwner(UserTest.JAVAJIGI);

        assertThat(question.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }

    @Test
    void 생성예외처리() {
        assertThatThrownBy(() -> {
            Q2.isOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 지우기() throws CannotDeleteException{
        Question question = Q1.isOwner(UserTest.JAVAJIGI);
        assertThat(question.ownerValidation(UserTest.JAVAJIGI)).isTrue();
    }

}
