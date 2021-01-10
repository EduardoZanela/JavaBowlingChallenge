package br.com.eduardozanela.service;

import java.util.List;

import br.com.eduardozanela.entity.Player;

public interface PrintService {

	public String getResultsString(List<Player> games);

	public String printSpare(List<String> spare);

	public String printBasic(List<String> frame);

	public String printStrike();

}