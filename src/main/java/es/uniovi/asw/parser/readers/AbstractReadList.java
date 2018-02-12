package es.uniovi.asw.parser.readers;

import java.util.Set;

import es.uniovi.asw.database.AgentDao;
import es.uniovi.asw.database.MongoPersistanceFactory;
import es.uniovi.asw.parser.Agent;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.lettergenerators.ConsoleLetterGenerator;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportDefault;


public abstract class AbstractReadList implements ReadList {

	protected Set<Agent> census;
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
	public Set<Agent> parse(String ruta) {

		doParse(ruta);

		if (census != null && census.size() > 0) {
			PasswordGenerator.createPasswords(census);
			insertAgents(census);
		}
		return census;
	}

	private void insertAgents(Set<Agent> census) {
		AgentDao dao = MongoPersistanceFactory.getAgentDao();
		for (Agent c : census) {
			if (dao.insert(c)) {
				letterGen.generatePersonalLetter(c);
			}
		}
	}

	protected abstract void doParse(String ruta);

}
