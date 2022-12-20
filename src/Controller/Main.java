package Controller;

import Model.StartClient;

import javax.swing.*;

public class Main implements StartClient {

    public static void main(String[] args) {
        Main main = new Main();
        main.startClient();
    }

    @Override
    public void startClient() {
        JFrame mockGUI = new MockClient();
    }
}
