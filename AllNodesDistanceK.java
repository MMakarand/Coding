package Contest;

import javax.swing.tree.TreeNode;
import java.util.*;


public class AllNodesDistanceK {

  public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    };
    public static void main(String [] args)throws Exception{
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left = new TreeNode(0);
        AllNodesDistanceK obj = new AllNodesDistanceK();

        List<Integer> op = obj.distanceK(root,root.left,2);

        for(Integer i : op)
            System.out.println(i);
    }
    public void createParentMapping(TreeNode root, HashMap parent)
    {

        if(root == null)
            return;

        if(root.left != null)
        {
            parent.put(root.left,root);
            createParentMapping(root.left,parent);
        }
        if(root.right != null)
        {
            parent.put(root.right,root);
            createParentMapping(root.right,parent);
        }

    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if(root == null)
            return null;

        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);

        createParentMapping(root,parent);

        HashMap<TreeNode, Boolean> seen = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(target);
        queue.offer(null);
        int level = 0;

        while(!queue.isEmpty())
        {


            if(level == K)
            {
                List<Integer> op = new ArrayList<Integer>();
                while(!queue.isEmpty() && queue.peek() != null)
                {
                    op.add(queue.remove().val);
                }
                return op;

            }

            TreeNode cur = queue.remove();
            if(cur == null)
            {
                level++;
                queue.offer(null);
            }

            else if(!seen.containsKey(cur))
            {
                if(cur.left != null && !seen.containsKey(cur.left))
                    queue.offer(cur.left);

                if(cur.right != null && !seen.containsKey(cur.right))
                    queue.offer(cur.right);

                if(parent.get(cur) != null && !seen.containsKey(parent.get(cur)))
                    queue.offer(parent.get(cur));

                seen.put(cur,true);
            }
        }

        return null;
    }

}
