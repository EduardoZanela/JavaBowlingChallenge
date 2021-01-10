package br.com.eduardozanela.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.eduardozanela.entity.Player;
import br.com.eduardozanela.service.impl.FileReaderServiceImpl;
import br.com.eduardozanela.service.impl.PlayerScoreServiceImpl;
import br.com.eduardozanela.service.impl.PrintServiceImpl;

public class PlayerScoreServiceTest {

	private PlayerScoreService playerScoreService;

	@Before
	public void setUp() {
		playerScoreService = new PlayerScoreServiceImpl(new FileReaderServiceImpl(), new PrintServiceImpl());

	}

	@Test
	public void shouldCalculateZeroScore() {

		List<String> scores = new ArrayList<>();
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		scores.add("Jeff\t0");
		
		List<Player> convertPlayerScore = playerScoreService.convertPlayerScore(scores);
		assertEquals(convertPlayerScore.get(0).getScores().get(convertPlayerScore.get(0).getScores().size()-1).getScore().intValue(), 0);

	}
	
	@Test
	public void shouldCalculatePerfectScore() {

		List<String> scores = new ArrayList<>();
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		scores.add("Jeff\t10");
		
		List<Player> convertPlayerScore = playerScoreService.convertPlayerScore(scores);
		assertEquals(convertPlayerScore.get(0).getScores().get(convertPlayerScore.get(0).getScores().size()-1).getScore().intValue(), 300);

	}
	
	@Test
	public void shouldCalculateTwoPlayersScore() {

		List<String> scores = new ArrayList<>();
		scores.add("Jeff\t10");
		scores.add("John\t3");
		scores.add("John\t7");
		scores.add("Jeff\t7");
		scores.add("Jeff\t3");
		scores.add("John\t6");
		scores.add("John\t3");
		scores.add("Jeff\t9");
		scores.add("Jeff\t0");
		scores.add("John\t10");
		scores.add("Jeff\t10");
		scores.add("John\t8");
		scores.add("John\t1");
		scores.add("Jeff\t0");
		scores.add("Jeff\t8");
		scores.add("John\t10");
		scores.add("Jeff\t8");
		scores.add("Jeff\t2");
		scores.add("John\t10");
		scores.add("Jeff\tF");
		scores.add("Jeff\t6");
		scores.add("John\t9");
		scores.add("John\t0");
		scores.add("Jeff\t10");
		scores.add("John\t7");
		scores.add("John\t3");
		scores.add("Jeff\t10");
		scores.add("John\t4");
		scores.add("John\t4");
		scores.add("Jeff\t10");
		scores.add("Jeff\t8");
		scores.add("Jeff\t1");
		scores.add("John\t10");
		scores.add("John\t9");
		scores.add("John\t0");
		
		List<Player> convertPlayerScore = playerScoreService.convertPlayerScore(scores);
		assertEquals(convertPlayerScore.get(0).getScores().get(convertPlayerScore.get(0).getScores().size()-1).getScore().intValue(), 151);
		assertEquals(convertPlayerScore.get(1).getScores().get(convertPlayerScore.get(0).getScores().size()-1).getScore().intValue(), 167);

	}
}
