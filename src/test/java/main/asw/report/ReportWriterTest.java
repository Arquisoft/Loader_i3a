package main.asw.report;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import main.asw.user.User;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
public class ReportWriterTest {

    private final static Logger log = LoggerFactory.getLogger(ReportWriterTest.class);

    @Test
    public void testTxtWriter() {

        File dir = new File("Generated/GeneratedTxt");
        dir.mkdirs();

        List<User> users = new ArrayList<>();

        users.add(new User("Pablo", "Pineirin", "pineirin@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Pablo", "García Marcos", "PabloGarciaMarcos@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Angel", "Borré Santiago", "AngelBorreSantiago@gmail.com", new Date(), "Navia", "Spain", "65489683N"));

        ReportWriter textWriter = ReportFactory.createTxtWriter();
        textWriter.writeReport(users);

        File file = new File("Generated/GeneratedTxt/pineirin@gmail.com.txt");
        File file2 = new File("Generated/GeneratedTxt/PabloGarciaMarcos@gmail.com.txt");
        File file3 = new File("Generated/GeneratedTxt/AngelBorreSantiago@gmail.com.txt");
        File file4 = new File("pablo@gmail.com.txt");

        assertEquals(true, file.exists());
        assertEquals(true, file2.exists());
        assertEquals(true, file3.exists());
        assertEquals(false, file4.exists());

        String[] lines = readerTxt(file);
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerTxt(file2);
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerTxt(file3);
        assertTrue(lines[0].contains("Greetings: Angel Borré Santiago."));
        assertTrue(lines[5].contains("Address: Navia"));
        assertTrue(lines[8].contains("Your password is: "));

        assertEquals(true, file.delete());
        assertEquals(true, file2.delete());
        assertEquals(true, file3.delete());
        assertEquals(false, file4.delete());

        dir.delete();
    }


    @Test
    public void testDocxWriter() {

        File dir = new File("Generated/GeneratedDocx");
        dir.mkdirs();

        List<User> users = new ArrayList<>();

        users.add(new User("Pablo", "Pineirin", "pineirin@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Pablo", "García Marcos", "PabloGarciaMarcos@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Angel", "Borré Santiago", "AngelBorreSantiago@gmail.com", new Date(), "Navia", "Spain", "65489683N"));

        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        docxWriter.writeReport(users);

        File file = new File("Generated/GeneratedDocx/pineirin@gmail.com.docx");
        File file2 = new File("Generated/GeneratedDocx/PabloGarciaMarcos@gmail.com.docx");
        File file3 = new File("Generated/GeneratedDocx/AngelBorreSantiago@gmail.com.docx");
        File file4 = new File("pablo@gmail.com.docx");

        assertEquals(true, file.exists());
        assertEquals(true, file2.exists());
        assertEquals(true, file3.exists());
        assertEquals(false, file4.exists());


        String[] lines = readerDocx(file);
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerDocx(file2);
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerDocx(file3);
        assertTrue(lines[0].contains("Greetings: Angel Borré Santiago."));
        assertTrue(lines[5].contains("Address: Navia"));
        assertTrue(lines[8].contains("Your password is: "));


        assertEquals(true, file.delete());
        assertEquals(true, file2.delete());
        assertEquals(true, file3.delete());
        assertEquals(false, file4.delete());

        dir.delete();
    }


    @Test
    public void testPdfWriter() {

        File dir = new File("Generated/GeneratedPdf");
        dir.mkdirs();

        List<User> users = new ArrayList<>();

        users.add(new User("Pablo", "Pineirin", "pineirin@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Pablo", "García Marcos", "PabloGarciaMarcos@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Angel", "Borré Santiago", "AngelBorreSantiago@gmail.com", new Date(), "Navia", "Spain", "65489683N"));

        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        pdfWriter.writeReport(users);

        File file = new File("Generated/GeneratedPdf/pineirin@gmail.com.pdf");
        File file2 = new File("Generated/GeneratedPdf/PabloGarciaMarcos@gmail.com.pdf");
        File file3 = new File("Generated/GeneratedPdf/AngelBorreSantiago@gmail.com.pdf");
        File file4 = new File("pablo@gmail.com.pdf");

        assertEquals(true, file.exists());
        assertEquals(true, file2.exists());
        assertEquals(true, file3.exists());
        assertEquals(false, file4.exists());

        String filename1 = "Generated/GeneratedPdf/pineirin@gmail.com.pdf";
        String filename2 = "Generated/GeneratedPdf/PabloGarciaMarcos@gmail.com.pdf";
        String filename3 = "Generated/GeneratedPdf/AngelBorreSantiago@gmail.com.pdf";


        String[] lines = readerPdf(filename1);
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerPdf(filename2);
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerPdf(filename3);
        assertTrue(lines[0].contains("Greetings: Angel Borré Santiago."));
        assertTrue(lines[5].contains("Address: Navia"));
        assertTrue(lines[8].contains("Your password is: "));

        assertEquals(true, file.delete());
        assertEquals(true, file2.delete());
        assertEquals(true, file3.delete());
        assertEquals(false, file4.delete());

        dir.delete();
    }

