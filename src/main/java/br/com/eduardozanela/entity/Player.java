package br.com.eduardozanela.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
	
	private String name;
	private List<GameRounds> scores;
	
	@Override
	public String toString() {
		return name + " " + scores.toString();
	}
}
