package controller;

import domain.game.BowlingGame;
import domain.game.BowlingGames;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractController {
    protected static final String INDEX_TEMPLATE_PATH = "index.html";

    protected BowlingGames games;
    protected int currentPlayerIndex;

    public AbstractController() {
    }

    public AbstractController(BowlingGames games, int currentPlayerIndex) {
        this.games = games;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    protected static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    protected static void redirectToIndex(Response response) {
        response.redirect(INDEX_TEMPLATE_PATH);
    }

    protected static String getInputLine(Request req, String key) {
        String inputLine = req.queryParams(key);
        if (StringUtils.isEmpty(inputLine)) {
            throw new IllegalArgumentException("잘못된 입력값입니다.");
        }

        return inputLine;
    }

    protected Map<String, Object> putAndGetModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("games", games);
        model.put("currentPlayerName", getCurrentPlayerName());

        int currentFrameNumber = getCurrentFrameNumber();
        model.put("currentFrameNumber", currentFrameNumber);
        model.put("currentBowl", getCurrentBowl(currentFrameNumber));

        return model;
    }

    private String getCurrentPlayerName() {
        return games.get(currentPlayerIndex).getPlayerName();
    }

    private int getCurrentFrameNumber() {
        return Optional.ofNullable(games.get(currentPlayerIndex))
                .map(BowlingGame::getRecentFrame)
                .map(frame -> {
                    if (frame.isFinished() && !frame.isLastFrame()) {
                        return frame.getNumber() + 1;
                    }

                    return frame.getNumber();
                })
                .orElse(1);
    }

    private int getCurrentBowl(int currentFrameNumber) {
        return Optional.ofNullable(games.get(currentPlayerIndex))
                .map(game -> {
                    if (game.getFramesSize() < currentFrameNumber) {
                        return 1;
                    }

                    return game.getFrame(currentFrameNumber - 1).getPinsSize() + 1;
                })
                .orElse(1);
    }

    protected int getNextPlayerIndex() {
        return (currentPlayerIndex + 1) % games.size();
    }
}
