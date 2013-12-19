
package ruzzlesolver;


public class RuzzlePiece
{
    String letter;
    int points;

    public RuzzlePiece(String l, int p)
    {
        letter = l;
        points = p;
    }

    public RuzzlePiece(String l)
    {
        letter = l;
        points = RuzzlePiece.getPointVal(l);
        if(points == 0) points = 1;
    }

    public static int getPointVal(String l)
    {
        if(l.equals("a")) return 1;
        if(l.equals("b")) return 4;
        if(l.equals("c")) return 4;
        if(l.equals("d")) return 2;
        if(l.equals("e")) return 1;
        if(l.equals("f")) return 4;
        if(l.equals("g")) return 3;
        if(l.equals("h")) return 4;
        if(l.equals("i")) return 1;
        if(l.equals("j")) return 10;
        if(l.equals("k")) return 5;
        if(l.equals("l")) return 1;
        if(l.equals("m")) return 3;
        if(l.equals("n")) return 1;
        if(l.equals("o")) return 1;
        if(l.equals("p")) return 4;
        if(l.equals("q")) return 0;
        if(l.equals("r")) return 1;
        if(l.equals("s")) return 1;
        if(l.equals("t")) return 1;
        if(l.equals("u")) return 2;
        if(l.equals("v")) return 4;
        if(l.equals("w")) return 4;
        if(l.equals("x")) return 0;
        if(l.equals("y")) return 4;
        if(l.equals("z")) return 0;
        return 0;
    }

}
