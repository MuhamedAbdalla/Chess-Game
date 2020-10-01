/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JChess.Pieces;

import static test.Table.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Abd El-Rahman
 */
public class Bishop extends piece {

    private final int Dirx[] = {1, 1, -1, -1};// for The moves of The Bishob
    private final int Diry[] = {1, -1, -1, 1};

    public Bishop() {
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

    public Bishop(int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
        this.OpponentPiece = OpponentPiece;
        this.image = image;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }

    }

    private boolean Valid_Move(int row, int col) {

        return (row >= 0 && row < 8 && col >= 0 && col < 8);
    }

    public void Move(Icon white, Icon black, Icon GreenIcon, Coordinates Pos) {
        InitializeChessBoard(chess1);
        setChess(chess1);
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
        int np = (OpponentPiece == 2 ? 1 : 2);
        // if(!check1 && !check2){
        //     KingGrid[row][col] = 1;
        // }\
        kingGr = KingGrid.clone();
        King king = new King();
        
        Bishop_Coordinates_Row = Pos_Row;
        Bishop_Coordinates_Col = Pos_Col;
        
        int Shift = 1, dir_indx = 0;
        int x, y;
        x = Shift * Dirx[dir_indx] + Pos_Row;
        y = Shift * Diry[dir_indx] + Pos_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[x][y] == 55) || (OpponentPiece == 1 && board[x][y] == 5)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                break;
            } else if (grid[x][y] == OpponentPiece || np == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Pos_Row;
            y = Shift * Diry[dir_indx] + Pos_Col;
        }
        Shift = 1;
        dir_indx = 1;
        x = Shift * Dirx[dir_indx] + Pos_Row;
        y = Shift * Diry[dir_indx] + Pos_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[x][y] == 55) || (OpponentPiece == 1 && board[x][y] == 5)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                break;
            } else if (grid[x][y] == OpponentPiece || np == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Pos_Row;
            y = Shift * Diry[dir_indx] + Pos_Col;
        }
        Shift = 1;
        dir_indx = 2;
        x = Shift * Dirx[dir_indx] + Pos_Row;
        y = Shift * Diry[dir_indx] + Pos_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[x][y] == 55) || (OpponentPiece == 1 && board[x][y] == 5)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                break;
            } else if (grid[x][y] == OpponentPiece || np == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Pos_Row;
            y = Shift * Diry[dir_indx] + Pos_Col;
        }
        Shift = 1;
        dir_indx = 3;
        x = Shift * Dirx[dir_indx] + Pos_Row;
        y = Shift * Diry[dir_indx] + Pos_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Pos_Row;
                        y = Shift * Diry[dir_indx] + Pos_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[x][y] == 55) || (OpponentPiece == 1 && board[x][y] == 5)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                break;
            } else if (grid[x][y] == OpponentPiece || grid[x][y] == np) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Pos_Row;
            y = Shift * Diry[dir_indx] + Pos_Col;
        }
        KingGrid = kingGr.clone();
        setChess(chess1);
    }

    public void Resend() {
        if (Bishop_Coordinates_Row == -1) {
            return;
        }
        kingGr = KingGrid.clone();
        int rplayer = OpponentPiece;
        int np = (OpponentPiece == 2 ? 1 : 2);
        int Shift = 1, dir_indx = 0;
        int x, y;
        King king = new King();
        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == rplayer) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
                break;
            } else if (np == grid[x][y] || OpponentPiece == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 1;
        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);

            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == rplayer) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
                break;
            } else if (np == grid[x][y] || OpponentPiece == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 2;
        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == rplayer) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
                break;
            } else if (np == grid[x][y] || OpponentPiece == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 3;
        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        while (Valid_Move(x, y)) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);

            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == rplayer) {
                if (go && !check3) {
                    int num = grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col], renum = board[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    Icon icon_res = chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowbishop = Bishop_Coordinates_Row, colbishop = Bishop_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Bishop_Coordinates_Row = rowbishop;
                        Bishop_Coordinates_Col = colbishop;
                        check3 = false;
                        grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                        board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                        chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
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
                    Bishop_Coordinates_Row = rowbishop;
                    Bishop_Coordinates_Col = colbishop;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = num;
                    board[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = renum;
                    chess1[Bishop_Coordinates_Row][Bishop_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[x][y] = (ICons_Arr[x][y]);
                break;
            } else if (np == grid[x][y] || OpponentPiece == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Bishop_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Bishop_Coordinates_Col;
        }
        KingGrid = kingGr.clone();
        Bishop_Coordinates_Row = Bishop_Coordinates_Col = -1;
        setChess(chess1);
        SetGreen();
    }
}
