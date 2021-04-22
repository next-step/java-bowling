package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    public void checkIsOwner_다른사람이_쓴_글() {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            Q1.checkIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            Q1.checkIsOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}

