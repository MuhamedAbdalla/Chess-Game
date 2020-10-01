/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import JChess.Pieces.Bishop;
import JChess.Pieces.King;
import JChess.Pieces.Knight;
import JChess.Pieces.Queen;
import JChess.Pieces.pawn;
import JChess.Pieces.Rook;
import java.io.*;
import java.util.*;
import java.nio.*;
import java.lang.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import test.Table.Pieces;

/**
 *
 * @author 20114
 */
public class Table extends javax.swing.JFrame {

    /**
     * Creates new form table1
     */
    public enum Pieces {

        Rook(1),
        Knight(2),
        Bishop(3),
        King(4),
        Queen(5),
        Pawn(6);
        private final int PieceNumber;

        Pieces(int PieceNumber) {
            this.PieceNumber = PieceNumber;
        }
    }
    public static class Coordinates
    {
        public int Row,Col;
        public Coordinates(){
            this.Row = -1;
            this.Col = -1;
        }
        public Coordinates(int x,int y){
            this.Row = x;
            this.Col = y;
        }
        public void Reset_Coordinates(){
            this.Row = -1;
            this.Col = -1;
        }
        
    }

    public static JButton[][] chess;
    public static int[][] board = new int[10][10]; // have the pieces number 
    public static boolean[][] ToGo_Pos = new boolean[10][10];
    public static boolean isat = false, ser = false, go = false, isattacked, check3 = false, ispawn = false; // Give me the one piece i will move
    public static int player, OpponentPlayer; // player the one is playing now 
    public String Piece_ICon = "";//Saves the Icon in function get icon
    public int Promo_Piece_Row, Promo_Piece_Col;//indx of pawn to promot
    public static int Pawn_Prv_Row_R = -1, Pawn_Prv_Col_R = -1, Pawn_Prv_Row_L = -1, Pawn_Prv_Col_L = -1;
    public final Icon GreenIcon = new ImageIcon("E:\\pic\\green.PNG");
    public static final Icon White_Icon = new ImageIcon("E:\\pic\\white.PNG");
    public static final Icon Black_Icon = new ImageIcon("E:\\pic\\black.PNG");
    public static String ncon;
    public static int[][] grid = new int[10][10];// has values 0 or 1 or 2 indecates of an empty selected cell or a white or black piece
    public static Icon right, left;
    public Icon Resault_ICon;
    public static Icon[][] ICons_Arr = new Icon[10][10];
    public static int  Bishop_Coordinates_Row = -1, Bishop_Coordinates_Col = -1, Queen_Coordinates_Row = -1, Queen_Coordinates_Col = -1, Knight_Coordinates_Row = -1, Knight_Coordinates_Col = -1, King_Coordinates_Row = -1, King_Coordinates_Col = -1;
    public static int KingGrid[][] = new int[10][10];
    public static int rowWKing, colWKing, rep;
    public static int W_KingRow, W_KingCol, B_KingRow, B_KingCol; /** Holding The Position of the White and Black King Respectivly*/
    public static int Operation_Conter, idx1, idx2;
    public int row1 = -1, col1 = -1, row2 = -1, col2 = -1, renum = 0, num = 0; //For Undo
    public static Stack<Pair<Integer, Pair<Integer, Icon>>> undo1 = new Stack<Pair<Integer, Pair<Integer, Icon>>>();
    public static Stack<Pair<Integer, Integer>> undo2 = new Stack<Pair<Integer, Integer>>();
    public static Stack<Pair<Integer, Integer>> undo3 = new Stack<Pair<Integer, Integer>>();
    public static Coordinates Rook_Coordinates ;
    
