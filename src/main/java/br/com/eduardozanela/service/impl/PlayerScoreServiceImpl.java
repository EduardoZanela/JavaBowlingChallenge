package br.com.eduardozanela.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import br.com.eduardozanela.constant.ErrorCode;
import br.com.eduardozanela.entity.GameRounds;
import br.com.eduardozanela.entity.Player;
import br.com.eduardozanela.exception.ProcessingExeption;
import br.com.eduardozanela.service.FileReaderService;
import br.com.eduardozanela.service.PlayerScoreService;
import br.com.eduardozanela.service.PrintService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerScoreServiceImpl implements PlayerScoreService {

	private static final String STRIKE_POINTS = "10";
	private static final String STRIKE = "X";
	private static final String SPARE = "/";
	
	private FileReaderService fileReader;
	private PrintService printService;
	
	public PlayerScoreServiceImpl(FileReaderService fileReader, PrintService printService) {
		this.fileReader = fileReader;
		this.printService = printService;
	}

	@Override
	public String generatePlayerScoreBoard(String fileName) {
		List<Player> players = convertPlayerScore(fileReader.readFile(fileName));
		return printService.getResultsString(players);
	}
	
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
		
		if(plays.size() < 12) {
			throw new ProcessingExeption("Invalid number of rounds", ErrorCode.INVALID_ROUNDS);
		}
		List<GameRounds> rounds = new ArrayList<>();
		Integer score = 0;
		try {
			
			for(int i = 0; i < plays.size(); i++) {
				List<String> pinFalls = new ArrayList<>();
				if(STRIKE_POINTS.equalsIgnoreCase(plays.get(i))) {
					pinFalls.add(STRIKE);
					if(plays.size()-2 <= i) {
						score += Integer.valueOf(plays.get(i));
					} else {
						score += Integer.valueOf(plays.get(i)) + Integer.valueOf(plays.get(i+1)) + Integer.valueOf(plays.get(i+2));
					}
				} else {
					pinFalls.add(plays.get(i));
					if(plays.size()-1 == i) {
						score += getFormatedValue(plays.get(i)) ;
					} else if(isSpare(plays.get(i), plays.get(++i))) {
						pinFalls.add(SPARE);
						score += 10 + getFormatedValue(plays.get(i+1));
					} else {				
						pinFalls.add(plays.get(i));
						score += getFormatedValue(plays.get(i-1)) + getFormatedValue(plays.get(i));
					}
				}
				if(rounds.size() == 10) {
					rounds.get(rounds.size()-1).getPinFalls().addAll(pinFalls);
				} else {
					rounds.add(new GameRounds(pinFalls, score));
				}
			}
		} catch (IndexOutOfBoundsException e) {
			log.error("", e);
			throw new ProcessingExeption("Invalid number of plays", ErrorCode.INVALID_ROUNDS);
		}
				
		if(rounds.size() != 10 || rounds.get(rounds.size()-1).getPinFalls().size() != 3) {
			throw new ProcessingExeption("Invalid number of rounds", ErrorCode.INVALID_ROUNDS);
		}
		
		return rounds;
	}
	

	private Integer getFormatedValue(String value) {
		if(value.equalsIgnoreCase("F")) return 0;
		return Integer.valueOf(value);
	}

	private boolean isSpare(String firstFall, String secondFall) {
		return isNumericPinFalls(firstFall, secondFall) && Integer.parseInt(firstFall) + Integer.parseInt(secondFall) == 10;
	}

	private boolean isNumericPinFalls(String firstFall, String secondFall) {
		return StringUtils.isNumeric(firstFall) && StringUtils.isNumeric(secondFall);
	}

	private String validateScore(String score) {
		if(score.equalsIgnoreCase("F"))
			return score;
		try {
			if(Integer.valueOf(score) < 0 && Integer.valueOf(score) > 10)
				throw new ProcessingExeption("Invalid Score", ErrorCode.INVALID_SCORE);
		} catch (NumberFormatException e) {
			log.error("PlayerServiceScoreImpl.validateScore - invalid score");
			throw new ProcessingExeption("Invalid Score", ErrorCode.INVALID_SCORE);
		}		
		return score;		
	}

}
