package es.uniovi.asw.parser.readers;

import java.util.Set;

import es.uniovi.asw.database.CitizenDao;
import es.uniovi.asw.database.MongoPersistanceFactory;
import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.lettergenerators.ConsoleLetterGenerator;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportDefault;

/**
 * @author Oriol Template, concrete parsers (Excel/Word/txt/...) will override
 *         "doParse".
 */
public abstract class AbstractReadList implements ReadList {

	protected Set<Citizen> census;
	private LetterGenerator letterGen;
	protected WriteReport wReport;

	public AbstractReadList() {
		this.letterGen = new ConsoleLetterGenerator();
		this.wReport = new WriteReportDefault();
	}

	public AbstractReadList(LetterGenerator letterGenerator) {
		this.letterGen = letterGenerator;
		this.wReport = new WriteReportDefault();
	}

	@Override
	public Set<Citizen> parse(String ruta) {

		doParse(ruta);

		if (census != null && census.size() > 0) {
			PasswordGenerator.createPasswords(census);
			insertCitizens(census);
		}
		return census;
	}

	private void insertCitizens(Set<Citizen> census) {
		CitizenDao dao = MongoPersistanceFactory.getCitizenDao();
		for (Citizen c : census) {
			if (dao.insert(c)) {
				letterGen.generatePersonalLetter(c);
			}
		}
	}

	protected abstract void doParse(String ruta);

}
