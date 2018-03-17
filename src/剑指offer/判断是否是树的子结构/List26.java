package 剑指offer.判断是否是树的子结构;

/**
 * Created by wby on 2018/3/17.
 */
public class List26 {
    public static void main(String[] args) {

        //构造树（树结构见剑指offer）-->前：第一棵树：8,8,9,2,4,7,7
        //                               第二棵树:8,9,2
        //                           中：第一棵树：9,8,4,2，7,7
        //                               第二棵树：9,8,2
        TreeNode root1 = new TreeNode(8);
        root1.leftNode = new TreeNode(8);
        root1.rightNode = new TreeNode(7);
        root1.leftNode.leftNode = new TreeNode(9);
        root1.leftNode.rightNode = new TreeNode(3);
        root1.rightNode.leftNode = new TreeNode('#');
        root1.rightNode.rightNode = new TreeNode('#');
        root1.leftNode.leftNode.leftNode = new TreeNode('#');
        root1.leftNode.leftNode.rightNode = new TreeNode('#');
        root1.leftNode.rightNode.leftNode = new TreeNode(4);
        root1.leftNode.rightNode.rightNode = new TreeNode(7);

        TreeNode root2 = new TreeNode(8);
        root2.leftNode = new TreeNode(8);
        root2.rightNode = new TreeNode(7);

        System.out.println(new HasSubTree().hasSubTree(root1,root2));
    }
}

//树结构
class TreeNode{
    int val;
    TreeNode leftNode;
    TreeNode rightNode;
    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }
}
class HasSubTree{


   public boolean hasSubTree(TreeNode root1,TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            //根节点相等，后续左右子树是否还是相等呢
            if (root1.val == root2.val) {
                result =doesTree1HaveTree2(root1,root2);
            }
            //根节点的左子树
            if (!result) {
                result = hasSubTree(root1.leftNode, root2);

            }
            //根节点的右子树
            if (!result) {
                result = hasSubTree(root1.rightNode, root2);
            }

        }
        return result;
    }


    public  boolean doesTree1HaveTree2(TreeNode root1,TreeNode root2){
        if(root1==null){
            return false;

        }
        if(root2==null){
            return true;

        }
        if(root1.val != root2.val) {
            return false;
        }
        return doesTree1HaveTree2(root1.leftNode,root2.leftNode)&&doesTree1HaveTree2(root1.rightNode,root2.rightNode);
    }
}




