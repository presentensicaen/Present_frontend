package fr.ensicaen.present.present;

/**
 * Created by jueast on 14/10/17.
 */

public class InitalFakeClassToDelete {

    private int _testInt;


    public InitalFakeClassToDelete(int _testInt) {
        this._testInt = _testInt;
    }

    public int get_testInt() {
        return _testInt;
    }

    public int add(InitalFakeClassToDelete a){
        return _testInt + a.get_testInt();
    }
}
