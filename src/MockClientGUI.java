import javax.swing.*;

public class MockClientGUI {
    private JPanel Panel;
    private JLabel WelcomeLabel;
    private JPanel Panel2;
    private JButton butik;
    private JButton lager;
    private JLabel Text;
    private JButton inköp;

    private MockServer mockServer;

    public MockClientGUI() {
        butik.addActionListener(e -> {
            if (butik == e.getSource()){
                mockServer = new MockServer(AccessLevel.BUTIK);
                setMockServer();
            }

        });
        lager.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.LAGER);
            setMockServer();
        });
        inköp.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.INKÖP);
            setMockServer();
        });
    }

    private void setMockServer(){

    }
}
