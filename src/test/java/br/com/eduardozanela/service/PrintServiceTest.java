package br.com.eduardozanela.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.eduardozanela.service.impl.PrintServiceImpl;

public class PrintServiceTest {

	private PrintService printService;

	@Before
	public void setUp() {
		printService = new PrintServiceImpl();

	}

	@Test
	public void printStrike() {
		assertEquals(printService.printStrike(), "\tX\t");
	}

	@Test
	public void printSpare() {
		List<String> spare = new ArrayList<>();
		spare.add("7");
		spare.add("3");
		assertEquals(printService.printSpare(spare), "7\t/\t");
	}

	@Test
	public void printBasic() {
		List<String> basic = new ArrayList<>();
		basic.add("7");
		basic.add("1");
		assertEquals(printService.printBasic(basic), "7\t1\t");
	}

}

