package leaderboard.endpoints;

import leaderboard.entities.Highscore;
import leaderboard.jpaRepos.HighscoreRepo;
import leaderboard.pojos.DefaultRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by aaron on 3/27/16.
 */
@RestController
@RequestMapping("/highscore")
public class HighscoreController {

  private final AtomicLong counter = new AtomicLong();
  private HighscoreRepo repo;

  @Autowired
  public HighscoreController(HighscoreRepo repo) {
    this.repo = repo;
    Highscore test = new Highscore("shamshirz", "HoN", 42);
    repo.save(test);
  }

  // TODO: Make the apiKey direct you to a different DB
  @RequestMapping(value = "", method = GET)
  public Object getHighscores(@RequestParam(value="apiKey", defaultValue = "", required = false) String apiKey) {
    List<Highscore> allHighscores = repo.findAll();
    System.out.println(apiKey.isEmpty() ? "No ApiKey provided" : "ApiKey: "+apiKey);
    return allHighscores != null ? allHighscores : new DefaultRestResponse(true, "No scores found.");
  }

  @RequestMapping(value = "", method = PUT)
  public Object addHighscore(@RequestBody Highscore highscore) {
    if (highscore != null) {
      Highscore savedHs = repo.save(highscore);
      return savedHs;
    }
    return new DefaultRestResponse(true, "Expected Highscore JSON, but didn't find one.");
  }

  @RequestMapping(value = "/user/{username}", method = GET)
  public Object getUserHighscores(@PathVariable String username) {
    List<Highscore> userHighscores = repo.findByUsername(username);
    return userHighscores != null ? userHighscores : new DefaultRestResponse(true, "No scores found for user: "+username);
  }

  //  This can be put into a controller advice file in this directory, but this was easier for the example
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public DefaultRestResponse handleMissingParameter() {
    return new DefaultRestResponse(true, "Invalid JSON.");
  }



}
