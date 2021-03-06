package br.com.eduardozanela.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.eduardozanela.constant.ErrorCode;
import br.com.eduardozanela.exception.ProcessingExeption;
import br.com.eduardozanela.service.FileReaderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReaderServiceImpl implements FileReaderService {
	
	@Override
	public List<String> readFile(String fileName) throws ProcessingExeption {
		String normalized = FilenameUtils.normalize(fileName);
		File file = new File(normalized); 
		try {
			return FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			log.error("FileReader.readFile - error to read file", e);
			throw new ProcessingExeption(e.getMessage(), ErrorCode.FILE_READ_ERROR);
		}
	}
}
