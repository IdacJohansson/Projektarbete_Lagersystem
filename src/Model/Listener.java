package Model;

import Controller.MockServer;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener, FocusListener {

    MockServer mockServer;

    public Listener(MockServer mockServer) {
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
        if (e.getSource().equals(mockServer.getAddArticle())) {
            mockServer.getAddArticle().setBackground(Color.GREEN);
        } else if (e.getSource().equals(mockServer.getRemoveArticle())) {
            mockServer.getRemoveArticle().setBackground(Color.RED);
        } else if (e.getSource().equals(mockServer.getShowAllButton())) {
            mockServer.getShowAllButton().setBackground(Color.GREEN);
        } else if (e.getSource().equals(mockServer.getPutOrder())) {
            mockServer.getPutOrder().setBackground(Color.YELLOW);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(mockServer.getAddArticle())) {
            mockServer.getAddArticle().setBackground(new JButton().getBackground());
        } else if (e.getSource().equals(mockServer.getRemoveArticle())) {
            mockServer.getRemoveArticle().setBackground(new JButton().getBackground());
        } else if (e.getSource().equals(mockServer.getShowAllButton())) {
            mockServer.getShowAllButton().setBackground(new JButton().getBackground());
        } else if (e.getSource().equals(mockServer.getPutOrder())) {
            mockServer.getPutOrder().setBackground(new JButton().getBackground());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        mockServer.getSearchInput().setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        mockServer.setSearchWord(mockServer.getSearchInput().getText());
    }
}