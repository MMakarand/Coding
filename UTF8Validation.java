import java.io.IOException;

public class UTF8Validation {
    public boolean validUtf8(int[] data) {

        boolean isValidUtf = true;

        for(int i=0; i < data.length; i++)
        {

            if(data[i] >=0 && data[i] <= 191)
            {
                if(((data[i] >> 7) & 1) != 0)
                    return false;
            }
            else if(data[i] >= 192 && data[i] <= 223)
            {
                if(i >= data.length - 1)
                    return false;

                if((((data[i] >> 5) & 6) != 6) || (((data[i+1] >> 6) & 2) != 2))
                    return false;
                i++;

            }
            else if(data[i] >= 224 && data[i] <= 239)
            {
                if(i >= data.length - 2)
                    return false;

                if(((data[i] >> 4) & 14) != 14 || (((data[i+1] >> 6) & 2) != 2) || (((data[i+2] >> 6) & 2) != 2))
                    return false;
                i++;
                i++;
            }
            else if(data[i] >= 240 && data[i] <= 247){
                if(i >= data.length - 3)
                    return false;

                if(((data[i] >> 3) & 30) != 30 || (((data[i+1] >> 6) & 2) != 2) || (((data[i+2] >> 6) & 2) != 2) || (((data[i+3] >> 6) & 2) != 2))
                    return false;

                i++;
                i++;
                i++;
            }
            else if(data[i] >= 248 && data[i] < 256)
            {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args)throws IOException
    {
        int[] data = {8,21,212,177,245,187,178,157,18,196,155,227,164,170,5,196,144,229,187,161,235,164,188,12,198,163,245,135,188,189,113,77,202,177,213,166,237,129,180,247,174,169,167,217,142,228,154,184,245,163,146,157,107,225,182,180,243,149,146,153,210,158,30,201,167,227,160,160};//{197, 130, 1};
        UTF8Validation obj = new UTF8Validation();
        System.out.println(obj.validUtf8(data));
    }
}



