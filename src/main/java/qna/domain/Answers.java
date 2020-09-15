package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    private void confirmDeletableAnswers(User loginUser) throws CannotDeleteException {
        boolean hasOtherWriter = answers.stream()
                                        .anyMatch(answer -> !answer.isOwner(loginUser));
        if (hasOtherWriter) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public DeleteHistories deleteBy(User loginUser) throws CannotDeleteException {
        confirmDeletableAnswers(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.collectingAndThen(toList(), DeleteHistories::new));
    }

}
