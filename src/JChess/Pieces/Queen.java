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
 * @author Escanor
 */
public class Queen extends piece {

    private static final int Dirx[] = {1, 1, -1, -1};// for The moves of The Bishob
    private static final int Diry[] = {1, -1, -1, 1};

    public Queen() {
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

    public Queen(int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
        this.OpponentPiece = OpponentPiece;
        this.image = image;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
    }

    private boolean Valid_Move(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @Override
    public void Move(Icon white, Icon black, Icon GreenIcon, Coordinates Pos) {
        InitializeChessBoard(chess1);
        int np = (OpponentPiece == 2 ? 1 : 2);
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
        Queen_Coordinates_Row = Pos_Row;
        Queen_Coordinates_Col = Pos_Col;
        //  if(!check1 && !check2){
        //    KingGrid[row][col] = 1;
        // }
        kingGr = KingGrid.clone();
        King king = new King();
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
            } else if (((OpponentPiece == 1 && board[x][y] == 5) || (OpponentPiece == 2 && board[x][y] == 55)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
                break;
            } else if (OpponentPiece == grid[x][y] || np == grid[x][y]) {
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
            rechess1 = chess1;
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
            } else if (OpponentPiece == grid[x][y] || np == grid[x][y]) {
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
            } else if (OpponentPiece == grid[x][y] || np == grid[x][y]) {
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
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
            } else if (OpponentPiece == grid[x][y] || np == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Pos_Row;
            y = Shift * Diry[dir_indx] + Pos_Col;
        }

        // for + plus Moves
        for (int i = Pos_Row + 1; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[i][Pos_Col], renum1 = board[i][Pos_Col];
                    Icon icon_res1 = chess1[i][Pos_Col];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[i][Pos_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[i][Pos_Col] = num1;
                        board[i][Pos_Col] = renum1;
                        chess1[i][Pos_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[i][Pos_Col] = num1;
                    board[i][Pos_Col] = renum1;
                    chess1[i][Pos_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos_Col] = chess1[i][Pos_Col];
                chess1[i][Pos_Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[i][Pos_Col], renum1 = board[i][Pos_Col];
                    Icon icon_res1 = chess1[i][Pos_Col];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[i][Pos_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[i][Pos_Col] = num1;
                        board[i][Pos_Col] = renum1;
                        chess1[i][Pos_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[i][Pos_Col] = num1;
                    board[i][Pos_Col] = renum1;
                    chess1[i][Pos_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos_Col] = chess1[i][Pos_Col];
                chess1[i][Pos_Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[i][Pos_Col] == 55) || (OpponentPiece == 1 && board[i][Pos_Col] == 5)) || board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                break;
            } else if (OpponentPiece == grid[i][Pos_Col] || np == grid[i][Pos_Col]) {
                break;
            }
        }
        for (int i = Pos_Row - 1; i >= 0; --i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[i][Pos_Col], renum1 = board[i][Pos_Col];
                    Icon icon_res1 = chess1[i][Pos_Col];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[i][Pos_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[i][Pos_Col] = num1;
                        board[i][Pos_Col] = renum1;
                        chess1[i][Pos_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[i][Pos_Col] = num1;
                    board[i][Pos_Col] = renum1;
                    chess1[i][Pos_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos_Col] = chess1[i][Pos_Col];
                chess1[i][Pos_Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[i][Pos_Col], renum1 = board[i][Pos_Col];
                    Icon icon_res1 = chess1[i][Pos_Col];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[i][Pos_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[i][Pos_Col] = num1;
                        board[i][Pos_Col] = renum1;
                        chess1[i][Pos_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[i][Pos_Col] = num1;
                    board[i][Pos_Col] = renum1;
                    chess1[i][Pos_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[i][Pos_Col] = chess1[i][Pos_Col];
                chess1[i][Pos_Col] = (GreenIcon);
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[i][Pos_Col] == 55) || (OpponentPiece == 1 && board[i][Pos_Col] == 5)) || board[i][Pos_Col] != 5 && board[i][Pos_Col] != 55 && grid[i][Pos_Col] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[i][Pos_Col] = 1;
                }
                break;
            } else if (OpponentPiece == grid[i][Pos_Col] || np == grid[i][Pos_Col]) {
                break;
            }
        }
        for (int i = Pos_Col + 1; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[Pos_Row][i], renum1 = board[Pos_Row][i];
                    Icon icon_res1 = chess1[Pos_Row][i];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[Pos_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        check3 = false;
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[Pos_Row][i] = num1;
                        board[Pos_Row][i] = renum1;
                        chess1[Pos_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row][i] = num1;
                    board[Pos_Row][i] = renum1;
                    chess1[Pos_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos_Row][i] = chess1[Pos_Row][i];
                chess1[Pos_Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[Pos_Row][i], renum1 = board[Pos_Row][i];
                    Icon icon_res1 = chess1[Pos_Row][i];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[Pos_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        check3 = false;
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[Pos_Row][i] = num1;
                        board[Pos_Row][i] = renum1;
                        chess1[Pos_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row][i] = num1;
                    board[Pos_Row][i] = renum1;
                    chess1[Pos_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos_Row][i] = chess1[Pos_Row][i];
                chess1[Pos_Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[Pos_Row][i] == 55) || (OpponentPiece == 1 && board[Pos_Row][i] == 5)) || board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                break;
            } else if (OpponentPiece == grid[Pos_Row][i] || np == grid[Pos_Row][i]) {
                break;
            }
        }

        for (int i = Pos_Col - 1; i >= 0; --i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[Pos_Row][i], renum1 = board[Pos_Row][i];
                    Icon icon_res1 = chess1[Pos_Row][i];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[Pos_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        check3 = false;
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[Pos_Row][i] = num1;
                        board[Pos_Row][i] = renum1;
                        chess1[Pos_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row][i] = num1;
                    board[Pos_Row][i] = renum1;
                    chess1[Pos_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos_Row][i] = chess1[Pos_Row][i];
                chess1[Pos_Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                else{
                    rep++;
                }
            } else if (board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[Pos_Row][i], renum1 = board[Pos_Row][i];
                    Icon icon_res1 = chess1[Pos_Row][i];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[Pos_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        check3 = false;
                        Queen_Coordinates_Row = rowquene;
                        Queen_Coordinates_Col = colquene;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[Pos_Row][i] = num1;
                        board[Pos_Row][i] = renum1;
                        chess1[Pos_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    grid[Pos_Row][Pos_Col] = num;
                    board[Pos_Row][Pos_Col] = renum;
                    chess1[Pos_Row][Pos_Col] = (icon_res);
                    grid[Pos_Row][i] = num1;
                    board[Pos_Row][i] = renum1;
                    chess1[Pos_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                ICons_Arr[Pos_Row][i] = chess1[Pos_Row][i];
                chess1[Pos_Row][i] = (GreenIcon);
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                else{
                    rep++;
                }
                break;
            } else if (((OpponentPiece == 2 && board[Pos_Row][i] == 55) || (OpponentPiece == 1 && board[Pos_Row][i] == 5)) || board[Pos_Row][i] != 5 && board[Pos_Row][i] != 55 && grid[Pos_Row][i] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[Pos_Row][i] = 1;
                }
                break;

            } else if (OpponentPiece == grid[Pos_Row][i] || np == grid[Pos_Row][i]) {
                break;
            }
        }
        KingGrid = kingGr;
        setChess(chess1);

    }

    public void Resend() {
        if (Queen_Coordinates_Row == -1 || Queen_Coordinates_Col == -1) {
            return;
        }
        kingGr = KingGrid;
        int rplayer = OpponentPiece;
        int np = (OpponentPiece == 2 ? 1 : 2);
        int Shift = 1, dir_indx = 0;
        int x, y;
        King king = new King();
        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
            x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 1;
        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
            x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 2;
        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
            x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
        }
        Shift = 1;
        dir_indx = 3;
        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        Shift++;
                        x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
                        y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
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
                chess1 = rechess1.clone();
                chess1[x][y] = (ICons_Arr[x][y]);
                break;
            } else if (np == grid[x][y] || OpponentPiece == grid[x][y]) {
                break;
            }
            Shift++;
            x = Shift * Dirx[dir_indx] + Queen_Coordinates_Row;
            y = Shift * Diry[dir_indx] + Queen_Coordinates_Col;
        }

        // for + plus Moves
        for (int i = Queen_Coordinates_Row + 1; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Queen_Coordinates_Col] != 5 && board[i][Queen_Coordinates_Col] != 55 && grid[i][Queen_Coordinates_Col] == 0) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[i][Queen_Coordinates_Col], renum1 = board[i][Queen_Coordinates_Col];
                    Icon icon_res1 = chess1[i][Queen_Coordinates_Col];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[i][Queen_Coordinates_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[i][Queen_Coordinates_Col] = num1;
                        board[i][Queen_Coordinates_Col] = renum1;
                        chess1[i][Queen_Coordinates_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[i][Queen_Coordinates_Col] = num1;
                    board[i][Queen_Coordinates_Col] = renum1;
                    chess1[i][Queen_Coordinates_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Queen_Coordinates_Col] = (ICons_Arr[i][Queen_Coordinates_Col]);
            } else if (board[i][Queen_Coordinates_Col] != 5 && board[i][Queen_Coordinates_Col] != 55 && grid[i][Queen_Coordinates_Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[i][Queen_Coordinates_Col], renum1 = board[i][Queen_Coordinates_Col];
                    Icon icon_res1 = chess1[i][Queen_Coordinates_Col];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[i][Queen_Coordinates_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[i][Queen_Coordinates_Col] = num1;
                        board[i][Queen_Coordinates_Col] = renum1;
                        chess1[i][Queen_Coordinates_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[i][Queen_Coordinates_Col] = num1;
                    board[i][Queen_Coordinates_Col] = renum1;
                    chess1[i][Queen_Coordinates_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Queen_Coordinates_Col] = (ICons_Arr[i][Queen_Coordinates_Col]);
                break;
            } else if (np == grid[i][Queen_Coordinates_Col] || OpponentPiece == grid[i][Queen_Coordinates_Col]) {
                break;
            }
        }
        for (int i = Queen_Coordinates_Row - 1; i >= 0; --i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[i][Queen_Coordinates_Col] != 5 && board[i][Queen_Coordinates_Col] != 55 && grid[i][Queen_Coordinates_Col] == 0) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[i][Queen_Coordinates_Col], renum1 = board[i][Queen_Coordinates_Col];
                    Icon icon_res1 = chess1[i][Queen_Coordinates_Col];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[i][Queen_Coordinates_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[i][Queen_Coordinates_Col] = num1;
                        board[i][Queen_Coordinates_Col] = renum1;
                        chess1[i][Queen_Coordinates_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[i][Queen_Coordinates_Col] = num1;
                    board[i][Queen_Coordinates_Col] = renum1;
                    chess1[i][Queen_Coordinates_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Queen_Coordinates_Col] = (ICons_Arr[i][Queen_Coordinates_Col]);
            } else if (board[i][Queen_Coordinates_Col] != 5 && board[i][Queen_Coordinates_Col] != 55 && grid[i][Queen_Coordinates_Col] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[i][Queen_Coordinates_Col], renum1 = board[i][Queen_Coordinates_Col];
                    Icon icon_res1 = chess1[i][Queen_Coordinates_Col];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[i][Queen_Coordinates_Col] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[i][Queen_Coordinates_Col] = num1;
                        board[i][Queen_Coordinates_Col] = renum1;
                        chess1[i][Queen_Coordinates_Col] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[i][Queen_Coordinates_Col] = num1;
                    board[i][Queen_Coordinates_Col] = renum1;
                    chess1[i][Queen_Coordinates_Col] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[i][Queen_Coordinates_Col] = (ICons_Arr[i][Queen_Coordinates_Col]);
                break;
            } else if (np == grid[i][Queen_Coordinates_Col] || OpponentPiece == grid[i][Queen_Coordinates_Col]) {
                break;
            }
        }
        for (int i = Queen_Coordinates_Col + 1; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Queen_Coordinates_Row][i] != 5 && board[Queen_Coordinates_Row][i] != 55 && grid[Queen_Coordinates_Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[Queen_Coordinates_Row][i], renum1 = board[Queen_Coordinates_Row][i];
                    Icon icon_res1 = chess1[Queen_Coordinates_Row][i];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[Queen_Coordinates_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[Queen_Coordinates_Row][i] = num1;
                        board[Queen_Coordinates_Row][i] = renum1;
                        chess1[Queen_Coordinates_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[Queen_Coordinates_Row][i] = num1;
                    board[Queen_Coordinates_Row][i] = renum1;
                    chess1[Queen_Coordinates_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Queen_Coordinates_Row][i] = (ICons_Arr[Queen_Coordinates_Row][i]);
            } else if (board[Queen_Coordinates_Row][i] != 5 && board[Queen_Coordinates_Row][i] != 55 && grid[Queen_Coordinates_Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[Queen_Coordinates_Row][i], renum1 = board[Queen_Coordinates_Row][i];
                    Icon icon_res1 = chess1[Queen_Coordinates_Row][i];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[Queen_Coordinates_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[Queen_Coordinates_Row][i] = num1;
                        board[Queen_Coordinates_Row][i] = renum1;
                        chess1[Queen_Coordinates_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[Queen_Coordinates_Row][i] = num1;
                    board[Queen_Coordinates_Row][i] = renum1;
                    chess1[Queen_Coordinates_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Queen_Coordinates_Row][i] = (ICons_Arr[Queen_Coordinates_Row][i]);
                break;
            } else if (np == grid[Queen_Coordinates_Row][i] || OpponentPiece == grid[Queen_Coordinates_Row][i]) {
                break;
            }
        }

        for (int i = Queen_Coordinates_Col - 1; i >= 0; --i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            if (board[Queen_Coordinates_Row][i] != 5 && board[Queen_Coordinates_Row][i] != 55 && grid[Queen_Coordinates_Row][i] == 0) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[Queen_Coordinates_Row][i], renum1 = board[Queen_Coordinates_Row][i];
                    Icon icon_res1 = chess1[Queen_Coordinates_Row][i];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[Queen_Coordinates_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[Queen_Coordinates_Row][i] = num1;
                        board[Queen_Coordinates_Row][i] = renum1;
                        chess1[Queen_Coordinates_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[Queen_Coordinates_Row][i] = num1;
                    board[Queen_Coordinates_Row][i] = renum1;
                    chess1[Queen_Coordinates_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Queen_Coordinates_Row][i] = (ICons_Arr[Queen_Coordinates_Row][i]);
            } else if (board[Queen_Coordinates_Row][i] != 5 && board[Queen_Coordinates_Row][i] != 55 && grid[Queen_Coordinates_Row][i] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Queen_Coordinates_Row][Queen_Coordinates_Col], renum = board[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    Icon icon_res = chess1[Queen_Coordinates_Row][Queen_Coordinates_Col];
                    int num1 = grid[Queen_Coordinates_Row][i], renum1 = board[Queen_Coordinates_Row][i];
                    Icon icon_res1 = chess1[Queen_Coordinates_Row][i];
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = 0;
                    grid[Queen_Coordinates_Row][i] = num;
                    check3 = true;
                    int rowquene = Queen_Coordinates_Row, colquene = Queen_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Queen_Coordinates_Col = colquene;
                        Queen_Coordinates_Row = rowquene;
                        check3 = false;
                        grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                        board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                        chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                        grid[Queen_Coordinates_Row][i] = num1;
                        board[Queen_Coordinates_Row][i] = renum1;
                        chess1[Queen_Coordinates_Row][i] = (icon_res1);
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
                    Queen_Coordinates_Col = colquene;
                    Queen_Coordinates_Row = rowquene;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Queen_Coordinates_Row][Queen_Coordinates_Col] = num;
                    board[Queen_Coordinates_Row][Queen_Coordinates_Col] = renum;
                    chess1[Queen_Coordinates_Row][Queen_Coordinates_Col] = (icon_res);
                    grid[Queen_Coordinates_Row][i] = num1;
                    board[Queen_Coordinates_Row][i] = renum1;
                    chess1[Queen_Coordinates_Row][i] = (icon_res1);
                }
                for (int ii = 0; ii < 8; ii++) {
                    for (int j = 0; j < 8; j++) {
                        if (kingcon[ii][j] != null) {
                            ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                        }
                    }
                }
                chess1[Queen_Coordinates_Row][i] = (ICons_Arr[Queen_Coordinates_Row][i]);
                break;
            } else if (np == grid[Queen_Coordinates_Row][i] || OpponentPiece == grid[Queen_Coordinates_Row][i]) {
                break;

            }

        }
        KingGrid = kingGr.clone();
        Queen_Coordinates_Row = Queen_Coordinates_Col = -1;
        SetGreen();
        setChess(chess1);

    }

}
