package Controller;

import Model.AccessLevel;

import javax.swing.*;

public class MockServer {

    private final AccessLevel accessLevel;
    private final MockServer thisMockServer;

    public MockServer(MockServer thisMockServer, AccessLevel accesLevel) {
        this.accessLevel = accesLevel;
        this.thisMockServer = thisMockServer;
    }


    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
}
