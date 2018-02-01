package main.asw.report;

import main.asw.user.User;

import java.util.List;

/**
 * @author Pineirin
 * @since 14/02/2017.
 */
public interface ReportWriter {

    /**
     * Writes a reports in txt word and pdf of the different users.
     *
     * @param users the users whose information we need to report.
     */
    void writeReport(List<User> users);
}
