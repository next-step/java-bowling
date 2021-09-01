package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("글쓴이를 확인한다.")
    @Test
    void validWriter() throws CannotDeleteException {
        Q1.validUserAuthority(UserTest.JAVAJIGI);
        Q2.validUserAuthority(UserTest.SANJIGI);
    }

    @DisplayName("글쓴이가 아니면 에러가 발생한다.")
    @Test
    void validWriterException() {
        assertThatThrownBy(() -> Q1.validUserAuthority(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.validUserAuthority(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
