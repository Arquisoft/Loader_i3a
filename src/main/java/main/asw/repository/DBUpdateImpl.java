package main.asw.repository;


import main.asw.report.ReportFactory;
import main.asw.report.ReportWriter;
import main.asw.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class DBUpdateImpl implements DBUpdate {

    private List<User> correctUsers;

    @Override
    public void insert(List<User> users) {
        UserDao ud = PersistenceFactory.getUserDAO();
        this.correctUsers = new ArrayList<>();
        for (User u : users) {
            if(ud.saveUser(u)) {
                correctUsers.add(u);
            }
        }
    }

    @Override
    public void writeReport() {
        generateDirectories();
        ReportWriter textWriter = ReportFactory.createTxtWriter();
        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        textWriter.writeReport(correctUsers);
        docxWriter.writeReport(correctUsers);
        pdfWriter.writeReport(correctUsers);
    }

    private void generateDirectories() {
        File dir = new File("Generated/GeneratedTxt");
        File dir2 = new File("Generated/GeneratedDocx");
        File dir3 = new File("Generated/GeneratedPdf");

        dir.mkdirs();
        dir2.mkdirs();
        dir3.mkdirs();
    }
}
