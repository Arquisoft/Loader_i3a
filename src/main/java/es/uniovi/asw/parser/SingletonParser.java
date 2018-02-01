package es.uniovi.asw.parser;

import es.uniovi.asw.parser.lettergenerators.PDFLetterGenerator;
import es.uniovi.asw.parser.lettergenerators.WordLetterGenerator;
import es.uniovi.asw.parser.readers.ExcelReadList;
import es.uniovi.asw.parser.readers.TxtReadList;

/**
 * @author uo245303
 * Parser Manager
 */
public class SingletonParser {
	private static SingletonParser instance;
	private ExcelReadList excelRL;
	private ExcelReadList excelRLWordLetter;
	private ExcelReadList excelRLPDFLetter;
	private TxtReadList txtRL;
	private TxtReadList txtRLWordLetter;
	private TxtReadList txtRLPDFLetter;

	private SingletonParser() {
		excelRL = new ExcelReadList();
		excelRLWordLetter = new ExcelReadList(new WordLetterGenerator());
		excelRLPDFLetter = new ExcelReadList(new PDFLetterGenerator());
		txtRL = new TxtReadList();
		txtRLWordLetter = new TxtReadList(new WordLetterGenerator());
		txtRLPDFLetter = new TxtReadList(new PDFLetterGenerator());
	}

	public static SingletonParser getInstance() {
		if (instance == null) {
			instance = new SingletonParser();
		}
		return instance;
	}
	
	public ReadList getDefaultTxtReadList() {
		return txtRL;
	}
	
	public ReadList getWordLetterTxtReadList() {
		return txtRLWordLetter;
	}
	
	public ReadList getPDFLetterTxtReadList() {
		return txtRLPDFLetter;
	}

	public ReadList getDefaultExcelReadList() {
		return excelRL;
	}
	
	public ReadList getWorldLetterExcelReadList() {
		return excelRLWordLetter;
	}
	
	public ReadList getPDFLetterExcelReadList() {
		return excelRLPDFLetter;
	}
}
