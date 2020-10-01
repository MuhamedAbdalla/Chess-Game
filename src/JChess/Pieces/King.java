package JChess.Pieces;

import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static test.Table.*;

public class King extends piece {

    final int dirx[] = {0, 0, 1, -1, 1, 1, -1, -1};
    final int diry[] = {1, -1, 0, 0, 1, -1, -1, 1};

    public King() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rechess1[i][j] = chess[i][j].getIcon();
            }
        }
        OpponentPiece = (player == 1 ? 2 : 1);
    }

    public King(int OpponentPiece, Icon image) {
        super(image, OpponentPiece);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess[i][j].getIcon();
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rechess1[i][j] = chess[i][j].getIcon();
            }
        }
    }

    boolean Valid_Move(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isAttacked(int row, int col, int OpponentPiece) {
        InitializeChessBoard(chess1);
        for (int i = 0; i < 8; ++i) {
            Arrays.fill(KingGrid[i], 0);
        }
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (grid[i][j] == OpponentPiece) {
                    if (board[i][j] == 1 || board[i][j] == 11) {
                        Rook rook = new Rook((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        rook.Move(white, black, GreenIcon,new Coordinates(i,j));
                        rook.resend();
                    } else if (board[i][j] == 2 || board[i][j] == 22) {
                        Knight knight = new Knight((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        knight.Move(white, black, GreenIcon,new Coordinates(i,j));
                        knight.Resend();

                    } else if (board[i][j] == 3 || board[i][j] == 33) {
                        Bishop bishop = new Bishop((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        bishop.Move(white, black, GreenIcon,new Coordinates(i,j));
                        bishop.Resend();
                    } else if ((board[i][j] == 5 || board[i][j] == 55)) {
                        King king = new King((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        king.Move(white, black, GreenIcon,new Coordinates(i,j));
                        king.Resend();
                    } else if (board[i][j] == 4 || board[i][j] == 44) {
                        Queen queen = new Queen((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        queen.Move(white, black, GreenIcon,new Coordinates(i,j));
                        queen.Resend();
                    } else if (board[i][j] == 6 || board[i][j] == 66) {
                        pawn paw = new pawn((OpponentPiece == 2 ? -1 : 1), (OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                        paw.Move(white, black, GreenIcon,new Coordinates(i,j));
                        paw.Resend();
                    }
                }
            }
        }
        return (KingGrid[row][col] == 1 ? true : false);
    }

    public boolean isDead(int row, int col) {
        rep = 0;
        go = true;
        if (row == W_KingRow) {
            int OpponentPiece = 1;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (grid[i][j] == 1) {
                        if (board[i][j] == 1 || board[i][j] == 11) {
                            Rook rook = new Rook((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            rook.Move(white, black, GreenIcon,new Coordinates(i,j));
                            rook.resend();
                        } else if (board[i][j] == 2 || board[i][j] == 22) {
                            Knight knight = new Knight((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            knight.Move(white, black, GreenIcon,new Coordinates(i,j));
                            knight.Resend();

                        } else if (board[i][j] == 3 || board[i][j] == 33) {
                            Bishop bishop = new Bishop((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            bishop.Move(white, black, GreenIcon, new Coordinates(i,j));
                            bishop.Resend();
                        } else if ((board[i][j] == 5 || board[i][j] == 55)) {
                            King king = new King((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            king.Move(white, black, GreenIcon, new Coordinates(i,j));
                            king.Resend();
                        } else if (board[i][j] == 4 || board[i][j] == 44) {
                            Queen queen = new Queen((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            queen.Move(white, black, GreenIcon, new Coordinates(i,j));
                            queen.Resend();
                        } else if (board[i][j] == 6 || board[i][j] == 66) {
                            pawn paw = new pawn((OpponentPiece == 2 ? -1 : 1), (OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            paw.Move(white, black, GreenIcon, new Coordinates(i,j));
                            paw.Resend();
                        }
                    }
                }
            }
        } else {
            int OpponentPiece = 2;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (grid[i][j] == 2) {
                        if (board[i][j] == 1 || board[i][j] == 11) {
                            Rook rook = new Rook((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            rook.Move(white, black, GreenIcon,new Coordinates(i,j));
                            rook.resend();
                        } else if (board[i][j] == 2 || board[i][j] == 22) {
                            Knight knight = new Knight((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            knight.Move(white, black, GreenIcon, new Coordinates(i,j));
                            knight.Resend();

                        } else if (board[i][j] == 3 || board[i][j] == 33) {
                            Bishop bishop = new Bishop((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            bishop.Move(white, black, GreenIcon, new Coordinates(i,j));
                            bishop.Resend();
                        } else if ((board[i][j] == 5 || board[i][j] == 55)) {
                            King king = new King((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            king.Move(white, black, GreenIcon,new Coordinates(i,j));
                            king.Resend();
                        } else if (board[i][j] == 4 || board[i][j] == 44) {
                            Queen queen = new Queen((OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            queen.Move(white, black, GreenIcon,new Coordinates(i,j));
                            queen.Resend();
                        } else if (board[i][j] == 6 || board[i][j] == 66) {
                            pawn paw = new pawn((OpponentPiece == 2 ? -1 : 1), (OpponentPiece == 1 ? 2 : 1), chess[i][j].getIcon());
                            paw.Move(white, black, GreenIcon, new Coordinates(i,j));
                            paw.Resend();
                        }
                    }
                }
            }
        }
        go = false;
        if ((rep > 0 && isattacked) || !isattacked) {
            return false;
        }
        return true;
    }

    public void getkingpos() {
        kingb:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 5) {
                    if (player == 1) {
                        rowWKing = i;
                        colWKing = j;
                    }
                    W_KingRow = i;
                    W_KingCol = j;
                }
                if (board[i][j] == 55) {
                    if (player == 2) {
                        rowWKing = i;
                        colWKing = j;
                    }
                    B_KingRow = i;
                    B_KingCol = j;
                }
            }
        }
    }

    @Override
    public void Move(Icon white, Icon black, Icon GreenIcon,Coordinates Pos) {
        int Pos_Row = Pos.Row;
        int Pos_Col = Pos.Col;
        King_Coordinates_Row = Pos_Row;
        King_Coordinates_Col = Pos_Col;
        if (check3) {
            KingGrid[Pos_Row][Pos_Col] = 1;
        }
        King king = new King(OpponentPiece == 2 ? 2 : 1, chess1[Pos_Row][Pos_Col]);
        for (int i = 0; i < 8; ++i) {
            int x = dirx[i] + Pos_Row;
            int y = diry[i] + Pos_Col;
            for (int ii = 0; ii < 8; ii++) {
                for (int j = 0; j < 8; j++) {
                    if (ICons_Arr[ii][j] != null) {
                        kingcon[ii][j] = ICons_Arr[ii][j].toString();
                    }
                }
            }
            if (!Valid_Move(x, y)) {
                continue;
            }
            if (KingGrid[x][y] == 1 && !check3) {
                continue;
            }
            if (grid[x][y] == 0 || grid[x][y] == OpponentPiece) {
                if (go && !check3) {
                    int num = grid[Pos_Row][Pos_Col], renum = board[Pos_Row][Pos_Col];
                    Icon icon_res = chess1[Pos_Row][Pos_Col];
                    int num1 = grid[x][y], renum1 = board[x][y];
                    Icon icon_res1 = chess1[x][y];
                    grid[Pos_Row][Pos_Col] = 0;
                    board[Pos_Row][Pos_Col] = 0;
                    grid[x][y] = num;
                    board[x][y] = renum;
                    check3 = true;
                    if (king.isAttacked(x, y, (OpponentPiece == 2 ? 2 : 1))) {
                        check3 = false;
                        grid[Pos_Row][Pos_Col] = num;
                        board[Pos_Row][Pos_Col] = renum;
                        chess1[Pos_Row][Pos_Col] = (icon_res);
                        grid[x][y] = num1;
                        board[x][y] = renum1;
                        chess1[x][y] = (icon_res1);
                        King_Coordinates_Row = Pos_Row; King_Coordinates_Col = Pos_Col;
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
                    King_Coordinates_Row = Pos_Row; King_Coordinates_Col = Pos_Col;
                    check3 = false;
                    chess1 = rechess1.clone();
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
                }
                ICons_Arr[x][y] = chess1[x][y];
                chess1[x][y] = (GreenIcon);
                if (check3) {
                    KingGrid[x][y] = 1;
                } else {
                    rep++;
                }

            }
        }
        setChess(chess1);
    }

    public void Resend() {
        if (King_Coordinates_Row == -1 || King_Coordinates_Col == -1) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            int x = dirx[i] + King_Coordinates_Row;
            int y = diry[i] + King_Coordinates_Col;
            if (!Valid_Move(x, y)) {
                continue;
            }
            if (KingGrid[x][y] == 1 && !check3) {
                continue;
            }
            if (grid[x][y] == 0 || grid[x][y] == OpponentPiece) {
                if (ICons_Arr[x][y] != null) {
                    chess1[x][y] = (ICons_Arr[x][y]);
                }
            }
        }
        King_Coordinates_Row = King_Coordinates_Col = -1;
        setChess(chess1);
        SetGreen();
    }
}
