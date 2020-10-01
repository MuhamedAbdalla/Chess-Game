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
public class Rook extends piece {

    public Rook() {
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

    public Rook(int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
    }

    @Override
    public void Move(Icon white, Icon black, Icon GreenIcon,Coordinates Pos) {
      
        InitializeChessBoard(chess1);
        kingGr = KingGrid.clone();
        King king = new King();
        int np = (OpponentPiece == 2 ? 1 : 2);
        Rook_Coordinates.Col = Pos.Col;
        Rook_Coordinates.Row = Pos.Row;
        for (int i = Pos.Row + 1; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == 0) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[i][Pos.Col], renum1 = board[i][Pos.Col];
                    Icon icon_res1 = chess1[i][Pos.Col];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[i][Pos.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[i][Pos.Col] = num1;
                        board[i][Pos.Col] = renum1;
                        chess1[i][Pos.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[i][Pos.Col] = num1;
                    board[i][Pos.Col] = renum1;
                    chess1[i][Pos.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos.Col] = chess1[i][Pos.Col];
                chess1[i][Pos.Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[i][Pos.Col], renum1 = board[i][Pos.Col];
                    Icon icon_res1 = chess1[i][Pos.Col];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[i][Pos.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[i][Pos.Col] = num1;
                        board[i][Pos.Col] = renum1;
                        chess1[i][Pos.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[i][Pos.Col] = num1;
                    board[i][Pos.Col] = renum1;
                    chess1[i][Pos.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos.Col] = chess1[i][Pos.Col];
                chess1[i][Pos.Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[i][Pos.Col] == 55) 
                    || (OpponentPiece == 1 && board[i][Pos.Col] == 5)) 
                    || board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                break;
            } else if (OpponentPiece == grid[i][Pos.Col] || np == grid[i][Pos.Col]) {
                break;
            }
        }
        for (int i = Pos.Row - 1; i >= 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == 0) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[i][Pos.Col], renum1 = board[i][Pos.Col];
                    Icon icon_res1 = chess1[i][Pos.Col];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[i][Pos.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[i][Pos.Col] = num1;
                        board[i][Pos.Col] = renum1;
                        chess1[i][Pos.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[i][Pos.Col] = num1;
                    board[i][Pos.Col] = renum1;
                    chess1[i][Pos.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos.Col] = chess1[i][Pos.Col];
                chess1[i][Pos.Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[i][Pos.Col], renum1 = board[i][Pos.Col];
                    Icon icon_res1 = chess1[i][Pos.Col];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[i][Pos.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[i][Pos.Col] = num1;
                        board[i][Pos.Col] = renum1;
                        chess1[i][Pos.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[i][Pos.Col] = num1;
                    board[i][Pos.Col] = renum1;
                    chess1[i][Pos.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos.Col] = chess1[i][Pos.Col];
                chess1[i][Pos.Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[i][Pos.Col] == 55) 
                    || (OpponentPiece == 1 && board[i][Pos.Col] == 5)) 
                    || board[i][Pos.Col] != 5 && board[i][Pos.Col] != 55 && grid[i][Pos.Col] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[i][Pos.Col] = 1;
                }
                break;
            } else if (OpponentPiece == grid[i][Pos.Col] || np == grid[i][Pos.Col]) {
                break;
            }
        }
        for (int i = Pos.Col + 1; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[Pos.Row][i], renum1 = board[Pos.Row][i];
                    Icon icon_res1 = chess1[Pos.Row][i];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[Pos.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[Pos.Row][i] = num1;
                        board[Pos.Row][i] = renum1;
                        chess1[Pos.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[Pos.Row][i] = num1;
                    board[Pos.Row][i] = renum1;
                    chess1[Pos.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos.Row][i] = chess1[Pos.Row][i];
                chess1[Pos.Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos.Row][i] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[Pos.Row][i], renum1 = board[Pos.Row][i];
                    Icon icon_res1 = chess1[Pos.Row][i];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[Pos.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[Pos.Row][i] = num1;
                        board[Pos.Row][i] = renum1;
                        chess1[Pos.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[Pos.Row][i] = num1;
                    board[Pos.Row][i] = renum1;
                    chess1[Pos.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos.Row][i] = chess1[Pos.Row][i];
                chess1[Pos.Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos.Row][i] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[Pos.Row][i] == 55) 
                    || (OpponentPiece == 1 && board[Pos.Row][i] == 5)) 
                    || board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == (OpponentPiece == 2 ? 1 : 2)) {
                
                if (check3) {
                    KingGrid[Pos.Row][i] = 1;
                }
                break;
            } else if (OpponentPiece == grid[Pos.Row][i] || np == grid[Pos.Row][i]) {
                break;
            }
        }
        for (int i = Pos.Col - 1; i >= 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[Pos.Row][i], renum1 = board[Pos.Row][i];
                    Icon icon_res1 = chess1[Pos.Row][i];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[Pos.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[Pos.Row][i] = num1;
                        board[Pos.Row][i] = renum1;
                        chess1[Pos.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[Pos.Row][i] = num1;
                    board[Pos.Row][i] = renum1;
                    chess1[Pos.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos.Row][i] = chess1[Pos.Row][i];
                chess1[Pos.Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos.Row][i] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos.Row][Pos.Col], renum = board[Pos.Row][Pos.Col];
                    Icon icon_res = chess1[Pos.Row][Pos.Col];
                    int num1 = grid[Pos.Row][i], renum1 = board[Pos.Row][i];
                    Icon icon_res1 = chess1[Pos.Row][i];
                    grid[Pos.Row][Pos.Col] = 0;
                    grid[Pos.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Pos.Row][Pos.Col] = num;
                        board[Pos.Row][Pos.Col] = renum;
                        chess1[Pos.Row][Pos.Col] = (icon_res);
                        grid[Pos.Row][i] = num1;
                        board[Pos.Row][i] = renum1;
                        chess1[Pos.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Pos.Row][Pos.Col] = num;
                    board[Pos.Row][Pos.Col] = renum;
                    chess1[Pos.Row][Pos.Col] = (icon_res);
                    grid[Pos.Row][i] = num1;
                    board[Pos.Row][i] = renum1;
                    chess1[Pos.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos.Row][i] = chess1[Pos.Row][i];
                chess1[Pos.Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos.Row][i] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[Pos.Row][i] == 55) 
                    || (OpponentPiece == 1 && board[Pos.Row][i] == 5)) 
                    || board[Pos.Row][i] != 5 && board[Pos.Row][i] != 55 && grid[Pos.Row][i] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    
                    KingGrid[Pos.Row][i] = 1;
                }
                break;
            } else if (OpponentPiece == grid[Pos.Row][i] || np == grid[Pos.Row][i]) {
                break;
            }
        }
        KingGrid = kingGr;
        setChess(chess1);
    }

    public void resend() {
        if (Rook_Coordinates.Row == -1) {
            return;
        }
        kingGr = KingGrid.clone();
        King king = new King();
        int rplayer = OpponentPiece;
        int np = (OpponentPiece == 2 ? 1 : 2);
        for (int i = Rook_Coordinates.Row + 1; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Rook_Coordinates.Col] != 5 && board[i][Rook_Coordinates.Col] != 55 && grid[i][Rook_Coordinates.Col] == 0) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[i][Rook_Coordinates.Col], renum1 = board[i][Rook_Coordinates.Col];
                    Icon icon_res1 = chess1[i][Rook_Coordinates.Col];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[i][Rook_Coordinates.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[i][Rook_Coordinates.Col] = num1;
                        board[i][Rook_Coordinates.Col] = renum1;
                        chess1[i][Rook_Coordinates.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[i][Rook_Coordinates.Col] = num1;
                    board[i][Rook_Coordinates.Col] = renum1;
                    chess1[i][Rook_Coordinates.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Rook_Coordinates.Col] = (ICons_Arr[i][Rook_Coordinates.Col]);
            } else if (board[i][Rook_Coordinates.Col] != 5 && board[i][Rook_Coordinates.Col] != 55 && grid[i][Rook_Coordinates.Col] == rplayer) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[i][Rook_Coordinates.Col], renum1 = board[i][Rook_Coordinates.Col];
                    Icon icon_res1 = chess1[i][Rook_Coordinates.Col];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[i][Rook_Coordinates.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[i][Rook_Coordinates.Col] = num1;
                        board[i][Rook_Coordinates.Col] = renum1;
                        chess1[i][Rook_Coordinates.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[i][Rook_Coordinates.Col] = num1;
                    board[i][Rook_Coordinates.Col] = renum1;
                    chess1[i][Rook_Coordinates.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Rook_Coordinates.Col] = (ICons_Arr[i][Rook_Coordinates.Col]);
                break;
            } else if (np == grid[i][Rook_Coordinates.Col] || grid[i][Rook_Coordinates.Col] == OpponentPiece) {
                break;
            }
        }
        for (int i = Rook_Coordinates.Row - 1; i >= 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Rook_Coordinates.Col] != 5 && board[i][Rook_Coordinates.Col] != 55 && grid[i][Rook_Coordinates.Col] == 0) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[i][Rook_Coordinates.Col], renum1 = board[i][Rook_Coordinates.Col];
                    Icon icon_res1 = chess1[i][Rook_Coordinates.Col];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[i][Rook_Coordinates.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[i][Rook_Coordinates.Col] = num1;
                        board[i][Rook_Coordinates.Col] = renum1;
                        chess1[i][Rook_Coordinates.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[i][Rook_Coordinates.Col] = num1;
                    board[i][Rook_Coordinates.Col] = renum1;
                    chess1[i][Rook_Coordinates.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Rook_Coordinates.Col] = (ICons_Arr[i][Rook_Coordinates.Col]);
            } else if (board[i][Rook_Coordinates.Col] != 5 && board[i][Rook_Coordinates.Col] != 55 && grid[i][Rook_Coordinates.Col] == rplayer) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[i][Rook_Coordinates.Col], renum1 = board[i][Rook_Coordinates.Col];
                    Icon icon_res1 = chess1[i][Rook_Coordinates.Col];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[i][Rook_Coordinates.Col] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[i][Rook_Coordinates.Col] = num1;
                        board[i][Rook_Coordinates.Col] = renum1;
                        chess1[i][Rook_Coordinates.Col] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[i][Rook_Coordinates.Col] = num1;
                    board[i][Rook_Coordinates.Col] = renum1;
                    chess1[i][Rook_Coordinates.Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Rook_Coordinates.Col] = (ICons_Arr[i][Rook_Coordinates.Col]);
                break;
            } else if (np == grid[i][Rook_Coordinates.Col] || grid[i][Rook_Coordinates.Col] == OpponentPiece) {
                break;
            }
        }
        for (int i = Rook_Coordinates.Col + 1; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Rook_Coordinates.Row][i] != 5 && board[Rook_Coordinates.Row][i] != 55 && grid[Rook_Coordinates.Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[Rook_Coordinates.Row][i], renum1 = board[Rook_Coordinates.Row][i];
                    Icon icon_res1 = chess1[Rook_Coordinates.Row][i];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[Rook_Coordinates.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[Rook_Coordinates.Row][i] = num1;
                        board[Rook_Coordinates.Row][i] = renum1;
                        chess1[Rook_Coordinates.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[Rook_Coordinates.Row][i] = num1;
                    board[Rook_Coordinates.Row][i] = renum1;
                    chess1[Rook_Coordinates.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Rook_Coordinates.Row][i] = (ICons_Arr[Rook_Coordinates.Row][i]);
            } else if (board[Rook_Coordinates.Row][i] != 5 && board[Rook_Coordinates.Row][i] != 55 && grid[Rook_Coordinates.Row][i] == rplayer) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[Rook_Coordinates.Row][i], renum1 = board[Rook_Coordinates.Row][i];
                    Icon icon_res1 = chess1[Rook_Coordinates.Row][i];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[Rook_Coordinates.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[Rook_Coordinates.Row][i] = num1;
                        board[Rook_Coordinates.Row][i] = renum1;
                        chess1[Rook_Coordinates.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[Rook_Coordinates.Row][i] = num1;
                    board[Rook_Coordinates.Row][i] = renum1;
                    chess1[Rook_Coordinates.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Rook_Coordinates.Row][i] = (ICons_Arr[Rook_Coordinates.Row][i]);
                break;
            } else if (np == grid[Rook_Coordinates.Row][i] || grid[Rook_Coordinates.Row][i] == OpponentPiece) {
                break;
            }
        }
        for (int i = Rook_Coordinates.Col - 1; i >= 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Rook_Coordinates.Row][i] != 5 && board[Rook_Coordinates.Row][i] != 55 && grid[Rook_Coordinates.Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[Rook_Coordinates.Row][i], renum1 = board[Rook_Coordinates.Row][i];
                    Icon icon_res1 = chess1[Rook_Coordinates.Row][i];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[Rook_Coordinates.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[Rook_Coordinates.Row][i] = num1;
                        board[Rook_Coordinates.Row][i] = renum1;
                        chess1[Rook_Coordinates.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[Rook_Coordinates.Row][i] = num1;
                    board[Rook_Coordinates.Row][i] = renum1;
                    chess1[Rook_Coordinates.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Rook_Coordinates.Row][i] = (ICons_Arr[Rook_Coordinates.Row][i]);
            } else if (board[Rook_Coordinates.Row][i] != 5 && board[Rook_Coordinates.Row][i] != 55 && grid[Rook_Coordinates.Row][i] == rplayer) {
                if (go && !check3) {
                    int num = grid[Rook_Coordinates.Row][Rook_Coordinates.Col], renum = board[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    Icon icon_res = chess1[Rook_Coordinates.Row][Rook_Coordinates.Col];
                    int num1 = grid[Rook_Coordinates.Row][i], renum1 = board[Rook_Coordinates.Row][i];
                    Icon icon_res1 = chess1[Rook_Coordinates.Row][i];
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = 0;
                    grid[Rook_Coordinates.Row][i] = num;
                    check3 = true;
                    int rowrook = Rook_Coordinates.Row, colrook = Rook_Coordinates.Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Rook_Coordinates.Row = rowrook;
                        Rook_Coordinates.Col = colrook;
                        check3 = false;
                        grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                        board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                        chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                        grid[Rook_Coordinates.Row][i] = num1;
                        board[Rook_Coordinates.Row][i] = renum1;
                        chess1[Rook_Coordinates.Row][i] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        chess1 = rechess1.clone();
                        continue;
                    }
                    check3 = false;
                    chess1 = rechess1.clone();
                    Rook_Coordinates.Row = rowrook;
                    Rook_Coordinates.Col = colrook;
                    grid[Rook_Coordinates.Row][Rook_Coordinates.Col] = num;
                    board[Rook_Coordinates.Row][Rook_Coordinates.Col] = renum;
                    chess1[Rook_Coordinates.Row][Rook_Coordinates.Col] = (icon_res);
                    grid[Rook_Coordinates.Row][i] = num1;
                    board[Rook_Coordinates.Row][i] = renum1;
                    chess1[Rook_Coordinates.Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Rook_Coordinates.Row][i] = (ICons_Arr[Rook_Coordinates.Row][i]);
                break;
            } else if (np == grid[Rook_Coordinates.Row][i] || grid[Rook_Coordinates.Row][i] == OpponentPiece) {
                break;
            }
        }
        KingGrid = kingGr.clone();
        Rook_Coordinates.Row = Rook_Coordinates.Col = -1;
        SetGreen();
        setChess(chess1);
    }

}
