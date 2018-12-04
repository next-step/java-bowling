package view.web;

import domain.Bowling;
import domain.BowlingGames;
import domain.Pin;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Created by hspark on 11/11/2018.
 */
public class BowlingWeb {
	public static void main(String[] args) {
		port(8080);
		routes();
	}

	private static void routes() {
		get("/", (req, res) -> render(new HashMap<>(), "/index.html"));
		post("/start", BowlingWeb::start);
		post("/throw", BowlingWeb::bowl);
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private static Object start(Request request, Response response) {
		String[] players = request.queryParams("players").trim().split(",");
		BowlingGames bowlingGames = new BowlingGames(players);
		request.session().attribute("bowlingGames", bowlingGames);

		Bowling bowling = bowlingGames.getCurrentGame();
		Map<String, Object> model = new HashMap<>();

		model.put("currentBowling", bowling);
		model.put("bowlingGames", bowlingGames);
		return render(model, "/play.html");
	}

	private static Object bowl(Request request, Response response) {
		BowlingGames bowlingGames = request.session().attribute("bowlingGames");
		Integer pin = Integer.parseInt(request.queryParams("throwingNo"));

		bowlingGames.bowl(Pin.of(pin));
		Bowling bowling = bowlingGames.getCurrentGame();
		Map<String, Object> model = new HashMap<>();

		model.put("currentBowling", bowling);
		model.put("bowlingGames", bowlingGames);
		model.put("finished", bowlingGames.isAllFinished());
		return render(model, "/play.html");
	}
}
