package br.com.eduardozanela.service;

import java.util.List;

import br.com.eduardozanela.entity.Player;

public interface PlayerScoreService {
	
	public void generatePlayerScoreBoard(String fileName);	
	public List<Player> convertPlayerScore(List<String> score);
	public String validateScore(String score);
}
