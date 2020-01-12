/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
// Q8
public class DPLab3Swing {
    JFrame frame;
    public static void main(String[] args) {
        DPLab3Swing example = new DPLab3Swing();
        example.go();
    }
    
    public void go(){
        frame = new JFrame();
        JButton button = new JButton("Should I do it?");
        
        // without lambdas
//        button.addActionListener(new AngleListener());
//        button.addActionListener(new DevilListener());
        
        // Q9 with lambdas
        button.addActionListener((event) -> {
            System.out.println("Don't do it, you might regret it!");
        });
        button.addActionListener((event) -> System.out.println("Come on, do it!"));
        
        //Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    
    // Can be removed if using lambda
    class AngleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it, you might regret it!");
        }
    }
    
    // Can be removed if using lambda    
    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on, do it!");
        }
        
    }
}

