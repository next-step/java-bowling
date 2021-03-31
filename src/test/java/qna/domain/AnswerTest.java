package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("답변")
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("작성자 확인")
    public void isAnswer() throws Exception {
        //given
        User javajigi = UserTest.JAVAJIGI;
        User sanjigi = UserTest.SANJIGI;

        //when

        //then
        assertThat(A1.isOwner(javajigi)).isTrue();
        assertThat(A2.isOwner(sanjigi)).isTrue();
    }

    @Test
    @DisplayName("작성자 null 에러 확인")
    public void writerIsNullException() throws Exception {
        //given
        //when
        assertThatThrownBy(() -> {
                new Answer(null, QuestionTest.Q1, "Answers Contents1");
        }).isInstanceOf(UnAuthorizedException.class);
        //then

    }

    @Test
    @DisplayName("게시글 null 에러 확인")
    public void questionIsNullException() throws Exception {
        //given

        //when
        assertThatThrownBy(() -> {
            new Answer(UserTest.JAVAJIGI, null, "Answers Contents1");
        }).isInstanceOf(NotFoundException.class);
        //then
    }

    @Test
    @DisplayName("삭제여부 변경 테스트")
    public void setDelete() throws Exception {
        //given
        boolean isDelete01 = true;
        boolean isDelete02 = false;

        //when
        A1.setDeleted(isDelete01);
        A2.setDeleted(isDelete02);

        //then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isFalse();
    }
}