    public Table(int Starting_Player) {
        initComponents();
        player = Starting_Player;
        jPanel1.hide();
        int[][] board1 = { // Pieces of the chess
            {1, 2, 3, 4, 5, 3, 2, 1},
            {6, 6, 6, 6, 6, 6, 6, 6},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {66, 66, 66, 66, 66, 66, 66, 66},
            {11, 22, 33, 44, 55, 33, 22, 11},};
        JButton[][] chess1 = { // Iconst of the buttons
            {a1, b1, c1, e1, d1, f1, g1, h1},
            {a2, b2, c2, d2, e2, f2, g2, h2},
            {a3, b3, c3, d3, e3, f3, g3, h3},
            {a4, b4, c4, d4, e4, f4, g4, h4},
            {a5, b5, c5, d5, e5, f5, g5, h5},
            {a6, b6, c6, d6, e6, f6, g6, h6},
            {a7, b7, c7, d7, e7, f7, g7, h7},
            {a8, b8, c8, e8, d8, f8, g8, h8}
        };
        chess = chess1;
        board = board1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = 1;
            }
        }
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = 2;
            }
        }
        for (int i = 0; i < 8; ++i) {
            Arrays.fill(KingGrid[i], 0);
        }
        /** Initializing the Coordinates of the Pieces to Coordinates(-1,-1)*/
        Rook_Coordinates = new Coordinates();
       
   
    }

    public static void SetGreen() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 0) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            chess[i][j].setIcon(White_Icon);
                        } else {
                            chess[i][j].setIcon(Black_Icon);
                        }
                    } else {
                        if (j % 2 != 0) {
                            chess[i][j].setIcon(White_Icon);
                        } else {
                            chess[i][j].setIcon(Black_Icon);
                        }
                    }
                }
            }
        }
    }

    public void set() {// set icons
        setA1(chess[0][0]);
        setB1(chess[0][1]);
        setC1(chess[0][2]);
        setD1(chess[0][4]);
        setE1(chess[0][3]);
        setF1(chess[0][5]);
        setG1(chess[0][6]);
        setH1(chess[0][7]);
        setA2(chess[1][0]);
        setB2(chess[1][1]);
        setC2(chess[1][2]);
        setD2(chess[1][3]);
        setE2(chess[1][4]);
        setF2(chess[1][5]);
        setG2(chess[1][6]);
        setH2(chess[1][7]);
        setA3(chess[2][0]);
        setB3(chess[2][1]);
        setC3(chess[2][2]);
        setD3(chess[2][3]);
        setE3(chess[2][4]);
        setF3(chess[2][5]);
        setG3(chess[2][6]);
        setH3(chess[1][7]);
        setA4(chess[3][0]);
        setB4(chess[3][1]);
        setC4(chess[3][2]);
        setD4(chess[3][3]);
        setE4(chess[3][4]);
        setF4(chess[3][5]);
        setG4(chess[3][6]);
        setH4(chess[1][7]);
        setA5(chess[4][0]);
        setB5(chess[4][1]);
        setC5(chess[4][2]);
        setD5(chess[4][3]);
        setE5(chess[4][4]);
        setF5(chess[4][5]);
        setG5(chess[4][6]);
        setH5(chess[1][7]);
        setA6(chess[5][0]);
        setB6(chess[5][1]);
        setC6(chess[5][2]);
        setD6(chess[5][3]);
        setE6(chess[5][4]);
        setF6(chess[5][5]);
        setG6(chess[5][6]);
        setH6(chess[1][7]);
        setA7(chess[6][0]);
        setB7(chess[6][1]);
        setC7(chess[6][2]);
        setD7(chess[6][3]);
        setE7(chess[6][4]);
        setF7(chess[6][5]);
        setG7(chess[6][6]);
        setH7(chess[1][7]);
        setA8(chess[7][0]);
        setB8(chess[7][1]);
        setC8(chess[7][2]);
        setD8(chess[7][4]);
        setE8(chess[7][3]);
        setF8(chess[7][5]);
        setG8(chess[7][6]);
        setH8(chess[1][7]);
    }

    public static void setPlayerTurn() {// Sets the panel green for the player who has the turn
        if (player == 1) {
            player = 2;
            jLabel22.setText("*********");
            jLabel21.setText("---------");
        } else {
            player = 1;
            jLabel22.setText("---------");
            jLabel21.setText("*********");
        }
    }

    public void Do_The_Move(Coordinates To_Pos) {// do the move 
        pawn paw1 = new pawn();
        Rook rook = new Rook();
        Bishop bishop = new Bishop();
        Queen queen = new Queen();
        Knight knight = new Knight();
        King king = new King();
        int To_Pos_Row = To_Pos.Row;
        int To_Pos_Col = To_Pos.Col;
        if (chess[To_Pos_Row][To_Pos_Col].getIcon() == GreenIcon) {
            ispawn = false;
            Operation_Conter++;
            ser = true;
            JFXPanel pq = new JFXPanel();
            String url = new File("E:\\pic\\piece_move.wav").toURI().toString();
            new MediaPlayer(new Media(url)).play();
            int ni = -1, nj = -1;//indcies of the green cell that you're going to
            proc:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (ToGo_Pos[i][j] == true) {
                        ni = i;
                        nj = j;
                        break proc;
                    }
                }
            }
            SetGreen();
            num = grid[To_Pos_Row][To_Pos_Col];
            renum = board[To_Pos_Row][To_Pos_Col];
            if (board[ni][nj] == 6 || board[ni][nj] == 66) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = paw1.getPieceIcon(player, Piece_ICon, Pieces.Pawn);
                Resault_ICon = new ImageIcon(paw1.getPieceIcon((player == 1 ? 2 : 1), "", ((renum == 1 || renum == 11) ? Pieces.Rook : ((renum == 2 || renum == 22) ? Pieces.Knight : ((renum == 3 || renum == 33) ? Pieces.Bishop : ((renum == 4 || renum == 44) ? Pieces.Queen : ((renum == 6 || renum == 66) ? Pieces.Pawn : Pieces.King)))))));
                paw1.Resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                if (player == 1) {
                    if (board[To_Pos_Row][To_Pos_Col] == 6 && To_Pos_Row == 7) {
                        Promo_Piece_Row = To_Pos_Row;
                        Promo_Piece_Col = To_Pos_Col;
                        OpponentPlayer = player;
                        ispawn = true;
                        jPanel1.show();
                    }
                    player = 2;
                    jLabel22.setText("");
                    jLabel21.setText("-");
                } else {
                    if (board[To_Pos_Row][To_Pos_Col] == 66 && To_Pos_Row == 0) {
                        Promo_Piece_Row = To_Pos_Row;
                        Promo_Piece_Col = To_Pos_Col;
                        OpponentPlayer = player;
                        ispawn = true;
                        jPanel1.show();
                    }
                    player = 1;
                    jLabel22.setText("-");
                    jLabel21.setText("");
                }
                SetGreen();
            } else if (board[ni][nj] == 1 || board[ni][nj] == 11) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = rook.getPieceIcon(player, Piece_ICon, Pieces.Rook);
                Resault_ICon = new ImageIcon(rook.getPieceIcon((player == 1 ? 2 : 1), Piece_ICon, ((renum == 1 || renum == 11) ? Pieces.Rook : ((renum == 2 || renum == 22) ? Pieces.Knight : ((renum == 3 || renum == 33) ? Pieces.Bishop : ((renum == 4 || renum == 44) ? Pieces.Queen : ((renum == 6 || renum == 66) ? Pieces.Pawn : Pieces.King)))))));
                rook.resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                setPlayerTurn();

            } else if (board[ni][nj] == 3 || board[ni][nj] == 33) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = bishop.getPieceIcon(player, Piece_ICon, Pieces.Bishop);
                Resault_ICon = new ImageIcon(bishop.getPieceIcon((player == 1 ? 2 : 1), Piece_ICon, ((renum == 1 || renum == 11) ? Pieces.Rook : ((renum == 2 || renum == 22) ? Pieces.Knight : ((renum == 3 || renum == 33) ? Pieces.Bishop : ((renum == 4 || renum == 44) ? Pieces.Queen : ((renum == 6 || renum == 66) ? Pieces.Pawn : Pieces.King)))))));
                bishop.Resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                setPlayerTurn();

            } else if (board[ni][nj] == 4 || board[ni][nj] == 44) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = queen.getPieceIcon(player, Piece_ICon, Pieces.Queen);
                String ur = queen.getPieceIcon((player == 1 ? 2 : 1), Piece_ICon, ((renum == 1 || renum == 11) ? Pieces.Rook : ((renum == 2 || renum == 22) ? Pieces.Knight : ((renum == 3 || renum == 33) ? Pieces.Bishop : ((renum == 4 || renum == 44) ? Pieces.Queen : ((renum == 6 || renum == 66) ? Pieces.Pawn : Pieces.King))))));
                Resault_ICon = new ImageIcon(ur);
                queen.Resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                setPlayerTurn();

            } else if (board[ni][nj] == 2 || board[ni][nj] == 22) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = knight.getPieceIcon(player, Piece_ICon, Pieces.Knight);
                Resault_ICon = new ImageIcon(knight.getPieceIcon((player == 1 ? 2 : 1), Piece_ICon, ((renum == 1 || renum == 11) ? Pieces.Rook : ((renum == 2 || renum == 22) ? Pieces.Knight : ((renum == 3 || renum == 33) ? Pieces.Bishop : ((renum == 4 || renum == 44) ? Pieces.Queen : ((renum == 6 || renum == 66) ? Pieces.Pawn : Pieces.King)))))));
                knight.Resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                setPlayerTurn();

            } else if (board[ni][nj] == 5 || board[ni][nj] == 55) {
                idx1 = To_Pos_Row;
                idx2 = To_Pos_Col;
                Piece_ICon = king.getPieceIcon(player, Piece_ICon, Pieces.King);
                king.Resend();
                Icon nowicon = new ImageIcon(Piece_ICon);
                chess[To_Pos_Row][To_Pos_Col].setIcon(nowicon);
                grid[To_Pos_Row][To_Pos_Col] = player;
                grid[ni][nj] = 0;
                board[To_Pos_Row][To_Pos_Col] = board[ni][nj];
                board[ni][nj] = 0;
                setPlayerTurn();
            }
            row1 = To_Pos_Row;
            col1 = To_Pos_Col;
            row2 = ni;
            col2 = nj;
            Pair<Integer, Icon> put = new Pair<Integer, Icon>(renum, Resault_ICon);
            undo1.add(new Pair<Integer, Pair<Integer, Icon>>(num, put));
            undo2.add(new Pair<Integer, Integer>(row1, col1));
            undo3.add(new Pair<Integer, Integer>(row2, col2));
            king.getkingpos();
            check3 = true;
            isattacked = king.isAttacked(rowWKing, colWKing, (player == 1 ? 2 : 1));
            check3 = false;
            return;
        }
        rook.resend();
        paw1.Resend();
        bishop.Resend();
        queen.Resend();
        knight.Resend();
        king.Resend();
        king.getkingpos();
        check3 = true;
        isattacked = king.isAttacked(rowWKing, colWKing, (player == 1 ? 2 : 1));
        check3 = false;
        SetGreen();
    }

    public void runPawn(int indxi, int indxj, int move, int remove, int nxtplayer) {// Get the available moves of the pown
        pawn paw = new pawn(move, nxtplayer, chess[indxi][indxj].getIcon());
        paw.Move(White_Icon, Black_Icon, GreenIcon,new Coordinates(indxi,indxj));
        set();
    }

    public void runRook(int indxi, int indxj, int nxtplayer) {
        Rook pawl = new Rook(nxtplayer, chess[indxi][indxj].getIcon());
        pawl.Move(White_Icon, Black_Icon, GreenIcon, new Coordinates(indxi,indxj));
        set();
    }

    public void runBishop(int indxi, int indxj, int nxtplayer) {
        Bishop bishop = new Bishop(nxtplayer, chess[indxi][indxj].getIcon());
        bishop.Move(White_Icon, Black_Icon, GreenIcon,new Coordinates(indxi,indxj));
        set();
    }

    public void runQueen(int indxi, int indxj, int nxtplayer) {
        Queen queen = new Queen(nxtplayer, chess[indxi][indxj].getIcon());
        queen.Move(White_Icon, Black_Icon, GreenIcon, new Coordinates(indxi,indxj));
        set();
    }

    public void runKnight(int indxi, int indxj, int nxtplayer) {
        Knight knight = new Knight(nxtplayer, chess[indxi][indxj].getIcon());
        knight.Move(White_Icon, Black_Icon, GreenIcon,new Coordinates(indxi,indxj));
        set();
    }

    public void runKing(int indxi, int indxj, int nxtplayer) {
        King king = new King(nxtplayer, chess[indxi][indxj].getIcon());
        king.Move(White_Icon, Black_Icon, GreenIcon, new Coordinates(indxi,indxj));
        set();
    }

    public static void process(King king) {
        king.getkingpos();
        for (int i = 0; i < 8; ++i) {
            Arrays.fill(KingGrid[i], 0);
        }
        check3 = true;
        isattacked = king.isAttacked(W_KingRow, W_KingCol, 2);
        check3 = false;
        boolean isdeadx = king.isDead(W_KingRow, W_KingCol);
        for (int i = 0; i < 8; ++i) {
            Arrays.fill(KingGrid[i], 0);
        }
        check3 = true;
        isattacked = king.isAttacked(B_KingRow, B_KingCol, 1);
        check3 = false;
        boolean isdeady = king.isDead(B_KingRow, B_KingCol);
        if (player == 1) {
            for (int i = 0; i < 8; ++i) {
                Arrays.fill(KingGrid[i], 0);
            }
            check3 = true;
            king.isAttacked(W_KingRow, W_KingCol, 2);
            check3 = false;
            king.isDead(W_KingRow, W_KingCol);
        } else {
            for (int i = 0; i < 8; ++i) {
                Arrays.fill(KingGrid[i], 0);
            }
            check3 = true;
            king.isAttacked(B_KingRow, B_KingCol, 1);
            check3 = false;
            king.isDead(B_KingRow, B_KingCol);
        }
        check3 = true;
        isattacked = king.isAttacked(rowWKing, colWKing, (player == 1 ? 2 : 1));
        check3 = false;
        if ((isdeadx && isdeady) || king.isDraw()) {
            result res = new result("E:\\pic\\draw.png");
            res.show();
        }
        if (isdeadx) {
            result res = new result("E:\\pic\\blackWins.png");
            res.show();

        }
        if (isdeady) {
            result res = new result("E:\\pic\\whiteWins.png");
            res.show();
        }
    }

    public void Cellwork(Coordinates Pos) {// Calls the Move Function of the piece you're setting on and the player who playes it
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
        
        pawn paw1 = new pawn();
        Rook paw2 = new Rook();
        Bishop bishop = new Bishop();
        Queen queen = new Queen();
        Knight knight = new Knight();
        King king = new King();
        for (int i = 0; i < 10; i++) {
            Arrays.fill(ToGo_Pos[i], false);
        }
        process(king);
        go = true;
        if (grid[Pos_Row][Pos_Col] == player && player == 1) {
            ToGo_Pos[Pos_Row][Pos_Col] = true;
            if (board[Pos_Row][Pos_Col] == 6) {
                runPawn(Pos_Row, Pos_Col, 1, 2, 2);
            }
            if (board[Pos_Row][Pos_Col] == 1) {
                runRook(Pos_Row, Pos_Col, 2);
            } else if (board[Pos_Row][Pos_Col] == 3) {
                runBishop(Pos_Row, Pos_Col, 2);
            } else if (board[Pos_Row][Pos_Col] == 4) {
                runQueen(Pos_Row, Pos_Col, 2);
            } else if (board[Pos_Row][Pos_Col] == 2) {
                runKnight(Pos_Row, Pos_Col, 2);
            } else if (board[Pos_Row][Pos_Col] == 5) {
                runKing(Pos_Row, Pos_Col, 2);
            }
        } else if (grid[Pos_Row][Pos_Col] == player && player == 2) {
            ToGo_Pos[Pos_Row][Pos_Col] = true;
            if (board[Pos_Row][Pos_Col] == 66) {
                runPawn(Pos_Row, Pos_Col, -1, -2, 1);
            }
            if (board[Pos_Row][Pos_Col] == 11) {
                runRook(Pos_Row, Pos_Col, 1);
            } else if (board[Pos_Row][Pos_Col] == 33) {
                runBishop(Pos_Row, Pos_Col, 1);
            } else if (board[Pos_Row][Pos_Col] == 44) {
                runQueen(Pos_Row, Pos_Col, 1);
            } else if (board[Pos_Row][Pos_Col] == 22) {
                runKnight(Pos_Row, Pos_Col, 1);
            } else if (board[Pos_Row][Pos_Col] == 55) {
                runKing(Pos_Row, Pos_Col, 1);
            }
            go = false;
        } else {
            queen.Resend();
            knight.Resend();
            bishop.Resend();
            paw1.Resend();
            paw2.resend();
            king.Resend();
            SetGreen();
        }
        isat = go = ser = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        h8 = new javax.swing.JButton();
        h7 = new javax.swing.JButton();
        h6 = new javax.swing.JButton();
        h5 = new javax.swing.JButton();
        h3 = new javax.swing.JButton();
        h4 = new javax.swing.JButton();
        h2 = new javax.swing.JButton();
        h1 = new javax.swing.JButton();
        g1 = new javax.swing.JButton();
        g2 = new javax.swing.JButton();
        g4 = new javax.swing.JButton();
        g3 = new javax.swing.JButton();
        g5 = new javax.swing.JButton();
        g6 = new javax.swing.JButton();
        g7 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        g8 = new javax.swing.JButton();
        f8 = new javax.swing.JButton();
        f7 = new javax.swing.JButton();
        f6 = new javax.swing.JButton();
        f5 = new javax.swing.JButton();
        f4 = new javax.swing.JButton();
        f3 = new javax.swing.JButton();
        f2 = new javax.swing.JButton();
        f1 = new javax.swing.JButton();
        e1 = new javax.swing.JButton();
        e2 = new javax.swing.JButton();
        e3 = new javax.swing.JButton();
        e4 = new javax.swing.JButton();
        e5 = new javax.swing.JButton();
        e6 = new javax.swing.JButton();
        e7 = new javax.swing.JButton();
        e8 = new javax.swing.JButton();
        d8 = new javax.swing.JButton();
        c8 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        a8 = new javax.swing.JButton();
        a7 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        c7 = new javax.swing.JButton();
        d7 = new javax.swing.JButton();
        d6 = new javax.swing.JButton();
        c6 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        a6 = new javax.swing.JButton();
        a5 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        c5 = new javax.swing.JButton();
        d5 = new javax.swing.JButton();
        d4 = new javax.swing.JButton();
        c4 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        a4 = new javax.swing.JButton();
        a3 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        d3 = new javax.swing.JButton();
        c3 = new javax.swing.JButton();
        d2 = new javax.swing.JButton();
        c2 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        a2 = new javax.swing.JButton();
        a1 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        c1 = new javax.swing.JButton();
        d1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(180, 100));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(204, 102, 0));
        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Black");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 50, 20));

        jLabel4.setBackground(new java.awt.Color(204, 102, 0));
        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("White");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 60, 20));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/right.PNG"))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 140, 60));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/down.PNG"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 570, 20));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/right.PNG"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 40, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/1.PNG"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 30, 70));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/2.PNG"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 30, 70));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/3.PNG"))); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 30, 70));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/4.PNG"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 30, 70));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/5.PNG"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 30, 70));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/6.PNG"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 30, 70));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/8.PNG"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 30, 70));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/7.PNG"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 30, 70));

        h8.setIcon(new javax.swing.ImageIcon("E:\\pic\\alb.PNG")); // NOI18N
        h8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h8ActionPerformed(evt);
            }
        });
        getContentPane().add(h8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 70, 70));

        h7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbb.PNG")); // NOI18N
        h7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h7ActionPerformed(evt);
            }
        });
        getContentPane().add(h7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 70, 70));

        h6.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        h6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h6ActionPerformed(evt);
            }
        });
        getContentPane().add(h6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 70, 70));

        h5.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        h5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h5ActionPerformed(evt);
            }
        });
        getContentPane().add(h5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 70, 70));

        h3.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        h3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h3ActionPerformed(evt);
            }
        });
        getContentPane().add(h3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 70, 70));

        h4.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        h4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h4ActionPerformed(evt);
            }
        });
        getContentPane().add(h4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 70, 70));

        h2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jll.PNG")); // NOI18N
        h2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h2ActionPerformed(evt);
            }
        });
        getContentPane().add(h2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 70, 70));

        h1.setIcon(new javax.swing.ImageIcon("E:\\pic\\abl.PNG")); // NOI18N
        h1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h1ActionPerformed(evt);
            }
        });
        getContentPane().add(h1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 70, 70));

        g1.setIcon(new javax.swing.ImageIcon("E:\\pic\\bll.PNG")); // NOI18N
        g1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1ActionPerformed(evt);
            }
        });
        getContentPane().add(g1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, 70, 70));

        g2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbl.PNG")); // NOI18N
        g2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g2ActionPerformed(evt);
            }
        });
        getContentPane().add(g2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 70, 70));

        g4.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        g4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g4ActionPerformed(evt);
            }
        });
        getContentPane().add(g4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 70, 70));

        g3.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        g3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g3ActionPerformed(evt);
            }
        });
        getContentPane().add(g3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 70, 70));

        g5.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        g5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g5ActionPerformed(evt);
            }
        });
        getContentPane().add(g5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 70, 70));

        g6.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        g6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g6ActionPerformed(evt);
            }
        });
        getContentPane().add(g6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 70, 70));

        g7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jlb.PNG")); // NOI18N
        g7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g7ActionPerformed(evt);
            }
        });
        getContentPane().add(g7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 70, 70));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/up.PNG"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 10));

        g8.setIcon(new javax.swing.ImageIcon("E:\\pic\\bbb.PNG")); // NOI18N
        g8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g8ActionPerformed(evt);
            }
        });
        getContentPane().add(g8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 70, 70));

        f8.setIcon(new javax.swing.ImageIcon("E:\\pic\\clb.PNG")); // NOI18N
        f8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f8ActionPerformed(evt);
            }
        });
        getContentPane().add(f8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 70, 70));

        f7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbb.PNG")); // NOI18N
        f7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f7ActionPerformed(evt);
            }
        });
        getContentPane().add(f7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 70, 70));

        f6.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        f6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f6ActionPerformed(evt);
            }
        });
        getContentPane().add(f6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 70, 70));

        f5.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        f5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f5ActionPerformed(evt);
            }
        });
        getContentPane().add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 70, 70));

        f4.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        f4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f4ActionPerformed(evt);
            }
        });
        getContentPane().add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 70, 70));

        f3.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        f3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f3ActionPerformed(evt);
            }
        });
        getContentPane().add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 70, 70));

        f2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jll.PNG")); // NOI18N
        f2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f2ActionPerformed(evt);
            }
        });
        getContentPane().add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 70, 70));

        f1.setIcon(new javax.swing.ImageIcon("E:\\pic\\cbl.PNG")); // NOI18N
        f1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f1ActionPerformed(evt);
            }
        });
        getContentPane().add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 70, 70));

        e1.setIcon(new javax.swing.ImageIcon("E:\\pic\\wbq.PNG")); // NOI18N
        e1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e1ActionPerformed(evt);
            }
        });
        getContentPane().add(e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 70, 70));

        e2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbl.PNG")); // NOI18N
        e2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e2ActionPerformed(evt);
            }
        });
        getContentPane().add(e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 70, 70));

        e3.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        e3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e3ActionPerformed(evt);
            }
        });
        getContentPane().add(e3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 70, 70));

        e4.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        e4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e4ActionPerformed(evt);
            }
        });
        getContentPane().add(e4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 70, 70));

        e5.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        e5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e5ActionPerformed(evt);
            }
        });
        getContentPane().add(e5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 70, 70));

        e6.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        e6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e6ActionPerformed(evt);
            }
        });
        getContentPane().add(e6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 70, 70));

        e7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jlb.PNG")); // NOI18N
        e7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e7ActionPerformed(evt);
            }
        });
        getContentPane().add(e7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 70, 70));

        e8.setIcon(new javax.swing.ImageIcon("E:\\pic\\bwq.PNG")); // NOI18N
        e8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e8ActionPerformed(evt);
            }
        });
        getContentPane().add(e8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 70, 70));

        d8.setIcon(new javax.swing.ImageIcon("E:\\pic\\lbb.PNG")); // NOI18N
        d8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d8ActionPerformed(evt);
            }
        });
        getContentPane().add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 70, 70));

        c8.setIcon(new javax.swing.ImageIcon("E:\\pic\\fbb.PNG")); // NOI18N
        c8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c8ActionPerformed(evt);
            }
        });
        getContentPane().add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 70, 70));

        b8.setIcon(new javax.swing.ImageIcon("E:\\pic\\glb.PNG")); // NOI18N
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        getContentPane().add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 70));

        a8.setIcon(new javax.swing.ImageIcon("E:\\pic\\hbb.PNG")); // NOI18N
        a8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a8ActionPerformed(evt);
            }
        });
        getContentPane().add(a8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 70));

        a7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jlb.PNG")); // NOI18N
        a7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a7ActionPerformed(evt);
            }
        });
        getContentPane().add(a7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, 70));

        b7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbb.PNG")); // NOI18N
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        getContentPane().add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 70, 70));

        c7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jlb.PNG")); // NOI18N
        c7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c7ActionPerformed(evt);
            }
        });
        getContentPane().add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 70, 70));

        d7.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbb.PNG")); // NOI18N
        d7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d7ActionPerformed(evt);
            }
        });
        getContentPane().add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 70, 70));

        d6.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        d6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d6ActionPerformed(evt);
            }
        });
        getContentPane().add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 70, 70));

        c6.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        c6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c6ActionPerformed(evt);
            }
        });
        getContentPane().add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 70, 70));

        b6.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        getContentPane().add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 70, 70));

        a6.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        a6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a6ActionPerformed(evt);
            }
        });
        getContentPane().add(a6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 70, 70));

        a5.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        a5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a5ActionPerformed(evt);
            }
        });
        getContentPane().add(a5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 70, 70));

        b5.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        getContentPane().add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 70));

        c5.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        c5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5ActionPerformed(evt);
            }
        });
        getContentPane().add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 70, 70));

        d5.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        d5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d5ActionPerformed(evt);
            }
        });
        getContentPane().add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 70, 70));

        d4.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        d4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d4ActionPerformed(evt);
            }
        });
        getContentPane().add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 70, 70));

        c4.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        c4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4ActionPerformed(evt);
            }
        });
        getContentPane().add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 70, 70));

        b4.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        getContentPane().add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 70, 70));

        a4.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a4ActionPerformed(evt);
            }
        });
        getContentPane().add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 70, 70));

        a3.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a3ActionPerformed(evt);
            }
        });
        getContentPane().add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 70, 70));

        b3.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        getContentPane().add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 70, 70));

        d3.setIcon(new javax.swing.ImageIcon("E:\\pic\\black.PNG")); // NOI18N
        d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d3ActionPerformed(evt);
            }
        });
        getContentPane().add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 70, 70));

        c3.setIcon(new javax.swing.ImageIcon("E:\\pic\\white.PNG")); // NOI18N
        c3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3ActionPerformed(evt);
            }
        });
        getContentPane().add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 70, 70));

        d2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jll.PNG")); // NOI18N
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2ActionPerformed(evt);
            }
        });
        getContentPane().add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 70, 70));

        c2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbl.PNG")); // NOI18N
        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });
        getContentPane().add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 70, 70));

        b2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jll.PNG")); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        getContentPane().add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 70, 70));

        a2.setIcon(new javax.swing.ImageIcon("E:\\pic\\jbl.PNG")); // NOI18N
        a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a2ActionPerformed(evt);
            }
        });
        getContentPane().add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 70, 70));

        a1.setIcon(new javax.swing.ImageIcon("E:\\pic\\hll.PNG")); // NOI18N
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });
        getContentPane().add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 70, 70));

        b1.setIcon(new javax.swing.ImageIcon("E:\\pic\\gbl.PNG")); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        getContentPane().add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 70, 70));

        c1.setIcon(new javax.swing.ImageIcon("E:\\pic\\fll.PNG")); // NOI18N
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });
        getContentPane().add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 70, 70));

        d1.setIcon(new javax.swing.ImageIcon("E:\\pic\\www.PNG")); // NOI18N
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });
        getContentPane().add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 70, 70));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel20.setText("Promote Pawn");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 5, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(204, 51, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Knight", "Rooks", "Bishops", "Queen" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 26, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setText("Done!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 51, -1, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/right.PNG"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 120, 110));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 110, 100));

        jLabel21.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("-");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 30, 20));

        jLabel22.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("-");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 30, 20));

        jButton2.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/right.PNG"))); // NOI18N
        jButton2.setText("Draw");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 70, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/undoButton.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 60, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/right.PNG"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 140, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void h8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,7));
        Cellwork(new Coordinates(7,7));
    }//GEN-LAST:event_h8ActionPerformed

    private void h7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,7));
        Cellwork(new Coordinates(6,7));
    }//GEN-LAST:event_h7ActionPerformed

    private void h6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,7));
        Cellwork(new Coordinates(5,7));
    }//GEN-LAST:event_h6ActionPerformed

    private void h5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,7));
        Cellwork(new Coordinates(4,7));
    }//GEN-LAST:event_h5ActionPerformed

    private void h3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h3ActionPerformed
        // TODO add your handling code here: 
        Do_The_Move(new Coordinates(2,7));
        Cellwork(new Coordinates(2,7));
    }//GEN-LAST:event_h3ActionPerformed

    private void h4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,7));
        Cellwork(new Coordinates(3,7));
    }//GEN-LAST:event_h4ActionPerformed

    private void h2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,7));
        Cellwork(new Coordinates(1,7));
    }//GEN-LAST:event_h2ActionPerformed

    private void h1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,7));
        Cellwork(new Coordinates(0,7));
    }//GEN-LAST:event_h1ActionPerformed

    private void g1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,6));
        Cellwork(new Coordinates(0,6));
    }//GEN-LAST:event_g1ActionPerformed

    private void g2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,6));
        Cellwork(new Coordinates(1,6));
    }//GEN-LAST:event_g2ActionPerformed

    private void g4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,6));
        Cellwork(new Coordinates(3,6));
    }//GEN-LAST:event_g4ActionPerformed

    private void g3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,6));
        Cellwork(new Coordinates(2,6));
    }//GEN-LAST:event_g3ActionPerformed

    private void g5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,6));
        Cellwork(new Coordinates(4,6));
    }//GEN-LAST:event_g5ActionPerformed

    private void g6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,6));
        Cellwork(new Coordinates(5,6));
    }//GEN-LAST:event_g6ActionPerformed

    private void g7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,6));
        Cellwork(new Coordinates(6,6));
    }//GEN-LAST:event_g7ActionPerformed

    private void g8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,6));
        Cellwork(new Coordinates(7,6));
    }//GEN-LAST:event_g8ActionPerformed

    private void f8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,5));
        Cellwork(new Coordinates(7,5));
    }//GEN-LAST:event_f8ActionPerformed

    private void f7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,5));
        Cellwork(new Coordinates(6,5));
    }//GEN-LAST:event_f7ActionPerformed

    private void f6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,5));
        Cellwork(new Coordinates(5,5));
    }//GEN-LAST:event_f6ActionPerformed

    private void f5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,5));
        Cellwork(new Coordinates(4,5));
    }//GEN-LAST:event_f5ActionPerformed

    private void f4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,5));
        Cellwork(new Coordinates(3,5));
    }//GEN-LAST:event_f4ActionPerformed

    private void f3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,5));
        Cellwork(new Coordinates(2,5));
    }//GEN-LAST:event_f3ActionPerformed

    private void f2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,5));
        Cellwork(new Coordinates(1,5));
    }//GEN-LAST:event_f2ActionPerformed

    private void f1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,5));
        Cellwork(new Coordinates(0,5));
    }//GEN-LAST:event_f1ActionPerformed

    private void e1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,3));
        Cellwork(new Coordinates(0,3));
    }//GEN-LAST:event_e1ActionPerformed

    private void e2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,4));
        Cellwork(new Coordinates(1,4));
    }//GEN-LAST:event_e2ActionPerformed

    private void e3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,4));
        Cellwork(new Coordinates(2,4));
    }//GEN-LAST:event_e3ActionPerformed

    private void e4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,4));
        Cellwork(new Coordinates(3,4));
    }//GEN-LAST:event_e4ActionPerformed

    private void e5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,4));
        Cellwork(new Coordinates(4,4));
    }//GEN-LAST:event_e5ActionPerformed

    private void e6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,4));
        Cellwork(new Coordinates(5,4));
    }//GEN-LAST:event_e6ActionPerformed

    private void e7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,4));
        Cellwork(new Coordinates(6,4));
    }//GEN-LAST:event_e7ActionPerformed

    private void e8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,3));
        Cellwork(new Coordinates(7,3));
    }//GEN-LAST:event_e8ActionPerformed

    private void d8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,4));
        Cellwork(new Coordinates(7,4));
    }//GEN-LAST:event_d8ActionPerformed

    private void c8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,2));
        Cellwork(new Coordinates(7,2));
    }//GEN-LAST:event_c8ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,1));
        Cellwork(new Coordinates(7,1));
    }//GEN-LAST:event_b8ActionPerformed

    private void a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a8ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(7,0));
        Cellwork(new Coordinates(7,0));
    }//GEN-LAST:event_a8ActionPerformed

    private void a7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,0));
        Cellwork(new Coordinates(6,0));
    }//GEN-LAST:event_a7ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,1));
        Cellwork(new Coordinates(6,1));
    }//GEN-LAST:event_b7ActionPerformed

    private void c7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,2));
        Cellwork(new Coordinates(6,2));
    }//GEN-LAST:event_c7ActionPerformed

    private void d7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d7ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(6,3));
        Cellwork(new Coordinates(6,3));
    }//GEN-LAST:event_d7ActionPerformed

    private void d6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,3));
        Cellwork(new Coordinates(5,3));
    }//GEN-LAST:event_d6ActionPerformed

    private void c6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,2));
        Cellwork(new Coordinates(5,2));
    }//GEN-LAST:event_c6ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,1));
        Cellwork(new Coordinates(5,1));
    }//GEN-LAST:event_b6ActionPerformed

    private void a6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a6ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(5,0));
        Cellwork(new Coordinates(5,0));
    }//GEN-LAST:event_a6ActionPerformed

    private void a5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,0));
        Cellwork(new Coordinates(4,0));
    }//GEN-LAST:event_a5ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,1));
        Cellwork(new Coordinates(4,1));
    }//GEN-LAST:event_b5ActionPerformed

    private void c5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,2));
        Cellwork(new Coordinates(4,2));
    }//GEN-LAST:event_c5ActionPerformed

    private void d5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d5ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(4,3));
        Cellwork(new Coordinates(4,3));
    }//GEN-LAST:event_d5ActionPerformed

    private void d4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,3));
        Cellwork(new Coordinates(3,3));
    }//GEN-LAST:event_d4ActionPerformed

    private void c4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,2));
        Cellwork(new Coordinates(3,2));
    }//GEN-LAST:event_c4ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,1));
        Cellwork(new Coordinates(3,1));
    }//GEN-LAST:event_b4ActionPerformed

    private void a4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a4ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(3,0));
        Cellwork(new Coordinates(3,0));
    }//GEN-LAST:event_a4ActionPerformed

    private void a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,0));
        Cellwork(new Coordinates(2,0));
    }//GEN-LAST:event_a3ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,1));
        Cellwork(new Coordinates(2,1));
    }//GEN-LAST:event_b3ActionPerformed

    private void d3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,3));
        Cellwork(new Coordinates(2,3));
    }//GEN-LAST:event_d3ActionPerformed

    private void c3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(2,2));
        Cellwork(new Coordinates(2,2));
    }//GEN-LAST:event_c3ActionPerformed

    private void d2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,3));
        Cellwork(new Coordinates(1,3));
    }//GEN-LAST:event_d2ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,2));
        Cellwork(new Coordinates(1,2));
    }//GEN-LAST:event_c2ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,1));
        Cellwork(new Coordinates(1,1));
    }//GEN-LAST:event_b2ActionPerformed

    private void a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a2ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(1,0));
        Cellwork(new Coordinates(1,0));
    }//GEN-LAST:event_a2ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,0));
        Cellwork(new Coordinates(0,0));
    }//GEN-LAST:event_a1ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,1));
        Cellwork(new Coordinates(0,1));
    }//GEN-LAST:event_b1ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,2));
        Cellwork(new Coordinates(0,2));
    }//GEN-LAST:event_c1ActionPerformed

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        // TODO add your handling code here:
        Do_The_Move(new Coordinates(0,4));
        Cellwork(new Coordinates(0,4));
    }//GEN-LAST:event_d1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jComboBox1.getSelectedItem().toString() == "Knight") {
            if (Promo_Piece_Row % 2 == 0) {
                if (Promo_Piece_Col % 2 == 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\bll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 2;
                    } else {
                        ncon = "E:\\pic\\glb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 22;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\gbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 2;
                    } else {
                        ncon = "E:\\pic\\bbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 22;
                    }
                }
            } else {
                if (Promo_Piece_Col % 2 != 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\bll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 2;
                    } else {
                        ncon = "E:\\pic\\glb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 22;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\gbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 2;
                    } else {
                        ncon = "E:\\pic\\bbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 22;
                    }
                }
            }
        } else if (jComboBox1.getSelectedItem().toString() == "Rooks") {
            if (Promo_Piece_Row % 2 == 0) {
                if (Promo_Piece_Col % 2 == 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\hll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 1;
                    } else {
                        ncon = "E:\\pic\\alb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 11;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\abl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 1;
                    } else {
                        ncon = "E:\\pic\\hbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 11;
                    }
                }
            } else {
                if (Promo_Piece_Col % 2 != 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\hll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 1;
                    } else {
                        ncon = "E:\\pic\\alb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 11;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\abl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 1;
                    } else {
                        ncon = "E:\\pic\\hbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 11;
                    }
                }
            }
        } else if (jComboBox1.getSelectedItem().toString() == "Bishops") {
            if (Promo_Piece_Row % 2 == 0) {
                if (Promo_Piece_Col % 2 == 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\fll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 3;
                    } else {
                        ncon = "E:\\pic\\clb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 33;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\cbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 3;
                    } else {
                        ncon = "E:\\pic\\fbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 33;
                    }
                }
            } else {
                if (Promo_Piece_Col % 2 != 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\fll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 3;
                    } else {
                        ncon = "E:\\pic\\clb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 33;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\cbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 3;
                    } else {
                        ncon = "E:\\pic\\fbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 33;
                    }
                }
            }
        } else {
            if (Promo_Piece_Row % 2 == 0) {
                if (Promo_Piece_Col % 2 == 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\dll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 4;
                    } else {
                        ncon = "E:\\pic\\dllb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 44;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\dbbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 4;
                    } else {
                        ncon = "E:\\pic\\dbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 44;
                    }
                }
            } else {
                if (Promo_Piece_Col % 2 != 0) {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\dll.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 4;
                    } else {
                        ncon = "E:\\pic\\dllb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 44;
                    }
                } else {
                    if (OpponentPlayer == 1) {
                        ncon = "E:\\pic\\dbbl.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 4;
                    } else {
                        ncon = "E:\\pic\\dbb.PNG";
                        board[Promo_Piece_Row][Promo_Piece_Col] = 44;
                    }
                }
            }
        }
        chess[Promo_Piece_Row][Promo_Piece_Col].setIcon(new ImageIcon(ncon));
        jPanel1.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Draw_by_agreement pop = new Draw_by_agreement();
        pop.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (undo1.size() == 0) {
            JOptionPane.showMessageDialog(this, "!!UNDO ISN'T ALLOWED!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        undo pop = new undo();
        pop.show();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Table(player).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton a1;
    private javax.swing.JButton a2;
    private javax.swing.JButton a3;
    private javax.swing.JButton a4;
    private javax.swing.JButton a5;
    private javax.swing.JButton a6;
    private javax.swing.JButton a7;
    private javax.swing.JButton a8;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton c1;
    private javax.swing.JButton c2;
    private javax.swing.JButton c3;
    private javax.swing.JButton c4;
    private javax.swing.JButton c5;
    private javax.swing.JButton c6;
    private javax.swing.JButton c7;
    private javax.swing.JButton c8;
    private javax.swing.JButton d1;
    private javax.swing.JButton d2;
    private javax.swing.JButton d3;
    private javax.swing.JButton d4;
    private javax.swing.JButton d5;
    private javax.swing.JButton d6;
    private javax.swing.JButton d7;
    private javax.swing.JButton d8;
    private javax.swing.JButton e1;
    private javax.swing.JButton e2;
    private javax.swing.JButton e3;
    private javax.swing.JButton e4;
    private javax.swing.JButton e5;
    private javax.swing.JButton e6;
    private javax.swing.JButton e7;
    private javax.swing.JButton e8;
    private javax.swing.JButton f1;
    private javax.swing.JButton f2;
    private javax.swing.JButton f3;
    private javax.swing.JButton f4;
    private javax.swing.JButton f5;
    private javax.swing.JButton f6;
    private javax.swing.JButton f7;
    private javax.swing.JButton f8;
    private javax.swing.JButton g1;
    private javax.swing.JButton g2;
    private javax.swing.JButton g3;
    private javax.swing.JButton g4;
    private javax.swing.JButton g5;
    private javax.swing.JButton g6;
    private javax.swing.JButton g7;
    private javax.swing.JButton g8;
    private javax.swing.JButton h1;
    private javax.swing.JButton h2;
    private javax.swing.JButton h3;
    private javax.swing.JButton h4;
    private javax.swing.JButton h5;
    private javax.swing.JButton h6;
    private javax.swing.JButton h7;
    private javax.swing.JButton h8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private static javax.swing.JLabel jLabel21;
    private static javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public static void setGrid(int[][] grid) {
        Table.grid = grid;
    }

    public void setA1(JButton a1) {
        this.a1 = a1;
    }

    public void setA2(JButton a2) {
        this.a2 = a2;
    }

    public void setA3(JButton a3) {
        this.a3 = a3;
    }

    public void setA4(JButton a4) {
        this.a4 = a4;
    }

    public void setA5(JButton a5) {
        this.a5 = a5;
    }

    public void setA6(JButton a6) {
        this.a6 = a6;
    }

    public void setA7(JButton a7) {
        this.a7 = a7;
    }

    public void setA8(JButton a8) {
        this.a8 = a8;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public void setB3(JButton b3) {
        this.b3 = b3;
    }

    public void setB4(JButton b4) {
        this.b4 = b4;
    }

    public void setB5(JButton b5) {
        this.b5 = b5;
    }

    public void setB6(JButton b6) {
        this.b6 = b6;
    }

    public void setB7(JButton b7) {
        this.b7 = b7;
    }

    public void setB8(JButton b8) {
        this.b8 = b8;
    }

    public void setC1(JButton c1) {
        this.c1 = c1;
    }

    public void setC2(JButton c2) {
        this.c2 = c2;
    }

    public void setC3(JButton c3) {
        this.c3 = c3;
    }

    public void setC4(JButton c4) {
        this.c4 = c4;
    }

    public void setC5(JButton c5) {
        this.c5 = c5;
    }

    public void setC6(JButton c6) {
        this.c6 = c6;
    }

    public void setC7(JButton c7) {
        this.c7 = c7;
    }

    public void setC8(JButton c8) {
        this.c8 = c8;
    }

    public void setD1(JButton d1) {
        this.d1 = d1;
    }

    public void setD2(JButton d2) {
        this.d2 = d2;
    }

    public void setD3(JButton d3) {
        this.d3 = d3;
    }

    public void setD4(JButton d4) {
        this.d4 = d4;
    }

    public void setD5(JButton d5) {
        this.d5 = d5;
    }

    public void setD6(JButton d6) {
        this.d6 = d6;
    }

    public void setD7(JButton d7) {
        this.d7 = d7;
    }

    public void setD8(JButton d8) {
        this.d8 = d8;
    }

    public void setE1(JButton e1) {
        this.e1 = e1;
    }

    public void setE2(JButton e2) {
        this.e2 = e2;
    }

    public void setE3(JButton e3) {
        this.e3 = e3;
    }

    public void setE4(JButton e4) {
        this.e4 = e4;
    }

    public void setE5(JButton e5) {
        this.e5 = e5;
    }

    public void setE6(JButton e6) {
        this.e6 = e6;
    }

    public void setE7(JButton e7) {
        this.e7 = e7;
    }

    public void setE8(JButton e8) {
        this.e8 = e8;
    }

    public void setF1(JButton f1) {
        this.f1 = f1;
    }

    public void setF2(JButton f2) {
        this.f2 = f2;
    }

    public void setF3(JButton f3) {
        this.f3 = f3;
    }

    public void setF4(JButton f4) {
        this.f4 = f4;
    }

    public void setF5(JButton f5) {
        this.f5 = f5;
    }

    public void setF6(JButton f6) {
        this.f6 = f6;
    }

    public void setF7(JButton f7) {
        this.f7 = f7;
    }

    public void setF8(JButton f8) {
        this.f8 = f8;
    }

    public void setG1(JButton g1) {
        this.g1 = g1;
    }

    public void setG2(JButton g2) {
        this.g2 = g2;
    }

    public void setG3(JButton g3) {
        this.g3 = g3;
    }

    public void setG4(JButton g4) {
        this.g4 = g4;
    }

    public void setG5(JButton g5) {
        this.g5 = g5;
    }

    public void setG6(JButton g6) {
        this.g6 = g6;
    }

    public void setG7(JButton g7) {
        this.g7 = g7;
    }

    public void setG8(JButton g8) {
        this.g8 = g8;
    }

    public void setH1(JButton h1) {
        this.h1 = h1;
    }

    public void setH2(JButton h2) {
        this.h2 = h2;
    }

    public void setH3(JButton h3) {
        this.h3 = h3;
    }

    public void setH4(JButton h4) {
        this.h4 = h4;
    }

    public void setH5(JButton h5) {
        this.h5 = h5;
    }

    public void setH6(JButton h6) {
        this.h6 = h6;
    }

    public void setH7(JButton h7) {
        this.h7 = h7;
    }

    public void setH8(JButton h8) {
        this.h8 = h8;
    }

    public static JButton[][] getChess() {
        return chess;
    }

    public static int[][] getGrid() {
        return grid;
    }

    public JButton getA1() {
        return a1;
    }

    public JButton getA2() {
        return a2;
    }

    public JButton getA3() {
        return a3;
    }

    public JButton getA4() {
        return a4;
    }

    public JButton getA5() {
        return a5;
    }

    public JButton getA6() {
        return a6;
    }

    public JButton getA7() {
        return a7;
    }

    public JButton getA8() {
        return a8;
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getB5() {
        return b5;
    }

    public JButton getB6() {
        return b6;
    }

    public JButton getB7() {
        return b7;
    }

    public JButton getB8() {
        return b8;
    }

    public JButton getC1() {
        return c1;
    }

    public JButton getC2() {
        return c2;
    }

    public JButton getC3() {
        return c3;
    }

    public JButton getC4() {
        return c4;
    }

    public JButton getC5() {
        return c5;
    }

    public JButton getC6() {
        return c6;
    }

    public JButton getC7() {
        return c7;
    }

    public JButton getC8() {
        return c8;
    }

    public JButton getD1() {
        return d1;
    }

    public JButton getD2() {
        return d2;
    }

    public JButton getD3() {
        return d3;
    }

    public JButton getD4() {
        return d4;
    }

    public JButton getD5() {
        return d5;
    }

    public JButton getD6() {
        return d6;
    }

    public JButton getD7() {
        return d7;
    }

    public JButton getD8() {
        return d8;
    }

    public JButton getE1() {
        return e1;
    }

    public JButton getE2() {
        return e2;
    }

    public JButton getE3() {
        return e3;
    }

    public JButton getE4() {
        return e4;
    }

    public JButton getE5() {
        return e5;
    }

    public JButton getE6() {
        return e6;
    }

    public JButton getE7() {
        return e7;
    }

    public JButton getE8() {
        return e8;
    }

    public JButton getF1() {
        return f1;
    }

    public JButton getF2() {
        return f2;
    }

    public JButton getF3() {
        return f3;
    }

    public JButton getF4() {
        return f4;
    }

    public JButton getF5() {
        return f5;
    }

    public JButton getF6() {
        return f6;
    }

    public JButton getF7() {
        return f7;
    }

    public JButton getF8() {
        return f8;
    }

    public JButton getG1() {
        return g1;
    }

    public JButton getG2() {
        return g2;
    }

    public JButton getG3() {
        return g3;
    }

    public JButton getG4() {
        return g4;
    }

    public JButton getG5() {
        return g5;
    }

    public JButton getG6() {
        return g6;
    }

    public JButton getG7() {
        return g7;
    }

    public JButton getG8() {
        return g8;
    }

    public JButton getH1() {
        return h1;
    }

    public JButton getH2() {
        return h2;
    }

    public JButton getH3() {
        return h3;
    }

    public JButton getH4() {
        return h4;
    }

    public JButton getH5() {
        return h5;
    }

    public JButton getH6() {
        return h6;
    }

    public JButton getH7() {
        return h7;
    }

    public JButton getH8() {
        return h8;
    }
}