    @Test
    public void testReportWriter() {

        File dir = new File("Generated/GeneratedTxt");
        dir.mkdirs();
        File dir2 = new File("Generated/GeneratedDocx");
        dir2.mkdirs();
        File dir3 = new File("Generated/GeneratedPdf");
        dir3.mkdirs();

        List<User> users = new ArrayList<>();

        users.add(new User("Pablo", "Pineirin", "pineirin@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));
        users.add(new User("Pablo", "García Marcos", "PabloGarciaMarcos@gmail.com", new Date(), "Gijón", "Spain", "53520961F"));

        ReportWriter textWriter = ReportFactory.createTxtWriter();
        ReportWriter docxWriter = ReportFactory.createDocxWriter();
        ReportWriter pdfWriter = ReportFactory.createPdfWriter();
        textWriter.writeReport(users);
        docxWriter.writeReport(users);
        pdfWriter.writeReport(users);

        File file = new File("Generated/GeneratedTxt/pineirin@gmail.com.txt");
        File file2 = new File("Generated/GeneratedTxt/PabloGarciaMarcos@gmail.com.txt");
        File file3 = new File("Generated/GeneratedDocx/pineirin@gmail.com.docx");
        File file4 = new File("Generated/GeneratedDocx/PabloGarciaMarcos@gmail.com.docx");
        File file5 = new File("Generated/GeneratedPdf/pineirin@gmail.com.pdf");
        File file6 = new File("Generated/GeneratedPdf/PabloGarciaMarcos@gmail.com.pdf");
        File file7 = new File("pineirin@gmail.com.txt");
        File file8 = new File("pablo@gmail.com.pdf");

        assertEquals(true, file.exists());
        assertEquals(true, file2.exists());
        assertEquals(true, file3.exists());
        assertEquals(true, file4.exists());
        assertEquals(true, file5.exists());
        assertEquals(true, file6.exists());
        assertEquals(false, file7.exists());
        assertEquals(false, file8.exists());

        String contraseña1;
        String contraseña2;
        String contraseña3;

        String[] lines = readerTxt(file);
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));
        contraseña1 = lines[8];

        lines = readerTxt(file2);
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerDocx(file3);
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));
        contraseña2 = lines[8];

        lines = readerDocx(file4);
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        lines = readerPdf("Generated/GeneratedPdf/pineirin@gmail.com.pdf");
        assertTrue(lines[0].contains("Greetings: Pablo Pineirin."));
        assertTrue(lines[1].contains("This is your personal information that we have received: "));
        assertTrue(lines[8].contains("Your password is: "));
        contraseña3 = lines[8];

        lines = readerPdf("Generated/GeneratedPdf/PabloGarciaMarcos@gmail.com.pdf");
        assertTrue(lines[0].contains("Greetings: Pablo García Marcos."));
        assertTrue(lines[3].contains("NIF: 53520961F"));
        assertTrue(lines[8].contains("Your password is: "));

        assertTrue(contraseña1.contains(contraseña2));
        assertTrue(contraseña1.contains(contraseña3));

        assertEquals(true, file.delete());
        assertEquals(true, file2.delete());
        assertEquals(true, file3.delete());
        assertEquals(true, file4.delete());
        assertEquals(true, file5.delete());
        assertEquals(true, file6.delete());
        assertEquals(false, file7.delete());
        assertEquals(false, file8.delete());

        new File("Generated/GeneratedTxt").delete();
        new File("Generated/GeneratedDocx").delete();
        new File("Generated/GeneratedPdf").delete();
        new File("Generated").delete();
    }


    private String[] readerTxt(File file) {
        String[] lines = new String[9];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            int i = 0;
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
                lines[i] = sb.toString();
                i++;
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if( bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return lines;
    }

    private String[] readerDocx(File file) {
        String[] lines = new String[9];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fileInputStream);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
               lines = paragraph.getText().split("\n");
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return lines;
    }

    private String[] readerPdf(String filename1) {
        PdfReader reader = null;
        String[] lines = new String[9];

        try {

            reader = new PdfReader(filename1);

            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            lines = textFromPage.split("\n");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            if( reader != null)
                reader.close();
        }

        return lines;
    }

}