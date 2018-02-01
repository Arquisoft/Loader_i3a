package main.asw.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import main.asw.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * @author Pineirin
 * @since 14/02/2017.
 */
class MyPdfWriter implements ReportWriter {

    private final static Logger log = LoggerFactory.getLogger(DocxWriter.class);

    @Override
    public void writeReport(List<User> users) {
        Document document = null;
        FileOutputStream fileOutputStream = null;
        for (User user : users) {
            try {
                fileOutputStream = new FileOutputStream("Generated/GeneratedPdf/" + user.getEmail() + ".pdf");
                document = new Document();
                PdfWriter.getInstance(document, fileOutputStream);
                document.open();
                addText(user, document);
                log.info("Exported user with userId = " + user.getNif() + " correctly to PDF format");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                if (document != null) {
                    document.close();
                }
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Auxiliar method that adds all the text to the pdf.
     *
     * @param user     the user whose information we want to print.
     * @param document the document that we want to generate.
     * @throws DocumentException throws a exception you aren't able to write in the document.
     */
    private void addText(User user, Document document) throws DocumentException {
        document.add(new Paragraph("Greetings: " + user.getFirstName() + " " + user.getLastName() + "."));
        document.add(new Paragraph("This is your personal information that we have received: "));
        document.add(new Paragraph("Date of birth: " + user.getDateOfBirth() + "."));
        document.add(new Paragraph("NIF: " + user.getNif() + "."));
        document.add(new Paragraph("Nationality: " + user.getNationality() + "."));
        document.add(new Paragraph("Address: " + user.getAddress() + "."));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Your user name is your email: " + user.getEmail()));
        document.add(new Paragraph("Your password is: " + user.getUnencryptedPass()));
    }
}
