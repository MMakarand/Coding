import java.util.*;
import java.lang.*;
import java.io.*;
public class BFSDFSXTotalShape {
    public static void main (String[] args)
    {
        //code
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int i=0; i<tc; i++)
        {
            int row = sc.nextInt();
            int col = sc.nextInt();
            char[][] ip = new char[row][col];
            int[][] isVisited = new int[row][col];

            for(int ii=0; ii < row; ii++)
            {
                ip[ii] = (sc.next()).toCharArray();
            }

            for(int ii=0; ii < row; ii++)
            {
                for(int j=0; j < col; j++)
                {
                    System.out.print(ip[ii][j]);
                }
                System.out.println();
            }

            BFSDFSXTotalShape obj = new BFSDFSXTotalShape();
            System.out.println("Number of shapes "+ obj.getXShape(ip,isVisited));
        }
    }

    public  int getXShape(char[][] ip,int[][] isVisited)
    {
        class Pos{
            public int x,y;
            Pos(int i, int j){
                x = i;
                y = j;
            }

        };

        int row = ip.length;
        int col = ip[0].length;
        int count =0;
        Queue<Pos> q = new LinkedList<Pos>();

        for(int i = 0; i <row; i++ )
        {
            for(int j=0; j < col; j++)
            {
                if(ip[i][j] == 'X' && isVisited[i][j] == 0)
                {

                    q.add(new Pos(i,j));
                    count++;

                    while(!q.isEmpty()) {
                        Pos tPos = q.peek();
                        q.remove();

                        isVisited[tPos.x][tPos.y] = 1;
                        if (isValid(tPos.x + 1, tPos.y, row, col) && ip[tPos.x + 1][tPos.y] == 'X' && isVisited[tPos.x + 1][tPos.y] == 0) {
                            q.add(new Pos(tPos.x + 1, tPos.y));
                        }

                        if (isValid(tPos.x - 1, tPos.y, row, col) && ip[tPos.x - 1][tPos.y] == 'X' && isVisited[tPos.x - 1][tPos.y] == 0) {
                            q.add(new Pos(tPos.x - 1, tPos.y));
                        }

                        if (isValid(tPos.x , tPos.y+1, row, col) && ip[tPos.x ][tPos.y+1] == 'X' && isVisited[tPos.x ][tPos.y + 1] == 0) {
                            q.add(new Pos(tPos.x , tPos.y+1));
                        }

                        if (isValid(tPos.x , tPos.y - 1, row, col) && ip[tPos.x ][tPos.y - 1] == 'X' && isVisited[tPos.x ][tPos.y - 1] == 0) {
                            q.add(new Pos(tPos.x, tPos.y - 1));
                        }
                    }
                }
            }
        }

        return count;
    }

    private boolean isValid(int i, int j, int row, int col) {
        return i >= 0 && i < row && j >=0 && j < col;
    }
}
