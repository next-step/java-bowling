package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 불가능")
    @Test
    void delete_isOwner_error(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.SANJIGI));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    @Test
    void delete_isOwner() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI).isDeleted()).isTrue();
    }
}
