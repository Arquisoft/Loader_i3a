package main.asw.report;

import main.asw.user.User;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
class DocxWriter implements ReportWriter {

    private final static Logger log = LoggerFactory.getLogger(DocxWriter.class);

    @Override
    public void writeReport(List<User> users) {
        FileOutputStream outputStream = null;
        for (User user : users) {
            try {
                outputStream = new FileOutputStream("Generated/GeneratedDocx/" + user.getEmail() + ".docx");
                XWPFDocument document = new XWPFDocument();
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                addTitle(user, paragraph);
                XWPFRun run2 = addText(user, paragraph);
                document.write(outputStream);
                log.info("Exported user with userId = " + user.getNif() + " correctly to DOCX format");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Auxiliar method that creates the head of the document.
     *
     * @param user      the user whose report we're creating.
     * @param paragraph the text of the head of the document.
     */
    private void addTitle(User user, XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        //    run.setBold(true);
        run.setFontSize(14);
        addLine(run, "Greetings: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    /**
     * Auxiliar method that creates the body of the document.
     *
     * @param user      the user whose report we're creating.
     * @param paragraph the text of the body of the document.
     */
    private XWPFRun addText(User user, XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setBold(false);
        run.setFontSize(12);
        addLine(run, "This is your personal information that we have received: ");
        addLine(run, "Date of birth: " + user.getDateOfBirth() + ".");
        addLine(run, "NIF: " + user.getNif() + ".");
        addLine(run, "Nationality: " + user.getNationality() + ".");
        addLine(run, "Address: " + user.getAddress() + ".");
        run.addBreak();
        addLine(run, "Your user name is your email: " + user.getEmail());
        addLine(run, "Your password is: " + user.getUnencryptedPass());
        return run;
    }

    /**
     * Auxiliar method that Adds a line of text and a line jump.
     *
     * @param run  the XWPF run.
     * @param line the text we want to in the line.
     */
    private void addLine(XWPFRun run, String line) {
        run.setText(line);
        run.addBreak();
    }
}
