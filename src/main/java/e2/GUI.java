package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import e2.model.Cell;
import e2.model.CellsGenerator;
import e2.model.FreeCellsGenerator;
import e2.model.Grid;
import e2.model.GridImpl;
import e2.model.Logics;
import e2.model.LogicsImpl;
import e2.model.RandomCellsGenerator;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int numberOfMines) {
        CellsGenerator<Integer> cellsGenerator = new RandomCellsGenerator();
        CellsGenerator<Cell> freeCellsGenerator = new FreeCellsGenerator();
        Grid grid = new GridImpl(size, numberOfMines, cellsGenerator, freeCellsGenerator);
        this.logics = new LogicsImpl(grid);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = this.logics.isMine(pos); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                bt.setEnabled(false);
                drawBoard();            	
            }
            boolean isThereVictory = false; // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    logics.switchFlag(pos);
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a mine, draw it "*"
            // disable the button
            if (this.logics.isMine(entry.getValue())) {
                entry.getKey().setText("*");
            }
            entry.getKey().setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            if (this.logics.isFlagged(entry.getValue())) {
                entry.getKey().setText("F");
            }
    	}
    }
    
}
