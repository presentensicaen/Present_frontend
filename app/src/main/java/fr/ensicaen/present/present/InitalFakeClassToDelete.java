package fr.ensicaen.present.present;

/**
 * Created by jueast on 14/10/17.
 */

public class InitalFakeClassToDelete {

    private int _testInt;


    public InitalFakeClassToDelete(int testInt) {
        _testInt = testInt;
    }

    public int getTestInt() {
        return _testInt;
    }

    public int add(InitalFakeClassToDelete a){
        return _testInt + a.getTestInt();
    }
}
