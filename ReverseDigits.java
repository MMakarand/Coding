public class ReverseDigits {
    public int reverseBits(int n) {

        long op = 0;
        long mask = 1;
        long temp = 0;

        for(int i=0; i < 32; i++)
        {
            mask = (long)Math.pow(2,i);
            temp =n & mask;
            op= (op << 1) | (temp >> i);
        }
        return (int)op;
    }

    public static void main(String[] args)
    {
        ReverseDigits obj = new ReverseDigits();
        long n = 2147483648L;
        System.out.println(obj.reverseBits((int)n));
    }
}
