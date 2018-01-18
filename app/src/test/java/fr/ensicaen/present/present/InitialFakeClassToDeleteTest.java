package fr.ensicaen.present.present;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import fr.ensicaen.present.present.tests2delete.HobbiesExample;
import fr.ensicaen.present.present.tests2delete.InitalFakeClassToDelete;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

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

    @Test
    public void testGetHobbies() throws JSONException, IOException {
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody("[{\"name\": \"blah\"}, {\"name\": \"blah\"}]");
        server.enqueue(response);
        server.start();

        InitalFakeClassToDelete exampleHobies = new InitalFakeClassToDelete(2);
        exampleHobies.setBaseUrl(server.url("/").toString());
        ArrayList<HobbiesExample> hobbies = exampleHobies.getHobbies("julian");

        assertEquals(new HobbiesExample("blah"), hobbies.get(0));
        server.close();
    }
}