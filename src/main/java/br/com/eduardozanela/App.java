package br.com.eduardozanela;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;

import br.com.eduardozanela.constant.ErrorCode;
import br.com.eduardozanela.exception.ProcessingExeption;
import br.com.eduardozanela.service.PlayerScoreService;
import br.com.eduardozanela.service.impl.FileReaderServiceImpl;
import br.com.eduardozanela.service.impl.PlayerScoreServiceImpl;
import br.com.eduardozanela.service.impl.PrintServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App{
	
    public static void main( String[] args ){
    	PlayerScoreService playerServiceScore = new PlayerScoreServiceImpl(new FileReaderServiceImpl(), new PrintServiceImpl());
    	CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        
        options.addOption("f", "file", true, "Score file");
        
        try {
			CommandLine commandLine = parser.parse(options, args);
			if(!commandLine.hasOption("f") || StringUtils.isEmpty(commandLine.getOptionValue("f"))) {
				log.warn("App.main - file path not preset");
				throw new ProcessingExeption("File path not preset", ErrorCode.FILE_PATH_NOT_PRESET);
			}
			
			String generatePlayerScoreBoard = playerServiceScore.generatePlayerScoreBoard(commandLine.getOptionValue("f"));
			System.out.println(generatePlayerScoreBoard);
			
		} catch (ParseException e) {
			log.error("App.main - error to read comand line arguments", e);
		} catch (ProcessingExeption e) {
			log.error("{} code {}", e.getMessage(), e.getMessageCode());
		}     
    }
}
