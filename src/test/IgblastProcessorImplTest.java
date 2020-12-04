package test;

import com.rapidnovor.Exception.ExecuteException;
import com.rapidnovor.Exception.WrongInputMessage;
import com.rapidnovor.IgblastProcessor;
import com.rapidnovor.IgblastProcessorImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IgblastProcessorImplTest {
    private final IgblastProcessorImpl igpl = new IgblastProcessorImpl();

    @Test
    void match(){
    }

    @Test
    void testInputFormat() {
        try {
            igpl.match("BBBB");
        } catch (WrongInputMessage | ExecuteException wrongInputMessage) {
            Assert.assertEquals(wrongInputMessage.getMessage(), "Wrong format of amino acid sequence");
        }
        try {
            igpl.match("QVQL", "mouse", true);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            igpl.match("QVQL", "wrongFormat", true);
        } catch (WrongInputMessage | ExecuteException wrongInputMessage){
            Assert.assertEquals(wrongInputMessage.getMessage(), "Database does not contain that specie");
        }
    }

    @Test
    void testMatch() {
    }
}