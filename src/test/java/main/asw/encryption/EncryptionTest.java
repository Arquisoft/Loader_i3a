package main.asw.encryption;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by MIGUEL on 8/02/2017.
 */
public class EncryptionTest {

    private String unencryptedPass;
    private String password;

    @Before
    public void setUp() {
        unencryptedPass = EncryptionUtils.getInstance().generatePassword();
        password = EncryptionUtils.getInstance().encryptPassword(unencryptedPass);
    }

    @Test
    public void testSuccessfulLogin() {
        assertTrue(EncryptionUtils.getInstance().checkPassword(unencryptedPass, password));
    }

    @Test
    public void testUnsuccessfulLogin() {
        assertFalse(EncryptionUtils.getInstance().checkPassword(unencryptedPass.concat("etc123"), password));
    }

}
