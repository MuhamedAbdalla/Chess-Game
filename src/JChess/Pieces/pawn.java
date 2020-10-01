/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JChess.Pieces;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import static test.Table.*;

/**
 *
 * @author 20114
 */
public class pawn extends piece {

    private int di;

    public pawn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
        if (player == 1) {
            OpponentPiece = 2;
        } else {
            OpponentPiece = 1;
        }
    }

    public pawn(int di, int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
        this.di = di;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
    }

    public void Move(Icon white, Icon black, Icon GreenIcon,Coordinates Pos) {
        InitializeChessBoard(chess1);//Initialize The Chess Board With Black and white Blocks
        //   if(!check1 && !check2){
        //     KingGrid[r][col] = 1;
        // }
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
        kingGr = KingGrid.clone();
        boolean p1 = false;
        King king = new King();
        rechess1 = chess1.clone();
        if (Pos_Row == 1 && player == 1 && grid[Pos_Row + 1][Pos_Col] == 0 && grid[Pos_Row + 2][Pos_Col] == 0) {
            if (go && !check3) {
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (ICons_Arr[ii][j] != null) {
                            kingcon[ii][j] = ICons_Arr[ii][j].toString();
                        }
                    }
                }
                int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                Icon icon_res = chess1[Pos_Row][Pos_Col];
                int num1 = grid[Pos_Row + 2][Pos_Col], renum1 = board[Pos_Row + 2][Pos_Col];
                Icon icon_res1 = chess1[Pos_Row + 2][Pos_Col];
                grid[Pos_Row][Pos_Col] = 0;
                grid[Pos_Row + 2][Pos_Col] = num;
                check3 = true;
                if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                    check3 = false;
                    Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row + 2][Pos_Col] = num1;
                    board[Pos_Row + 2][Pos_Col] = renum1;
                    chess1[Pos_Row + 2][Pos_Col] = (icon_res1);
                    p1 = true;
                    chess1 = rechess1.clone();
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                }
                Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                check3 = false;
                grid[Pos_Row][Pos_Col] = num;
                board[Pos_Row][Pos_Col] = renum;
                chess1[Pos_Row][Pos_Col] = (icon_res);
                grid[Pos_Row + 2][Pos_Col] = num1;
                board[Pos_Row + 2][Pos_Col] = renum1;
                chess1[Pos_Row + 2][Pos_Col] = (icon_res1);
            }
            if (!p1) {
                chess1[Pos_Row + 2][Pos_Col] = (GreenIcon);
                if(!check3){
                    rep++;
                }
            }
        }
        p1 = false;
        rechess1 = chess1.clone();
        if (Pos_Row == 6 && player == 2 && grid[Pos_Row - 1][Pos_Col] == 0 && grid[Pos_Row - 2][Pos_Col] == 0) {
            if (go && !check3) {
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (ICons_Arr[ii][j] != null) {
                            kingcon[ii][j] = ICons_Arr[ii][j].toString();
                        }
                    }
                }
                int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                Icon icon_res = chess1[Pos_Row][Pos_Col];
                int num1 = grid[Pos_Row - 2][Pos_Col], renum1 = board[Pos_Row - 2][Pos_Col];
                Icon icon_res1 = chess1[Pos_Row - 2][Pos_Col];
                grid[Pos_Row][Pos_Col] = 0;
                grid[Pos_Row - 2][Pos_Col] = num;
                check3 = true;
                if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                    check3 = false;
                    Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row - 2][Pos_Col] = num1;
                    board[Pos_Row - 2][Pos_Col] = renum1;
                    chess1[Pos_Row - 2][Pos_Col] = (icon_res1);
                    p1 = true;
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                    chess1 = rechess1.clone();
                }
                Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                check3 = false;
                grid[Pos_Row][Pos_Col] = num;
                board[Pos_Row][Pos_Col] = renum;
                chess1[Pos_Row][Pos_Col] = (icon_res);
                grid[Pos_Row - 2][Pos_Col] = num1;
                board[Pos_Row - 2][Pos_Col] = renum1;
                chess1[Pos_Row - 2][Pos_Col] = (icon_res1);
            }
            if (!p1) {
                chess1[Pos_Row - 2][Pos_Col] = (GreenIcon);
                if(!check3){
                    rep++;
                }
            }
        }
        p1 = false;
        rechess1 = chess1.clone();
        if (Pos_Row + di >= 0 && Pos_Row + di < 8 && grid[Pos_Row + di][Pos_Col] == 0) {
            if (go && !check3) {
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (ICons_Arr[ii][j] != null) {
                            kingcon[ii][j] = ICons_Arr[ii][j].toString();
                        }
                    }
                }
                int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                Icon icon_res = chess1[Pos_Row][Pos_Col];
                int num1 = grid[Pos_Row + di][Pos_Col], renum1 = board[Pos_Row + di][Pos_Col];
                Icon icon_res1 = chess1[Pos_Row + di][Pos_Col];
                grid[Pos_Row][Pos_Col] = 0;
                grid[Pos_Row + di][Pos_Col] = num;
                check3 = true;
                if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                    check3 = false;Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row + di][Pos_Col] = num1;
                    board[Pos_Row + di][Pos_Col] = renum1;
                    chess1[Pos_Row + di][Pos_Col] = (icon_res1);
                    p1 = true;
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                    chess1 = rechess1.clone();
                }
                Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                check3 = false;
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                grid[Pos_Row][Pos_Col] = num;
                board[Pos_Row][Pos_Col] = renum;
                chess1[Pos_Row][Pos_Col] = (icon_res);
                grid[Pos_Row + di][Pos_Col] = num1;
                board[Pos_Row + di][Pos_Col] = renum1;
                chess1[Pos_Row + di][Pos_Col] = (icon_res1);
            }
            if (!p1) {
                chess1[Pos_Row + di][Pos_Col] = (GreenIcon);
                if(!check3){
                    rep++;
                }
            }
        }
        p1 = false;
        rechess1 = chess1.clone();
        if (Pos_Row + di >= 0 && Pos_Row + di < 8 && Pos_Col - 1 >= 0 &&
                ((board[Pos_Row + di][Pos_Col - 1] != 5 && board[Pos_Row + di][Pos_Col - 1] != 55) || ((OpponentPiece == 1 && board[Pos_Row + di][Pos_Col - 1] == 5) || (OpponentPiece == 2 && board[Pos_Row + di][Pos_Col - 1] == 55)))
                && (grid[Pos_Row + di][Pos_Col - 1] == OpponentPiece)) {
            if (go && !check3) {
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (ICons_Arr[ii][j] != null) {
                            kingcon[ii][j] = ICons_Arr[ii][j].toString();
                        }
                    }
                }
                int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                Icon icon_res = chess1[Pos_Row][Pos_Col];
                int num1 = grid[Pos_Row + di][Pos_Col - 1], renum1 = board[Pos_Row + di][Pos_Col - 1];
                Icon icon_res1 = chess1[Pos_Row + di][Pos_Col - 1];
                grid[Pos_Row][Pos_Col] = 0;
                grid[Pos_Row + di][Pos_Col - 1] = num;
                check3 = true;
                if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                    check3 = false;Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row + di][Pos_Col - 1] = num1;
                    board[Pos_Row + di][Pos_Col - 1] = renum1;
                    chess1[Pos_Row + di][Pos_Col - 1] = (icon_res1);
                    p1 = true;
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                    chess1 = rechess1.clone();
                }
                Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                check3 = false;
                grid[Pos_Row][Pos_Col] = num;
                board[Pos_Row][Pos_Col] = renum;
                chess1[Pos_Row][Pos_Col] = (icon_res);
                grid[Pos_Row + di][Pos_Col - 1] = num1;
                board[Pos_Row + di][Pos_Col - 1] = renum1;
                chess1[Pos_Row + di][Pos_Col - 1] = (icon_res1);
            }
            if (!p1) {
                Pawn_Prv_Row_R = Pos_Row;
                Pawn_Prv_Col_R = Pos_Col;
                right = chess1[Pos_Row + di][Pos_Col - 1];
                chess1[Pos_Row + di][Pos_Col - 1] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row + di][Pos_Col - 1] = 1;
                }
                else{
                    rep++;
                }
            }
        }
        p1 = false;
        rechess1 = chess1.clone();
        if (Pos_Row + di >= 0 && Pos_Row + di < 8 && Pos_Col + 1 < 8 && 
                ((board[Pos_Row + di][Pos_Col + 1] != 5 && board[Pos_Row + di][Pos_Col + 1] != 55) || ((OpponentPiece == 1 && board[Pos_Row + di][Pos_Col + 1] == 5) || (OpponentPiece == 2 && board[Pos_Row + di][Pos_Col + 1] == 55)))
                && (grid[Pos_Row + di][Pos_Col + 1] == OpponentPiece)) {
            if (go && !check3) {
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (ICons_Arr[ii][j] != null) {
                            kingcon[ii][j] = ICons_Arr[ii][j].toString();
                        }
                    }
                }
                int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col], pre1 = Pawn_Prv_Row_R, pre2 = Pawn_Prv_Col_R;
                Icon icon_res = chess1[Pos_Row][Pos_Col], res2 = right;
                int num1 = grid[Pos_Row + di][Pos_Col + 1], renum1 = board[Pos_Row + di][Pos_Col + 1];
                Icon icon_res1 = chess1[Pos_Row + di][Pos_Col + 1];
                grid[Pos_Row][Pos_Col] = 0;
                grid[Pos_Row + di][Pos_Col + 1] = num;
                check3 = true;
                if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                    check3 = false;Pos_Row = Pos.Row;Pos_Col = Pos.Col;right = res2;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row + di][Pos_Col + 1] = num1;
                    board[Pos_Row + di][Pos_Col + 1] = renum1;
                    chess1[Pos_Row + di][Pos_Col + 1] = (icon_res1);
                    Pawn_Prv_Row_R = pre1; Pawn_Prv_Col_R = pre2;
                    p1 = true;
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                    chess1 = rechess1.clone();
                }
                Pos_Row = Pos.Row;Pos_Col = Pos.Col;
                Pawn_Prv_Row_R = pre1; Pawn_Prv_Col_R = pre2;right = res2;
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                check3 = false;
                grid[Pos_Row][Pos_Col] = num;
                board[Pos_Row][Pos_Col] = renum;
                chess1[Pos_Row][Pos_Col] = (icon_res);
                grid[Pos_Row + di][Pos_Col + 1] = num1;
                board[Pos_Row + di][Pos_Col + 1] = renum1;
                chess1[Pos_Row + di][Pos_Col + 1] = (icon_res1);
            }
            if (!p1) {
                Pawn_Prv_Row_L = Pos_Row;
                Pawn_Prv_Col_L = Pos_Col;
                left = chess1[Pos_Row + di][Pos_Col + 1];
                chess1[Pos_Row + di][Pos_Col + 1] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row + di][Pos_Col + 1] = 1;
                }
                else{
                    rep++;
                }
            }
        }
        KingGrid = kingGr.clone();
        setChess(chess1);
    }

    public void Resend() {
        kingGr = KingGrid.clone();
        SetGreen();
        if (Pawn_Prv_Row_R == -1 && Pawn_Prv_Row_L == -1) {
            return;
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int j = 0; j < 8; j++) {
                if (ICons_Arr[ii][j] != null) {
                    kingcon[ii][j] = ICons_Arr[ii][j].toString();
                }
            }
        }
        rechess1 = chess1.clone();
        if (Pawn_Prv_Row_R != -1 && Pawn_Prv_Col_R != -1 && board[Pawn_Prv_Row_R + 1][Pawn_Prv_Col_R - 1] != 5 && board[Pawn_Prv_Row_R + 1][Pawn_Prv_Col_R - 1] != 55 && board[Pawn_Prv_Row_R][Pawn_Prv_Col_R] == 6) {
            chess1[Pawn_Prv_Row_R + 1][Pawn_Prv_Col_R - 1] = (right);
        }
        if (Pawn_Prv_Row_L != -1 && Pawn_Prv_Col_L != -1 && board[Pawn_Prv_Row_L + 1][Pawn_Prv_Col_L + 1] != 5 && board[Pawn_Prv_Row_L + 1][Pawn_Prv_Col_L + 1] != 55 && board[Pawn_Prv_Row_L][Pawn_Prv_Col_L] == 6) {
            chess1[Pawn_Prv_Row_L + 1][Pawn_Prv_Col_L + 1] = (left);
        }
        if (Pawn_Prv_Row_R != -1 && Pawn_Prv_Col_R != -1 && board[Pawn_Prv_Row_R - 1][Pawn_Prv_Col_R - 1] != 5 && board[Pawn_Prv_Row_R - 1][Pawn_Prv_Col_R - 1] != 55 && board[Pawn_Prv_Row_R][Pawn_Prv_Col_R] == 66) {
            chess1[Pawn_Prv_Row_R - 1][Pawn_Prv_Col_R - 1] = (right);
        }
        if (Pawn_Prv_Row_L != -1 && Pawn_Prv_Col_L != -1 && board[Pawn_Prv_Row_L - 1][Pawn_Prv_Col_L + 1] != 5 && board[Pawn_Prv_Row_L - 1][Pawn_Prv_Col_L + 1] != 55 && board[Pawn_Prv_Row_L][Pawn_Prv_Col_L] == 66) {
            chess1[Pawn_Prv_Row_L - 1][Pawn_Prv_Col_L + 1] = (left);
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int j = 0; j < 8; j++) {
                if (kingcon[ii][j] != null) {
                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                }
            }
        }
        KingGrid = kingGr.clone();
        chess1 = rechess1.clone();
        Pawn_Prv_Col_R = Pawn_Prv_Col_L = Pawn_Prv_Row_R = Pawn_Prv_Row_L = -1;
        setChess(chess1);
    }

}
