/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JChess.Pieces;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import test.Table.Pieces;
import static test.Table.*;

/**
 *
 * @author 20114
 */
public abstract class piece {

    final Pieces Rook = Pieces.Rook;
    final Pieces Knight = Pieces.Knight;
    final Pieces Bishop = Pieces.Bishop;
    final Pieces King = Pieces.King;
    final Pieces Queen = Pieces.Queen;
    final Pieces Pawn = Pieces.Pawn;

    
    protected Icon image;
    protected int[][] kingGr = new int[10][10];
    protected String[][] kingcon = new String[10][10];
    protected int OpponentPiece;// number indecates if the peice we are attackin if it's an allie or opp
    public final Icon white = new ImageIcon("E:\\pic\\white.PNG");
    public final Icon black = new ImageIcon("E:\\pic\\black.PNG");
    public final Icon GreenIcon = new ImageIcon("E:\\pic\\green.PNG");
    protected Icon rechess1[][] = new Icon[10][10];
    protected Icon chess1[][] = new Icon[10][10];

    public piece() {

    }

    public piece(Icon image, int OpponentPiece) {
        this.image = image;
        this.OpponentPiece = OpponentPiece;
    }
 
    public void InitializeChessBoard(Icon chess1[][]) {//Initialize The Chess Board With Black and white Blocks
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 0) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            chess1[i][j] = (white);
                        } else {
                            chess1[i][j] = (black);
                        }
                    } else {
                        if (j % 2 != 0) {
                            chess1[i][j] = (white);
                        } else {
                            chess1[i][j] = (black);
                        }
                    }
                }
            }
        }
    }

    public void setChess(Icon[][] chessc) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess[i][j].setIcon(chessc[i][j]);
            }
        }
    }

    public String getPieceIcon(int Current_Player, String Piece_ICon, Pieces pieceType) {

        if (pieceType == Rook) {
            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\hll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\alb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\abl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\hbb.PNG";
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\hll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\alb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\abl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\hbb.PNG";
                    }
                }
            }
            return Piece_ICon;

        } else if (pieceType == Knight) {

            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\bll.PNG"; // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\glb.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\gbl.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\bbb.PNG"; // black piece on black cell
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\bll.PNG";  // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\glb.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\gbl.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\bbb.PNG"; // black piece on black cell
                    }
                }
            }
            return Piece_ICon;

        } else if (pieceType == Bishop) {
            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\fll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\clb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\cbl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\fbb.PNG";
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\fll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\clb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\cbl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\fbb.PNG";
                    }
                }
            }
            return Piece_ICon;

        } else if (pieceType == King) {//Still need to do
            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\www.PNG"; // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\elb.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\ebl.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\lbb.PNG"; // black piece on black cell
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\www.PNG";  // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\elb.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\ebl.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\lbb.PNG"; // black piece on black cell
                    }
                }
            }
            return Piece_ICon;

        } else if (pieceType == Queen) {
            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\dll.PNG"; // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\bwq.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\wbq.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\dbb.PNG"; // black piece on black cell
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\dll.PNG";  // white piece on white cell
                    } else {
                        Piece_ICon = "E:\\pic\\bwq.PNG"; // black piece on white cell
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\wbq.PNG"; // white piece on black cell
                    } else {
                        Piece_ICon = "E:\\pic\\dbb.PNG"; // black piece on black cell
                    }
                }
            }
            return Piece_ICon;
        } else if (pieceType == Pawn) {
            if (idx1 % 2 == 0) {
                if (idx2 % 2 == 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\jll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\jlb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\jbl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\jbb.PNG";
                    }
                }
            } else {
                if (idx2 % 2 != 0) {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\jll.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\jlb.PNG";
                    }
                } else {
                    if (Current_Player == 1) {
                        Piece_ICon = "E:\\pic\\jbl.PNG";
                    } else {
                        Piece_ICon = "E:\\pic\\jbb.PNG";
                    }
                }
            }
            return Piece_ICon;
        }

        return Piece_ICon;
    }

    public abstract void Move(Icon white, Icon black, Icon GreenIcon,Coordinates Pos);

    public boolean isDraw() {
        int k1 = 0, k2 = 0, n = 0, b1 = 0, b2 = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0) {
                    n++;
                }
                if (board[i][j] == 3) {
                    k1++;
                }
                if (board[i][j] == 33) {
                    k2++;
                }
                if (board[i][j] == 2) {
                    b1++;
                }
                if (board[i][j] == 22) {
                    b2++;
                }
            }
        }
        if ((n == 32 && Operation_Conter == 50) || (n == 3 && (k1 == 1 || k2 == 1 || b1 == 1 || b2 == 1)) || (n == 4 && (k1 == 2 || k2 == 2))) {
            return true;
        }
        return false;
    }
}
