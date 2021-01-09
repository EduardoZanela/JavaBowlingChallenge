package br.com.eduardozanela.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.eduardozanela.constant.ErrorCode;
import br.com.eduardozanela.entity.Player;
import br.com.eduardozanela.exception.ProcessingExeption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReader {
	
	public static List<Player> readFile(String fileName) throws ProcessingExeption {
		String normalized = FilenameUtils.normalize(fileName);
		File file = new File(normalized); 
		try {
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			lines.forEach(System.out::println);
		} catch (IOException e) {
			log.error("FileReader.readFile - error to read file", e);
			throw new ProcessingExeption(e.getMessage(), ErrorCode.FILE_READ_ERROR);
		}
				
		return null;
	}
}