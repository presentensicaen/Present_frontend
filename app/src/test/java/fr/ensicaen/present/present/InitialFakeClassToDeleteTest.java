package fr.ensicaen.present.present;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InitialFakeClassToDeleteTest {
    @Test
    public void addition_isCorrect() throws Exception {
        InitalFakeClassToDelete a = new InitalFakeClassToDelete(2);
        InitalFakeClassToDelete b = new InitalFakeClassToDelete(2);


        assertEquals(4, a.add(b));
    }

}