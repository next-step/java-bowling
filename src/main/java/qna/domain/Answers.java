package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Answers {
    private static final String EXCEPTION_MESSAGE_OTHER_PERSON_ANSWER_EXIST = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> getDeleteHistories(User loginUser, Question question, LocalDateTime createTime) throws CannotDeleteException {
        validateAnswersDeleted(loginUser);
        return answersDeleteHistories(question.getDeleteHistory(createTime), createTime);
    }

    private List<DeleteHistory> answersDeleteHistories(DeleteHistory questionDeleteHistory, LocalDateTime createTime) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(questionDeleteHistory);
        answers.stream()
                    .map(answer -> answer.getDeleteHistory(createTime))
                    .forEach(deleteHistory -> deleteHistories.add(deleteHistory));
        return deleteHistories;
    }

    private void validateAnswersDeleted(User loginUser) throws CannotDeleteException {
        Optional<Answer> otherWriterAnswer = answers.stream()
                                                        .filter(answer -> !answer.isOwner(loginUser))
                                                        .findFirst();
        if (otherWriterAnswer.isPresent()) {
            throw new CannotDeleteException(EXCEPTION_MESSAGE_OTHER_PERSON_ANSWER_EXIST);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
