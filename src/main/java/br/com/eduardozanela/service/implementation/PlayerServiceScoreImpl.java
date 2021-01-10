package br.com.eduardozanela.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import br.com.eduardozanela.constant.ErrorCode;
import br.com.eduardozanela.entity.GameRounds;
import br.com.eduardozanela.entity.Player;
import br.com.eduardozanela.exception.ProcessingExeption;
import br.com.eduardozanela.service.PlayerScoreService;
import br.com.eduardozanela.utils.FileReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerServiceScoreImpl implements PlayerScoreService {

	private static final String STRIKE_POINTS = "10";
	private static final String STRIKE = "X";
	private static final String SPARE = "/";

	@Override
	public void generatePlayerScoreBoard(String fileName) {
		List<Player> playerScores = convertPlayerScore(FileReader.readFile(fileName));
		for(Player player : playerScores) {
			System.out.println(player.getName());
			player.getScores().forEach(round -> {
				System.out.print("\n");
				round.getPinFalls().forEach(pinFall -> {
					System.out.print(pinFall + " ");
				});
			});
			System.out.print("\n");
		}
	}

	@Override
	public List<Player> convertPlayerScore(List<String> scores) {
		Map<String, List<String>> scoreBoard = scores.stream()
				.map(s -> s.split("\\t"))
				.collect(Collectors.groupingBy(a -> a[0], Collectors.mapping(a -> validateScore(a[1]), Collectors.toList())));
		return scoreBoard.entrySet().stream()
				.map(m-> createPlayer(m.getKey(), m.getValue()))
				.collect(Collectors.toList());
	}

	private Player createPlayer(String name, List<String> plays) {
		return new Player(name, createGameRounds(plays));
	}
	
	private List<GameRounds> createGameRounds(List<String> plays) {
		
		if(plays.size() < 11) {
			throw new ProcessingExeption("Invalid number of rounds", ErrorCode.INVALID_ROUNDS);
		}
		List<GameRounds> rounds = new ArrayList<>();
		try {
			
			for(int i = 0; i < plays.size(); i++) {
				List<String> pinFalls = new ArrayList<>();
				if(STRIKE_POINTS.equalsIgnoreCase(plays.get(i))) {
					pinFalls.add(STRIKE);
					
				} else {
					pinFalls.add(plays.get(i));
					if(isSpare(plays.get(i), plays.get(++i))) {
						pinFalls.add(SPARE);
					} else {				
						pinFalls.add(plays.get(i));
					}
				}
				if(rounds.size() == 10) {
					rounds.get(rounds.size()-1).getPinFalls().addAll(pinFalls);
				} else {
					rounds.add(new GameRounds(pinFalls));
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new ProcessingExeption("Invalid number of plays", ErrorCode.INVALID_ROUNDS);
		}
				
		if(rounds.size() != 10 || rounds.get(rounds.size()-1).getPinFalls().size() != 3) {
			throw new ProcessingExeption("Invalid number of rounds", ErrorCode.INVALID_ROUNDS);
		}
		
		return rounds;
	}
	

	private boolean isSpare(String firstFall, String secondFall) {
		return isNumericPinFalls(firstFall, secondFall) && Integer.parseInt(firstFall) + Integer.parseInt(secondFall) == 10;
	}

	private boolean isNumericPinFalls(String firstFall, String secondFall) {
		return StringUtils.isNumeric(firstFall) && StringUtils.isNumeric(secondFall);
	}

	@Override
	public String validateScore(String score) {
		if(score.equalsIgnoreCase("F"))
			return score;
		try {
			if(Integer.valueOf(score) < 0 && Integer.valueOf(score) > 10)
				throw new ProcessingExeption("Invalid Score", ErrorCode.INVALID_SCORE);
		} catch (NumberFormatException e) {
			log.error("PlayerServiceScoreImpl.validateScore - invalid score", e);
			throw new ProcessingExeption("Invalid Score", ErrorCode.INVALID_SCORE);
		}		
		return score;		
	}

}
