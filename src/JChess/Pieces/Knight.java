package JChess.Pieces;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import static test.Table.*;

public class Knight extends piece {

    final static int dirx[] = {2, 2, -2, -2, 1, 1, -1, -1};
    final static int diry[] = {1, -1, -1, 1, 2, -2, -2, 2};

    public Knight() {
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

    public Knight(int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
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
        //if(!check1 && !check2){
        // KingGrid[row][col] = 1;
        //}  
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
    
        kingGr = KingGrid.clone();
        King king = new King();
        Knight_Coordinates_Row = Pos_Row;
        Knight_Coordinates_Col = Pos_Col;
        for (int i = 0; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            int x = Pos_Row + dirx[i];
            int y = Pos_Col + diry[i];
            if (!Valid_Move(x, y)) {
                continue;
            }
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowknight = Knight_Coordinates_Row, colknight = Knight_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Knight_Coordinates_Row = rowknight;
                        Knight_Coordinates_Col = colknight;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
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
                    Knight_Coordinates_Row = rowknight;
                    Knight_Coordinates_Col = colknight;
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
                    int rowknight = Knight_Coordinates_Row, colknight = Knight_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Knight_Coordinates_Row = rowknight;
                        Knight_Coordinates_Col = colknight;
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
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
                    Knight_Coordinates_Row = rowknight;
                    Knight_Coordinates_Col = colknight;
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

            } else if (((OpponentPiece == 2 && board[x][y] == 55) || (OpponentPiece == 1 && board[x][y] == 5)) || board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == (OpponentPiece == 2 ? 1 : 2)) {
                if (check3) {
                    KingGrid[x][y] = 1;
                }
            }

        }
        KingGrid = kingGr.clone();
        setChess(chess1);
    }

    public void Resend() {
        if (Knight_Coordinates_Row == -1 || Knight_Coordinates_Col == -1) {
            return;
        }
        kingGr = KingGrid.clone();
        King king = new King();
        for (int i = 0; i < 8; ++i) {
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            rechess1 = chess1.clone();
            int x = Knight_Coordinates_Row + dirx[i];
            int y = Knight_Coordinates_Col + diry[i];
            if (!Valid_Move(x, y)) {
                continue;
            }
            if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == 0) {
                if (go && !check3) {
                    int num = grid[Knight_Coordinates_Row][Knight_Coordinates_Col], renum = board[Knight_Coordinates_Row][Knight_Coordinates_Col];
                    Icon icon_res = chess1[Knight_Coordinates_Row][Knight_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowknight = Knight_Coordinates_Row, colknight = Knight_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Knight_Coordinates_Row = rowknight;
                        Knight_Coordinates_Col = colknight;
                        check3 = false;
                        chess1 = rechess1.clone();
                        grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = num;
                        board[Knight_Coordinates_Row][Knight_Coordinates_Col] = renum;
                        chess1[Knight_Coordinates_Row][Knight_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        continue;
                    }
                    Knight_Coordinates_Row = rowknight;
                    Knight_Coordinates_Col = colknight;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = num;
                    board[Knight_Coordinates_Row][Knight_Coordinates_Col] = renum;
                    chess1[Knight_Coordinates_Row][Knight_Coordinates_Col] = (icon_res);
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

            } else if (board[x][y] != 5 && board[x][y] != 55 && grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Knight_Coordinates_Row][Knight_Coordinates_Col], renum = board[Knight_Coordinates_Row][Knight_Coordinates_Col];
                    Icon icon_res = chess1[Knight_Coordinates_Row][Knight_Coordinates_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = 0;
                    grid[x][y] = num;
                    check3 = true;
                    int rowknight = Knight_Coordinates_Row, colknight = Knight_Coordinates_Col;
                    if (king.isAttacked(rowWKing, colWKing, (OpponentPiece))) {
                        Knight_Coordinates_Row = rowknight;
                        Knight_Coordinates_Col = colknight;
                        check3 = false;
                        chess1 = rechess1.clone();
                        grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = num;
                        board[Knight_Coordinates_Row][Knight_Coordinates_Col] = renum;
                        chess1[Knight_Coordinates_Row][Knight_Coordinates_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        for (int ii = 0; ii < 8; ii++) {
                            for (int j = 0; j < 8; j++) {
                                if (kingcon[ii][j] != null) {
                                    ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                                }
                            }
                        }
                        continue;
                    }
                    for (int ii = 0; ii < 8; ii++) {
                        for (int j = 0; j < 8; j++) {
                            if (kingcon[ii][j] != null) {
                                ICons_Arr[ii][j] = new ImageIcon(kingcon[ii][j]);
                            }
                        }
                    }
                    Knight_Coordinates_Row = rowknight;
                    Knight_Coordinates_Col = colknight;
                    check3 = false;
                    chess1 = rechess1.clone();
                    grid[Knight_Coordinates_Row][Knight_Coordinates_Col] = num;
                    board[Knight_Coordinates_Row][Knight_Coordinates_Col] = renum;
                    chess1[Knight_Coordinates_Row][Knight_Coordinates_Col] = (icon_res);
                    grid[x][y] = num1;
                    board[x][y] = renum1;
                    chess1[x][y] = (icon_res1);
                }
                chess1[x][y] = (ICons_Arr[x][y]);
            }

        }
        KingGrid = kingGr.clone();
        Knight_Coordinates_Row = Knight_Coordinates_Col = -1;
        SetGreen();
        setChess(chess1);
    }

}
