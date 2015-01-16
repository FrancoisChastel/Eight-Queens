/*
 * Copyright © 2015, François Chastel and Timothy Keynes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders X be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software.
 *
 * Except as contained in this notice, the name of the <copyright holders> shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization from the François Chastel and Timothy Keynes.
 */

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

/**
 * Class using JTree to solve the NQuens (eight queens) problem
 */
public class NQueens {
    public static void main(String args[]) {
        System.out.println("How many queens? ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] board = new int[n]; //hold the column position of n queens
        DefaultMutableTreeNode racine= new DefaultMutableTreeNode();
        placeQueenOnBoard(0, board, racine);

        JTree arbre=new JTree(racine);

        JFrame f = new JFrame("(SWING) Classe DemoJTree - V 1.0.0");
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        f.getContentPane().add(arbre);
        f.getContentPane().add(new JScrollPane(arbre));

        f.setVisible(true);
    }

    private static void placeQueenOnBoard(int Qi, int[] board, DefaultMutableTreeNode racine) {
        int n = board.length;
        //base case
        if (Qi == n) {// a valid configuration found.
            System.out.println(Arrays.toString(board));
        } else {
            //try to put the ith Queen (Qi) in all of the columns
            for (int column = 0; column < n; column++) {
                if (isSafePlace(column, Qi, board)) {
                    board[Qi] = column;
                    //then place remaining queens.
                    DefaultMutableTreeNode enfant = new DefaultMutableTreeNode(Arrays.toString(board));
                    racine.add(enfant);
                    placeQueenOnBoard(Qi + 1, board, enfant);
                    /**
                     * backtracking. It is not required in this as we only look previously
                     * placed queens in isSafePlace method and it doesnot care what values
                     * are available in next positions.*
                     */

                    board[Qi] = -1;
                }
            }
        }
    }

    //check if the column is safe place to put Qi (ith Queen)
    private static boolean isSafePlace(int column, int Qi, int[] board) {
        //check for all previously placed queens
        for (int i = 0; i < Qi; i++) {
            if (board[i] == column) { // the ith Queen(previous) is in same column
                return false;
            }
            //the ith Queen is in diagonal
            //(r1, c1) - (r2, c1). if |r1-r2| == |c1-c2| then they are in diagonal
            if (Math.abs(board[i] - column) == Math.abs(i - Qi)) {
                return false;
            }
        }
        return true;
    }
}
    
