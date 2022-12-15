package Model;

import Controller.MockServer;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {

    MockServer mockServer;

    public MouseListen(MockServer mockServer) {
        this.mockServer = mockServer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(mockServer.getAddArticle())){
            mockServer.getAddArticle().setBackground(Color.GREEN);
        } else if (e.getSource().equals(mockServer.getRemoveArticle())) {
            mockServer.getRemoveArticle().setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(mockServer.getAddArticle())){
            mockServer.getAddArticle().setBackground(new JButton().getBackground());
        } else if (e.getSource().equals(mockServer.getRemoveArticle())) {
            mockServer.getRemoveArticle().setBackground(new JButton().getBackground());
        }
    }
}
