package br.com.eduardozanela.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
	
	private String name;
	private List<Integer> scores;
	
}
