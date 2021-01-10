package br.com.eduardozanela.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameRounds {
	
	private List<String> pinFalls;
	private Integer score;
	
	public GameRounds(List<String> pinFalls) {
		this.pinFalls = pinFalls;
	}
}
