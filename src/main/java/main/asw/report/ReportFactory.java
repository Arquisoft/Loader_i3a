package main.asw.report;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
public class ReportFactory {

    /**
     * Method used to create ReportWriter.
     *
     * @return an instance TxtWriter.
     */
    public static ReportWriter createTxtWriter() {
        return new TxtWriter();
    }

    /**
     * Method used to create a DocxWriter.
     *
     * @return an instance of DocxWriter.
     */
    public static ReportWriter createDocxWriter() {
        return new DocxWriter();
    }

    /**
     * Method used to create a MyPdfWriter.
     *
     * @return an instance of MyPdfWriter.
     */
    public static ReportWriter createPdfWriter() {
        return new MyPdfWriter();
    }
}
