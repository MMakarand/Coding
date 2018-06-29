

public class MaxXOR {


    public static void main(String[] args)
    {

        MaxXOR obj = new MaxXOR();
//        int nums[] = {3, 10, 5, 25, 2, 8};
        int nums[] = {1,2};
        int op = obj.findMaximumXOR( nums);
//        System.out.println(op);
    }

    class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val)
        {
            this.val = val;
            left = null;
            right = null;
        }

    };

    public int findMaximumXOR(int[] nums) {

        TreeNode root = new TreeNode(-1);

        int max = 0;
        generteTrie( nums, root);
        TravelTree(root);

        max =  getMaxXOR(root,root,(0),2);
        System.out.println("\n"+max);
        return max;
    }

    private void TravelTree(TreeNode root) {
        if(root == null)
            return;
        if(root.left != null)
        {
            TravelTree(root.left);
        }
        System.out.print(root.val + " ");
        if(root.right != null)
        {
            TravelTree(root.right);
        }
    }


    public void generteTrie(int[] nums, TreeNode rootOrig)
    {
        for(int i:nums)
        {
            TreeNode root = rootOrig;
            for(int j=2; j >= 0; j--)
            {
                if( (i & (1 << j)) > 0)
                {
                    if(root.right == null)
                        root.right = new TreeNode(1);
                    root = root.right;
                }
                else
                {
                    if(root.left == null)
                        root.left = new TreeNode(0);

                    root = root.left;
                }
            }

        }
    }
    public  int getMaxXOR(TreeNode left, TreeNode right, int sum, int index)
    {
        //int max = 0;
        int lr=0,ll=0,rr=0,rl=0;

        if(index == -1)
            return sum;

        if(left == right)
        {
            if(left.left != null && right.right != null)
            {
                lr = getMaxXOR(left.left, right.right, sum + 1 << index, index - 1);
                return lr;

            }
            else if(left.left == null)
            {
                return getMaxXOR(left.right,left.right,sum,index-1);
            }
            else
            {
                return getMaxXOR(left.left,left.left,sum,index-1);
            }
        }
        else
        {
            if (left.left != null && right.right != null)
                lr = getMaxXOR(left.left, right.right, (int) (sum + 1 << index), index - 1);


            if (left.right != null && right.left != null)
                rl = getMaxXOR(left.right, right.left, (int) (sum + 1<<index), index - 1);


            if (left.right == null && right.right == null)
            {
                ll = getMaxXOR(left.left, right.left, sum, index - 1);
            }
            if (right.left == null && left.left == null)
            {
                rr = getMaxXOR(left.right, right.right, sum, index - 1);
            }

            return Math.max(Math.max(lr,rl), Math.max(rr,ll));
        }


    }

}



