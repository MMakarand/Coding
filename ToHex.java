import java.io.IOException;

public class ToHex {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();

        if(num < 0)
        {
            num = (1 << 32)  + num;
        }

        while(num != 0)
        {
            int temp = num % 16;
            num = num / 16;

            if(temp == 10)
                sb.append("a");
            else if(temp == 11)
                sb.append("b");
            else if(temp == 12)
                sb.append("c");
            else if(temp == 13)
                sb.append("d");
            else if(temp == 14)
                sb.append("e");
            else if(temp == 15)
                sb.append("f");
            else
                sb.append(temp);


        }
        return new String(sb.reverse());
    }

    public static void main(String[] args)throws IOException
    {
        ToHex obj = new ToHex();
        System.out.println(obj.toHex(-1));
    }
}
