package br.com.eduardozanela.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.eduardozanela.entity.Player;
import br.com.eduardozanela.service.PrintService;

public class PrintServiceImpl implements PrintService {

	private StringBuilder string = new StringBuilder();

	private String stringScore = "";

	private static final String PRINT_HEADER = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

	private static final String PRINT_SCORE_LABEL = "\nScore\t\t";

	private static final String PRINT_PINFALLS_LABEL = "\nPinfalls\t";

	private static final String TAB = "\t";

	private static final String CARRIAGE_RETURN = "\n";

	@Override
	public String getResultsString(List<Player> games) {
		string.append(PRINT_HEADER);

		games.forEach(game -> {

			string.append(game.getName());
			string.append(PRINT_PINFALLS_LABEL);

			game.getScores().forEach(frame -> {
				string.append(printBasic(frame.getPinFalls()));
				stringScore += frame.getScore() + TAB + TAB;
			});

			string.append(PRINT_SCORE_LABEL);
			string.append(stringScore);
			stringScore = "";
			string.append(CARRIAGE_RETURN);
		});

		return string.toString();

	}

	@Override
	public String printBasic(List<String> frame) {
		return frame.stream().map(roll -> roll + TAB).collect(Collectors.joining());
	}
}