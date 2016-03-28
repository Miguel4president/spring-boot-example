package leaderboard.jpaRepos;

import leaderboard.entities.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by aaron on 3/27/16.
 */
public interface HighscoreRepo extends JpaRepository<Highscore, Long> {
  List<Highscore> findByUsername (String username);
}
