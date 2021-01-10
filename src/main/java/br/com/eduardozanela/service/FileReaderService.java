package br.com.eduardozanela.service;

import java.util.List;

import br.com.eduardozanela.exception.ProcessingExeption;

public interface FileReaderService {

	public List<String> readFile(String fileName) throws ProcessingExeption;
	
}
