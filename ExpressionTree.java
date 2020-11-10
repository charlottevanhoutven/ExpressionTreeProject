/*Charlotte Van Houtven
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

//Expression tree object class
public class ExpressionTree {

    private ExpressionTreeNode root;

    public ExpressionTree(ExpressionTreeNode myroot) {
        this.root = myroot;
    }

    public ExpressionTreeNode getRoot() {
        return this.root;
    }

    public void setRoot(ExpressionTreeNode myroot) {
        this.root = myroot;
    }

    public void preorder() {
        preorderNode(root);
    }

    //Preorder traversal that receives tree root node
    public void preorderNode(ExpressionTreeNode node)
    {
        if (node != null)
        {
            System.out.print(node.getItem() + " ");
            preorderNode(node.getLeft()); //move to left child
            preorderNode(node.getRight()); //move to right child
        }

    }

    public void postorder()
    {
        postorderNode(root);
    }

    //Postorder traversal that receives tree root node
    public void postorderNode(ExpressionTreeNode node) {
        if (node != null)
        {
            postorderNode(node.getLeft());
            postorderNode(node.getRight());
            System.out.print(node.getItem() + " ");
        }
    }
    //Prints answer using postorder traversal
    public void postordereval()
    {
        postorderevalNode(root);
        System.out.print(" = " + root.getItem());
    }

    //Method that evaluates the expression tree using postorder traversal
    //Receives a tree root node
    public void postorderevalNode(ExpressionTreeNode node)
    {
        if (node.getRight() == null && node.getLeft() == null) //If there is one node left
        {
            System.out.print(" = " + node.getItem());
        }
        if (!isNum(node.getLeft().getItem()) )
        {
            postorderevalNode(node.getLeft());
        }
        if (!isNum(node.getRight().getItem()) )
        {
            postorderevalNode(node.getRight());
        }


        if (isNum(node.getLeft().getItem()) && isNum(node.getRight().getItem())) //If node is an operator
        {
            Double leftoperand = Double.parseDouble(node.getLeft().getItem());
            Double rightoperand = Double.parseDouble(node.getRight().getItem());
            String operator = node.getItem();
            Double replacement;

            if (operator.equals("*")) {
                replacement = leftoperand * rightoperand;
                node.setItem(replacement.toString());
            } else if (operator.equals("/")) {
                replacement = leftoperand / rightoperand;
                node.setItem(replacement.toString());
            } else if (operator.equals("-")) {
                replacement = leftoperand - rightoperand;
                node.setItem(replacement.toString());
            } else {
                replacement = leftoperand + rightoperand;
                node.setItem(replacement.toString());
            }
            node.setRight(null); //Getting rid of child nodes because we took care of them
            node.setLeft(null);
        }

    }

    //Helper method that returns a boolean whether or not a String is numerical
    public boolean isNum(String x)
    {
    try {
        Double.parseDouble(x);
        return true;
    }
    catch(NumberFormatException e){
        return false;
    }
}


    public void inorder() {
        inorderNode(root);
    }

    //Inorder traversal of expression tree, receives  the root
    public void inorderNode(ExpressionTreeNode node)
    {
        int parencount = 0;
        if (node != null)
        {
            if (node.getItem().equals("*") || node.getItem().equals("/") || node.getItem().equals("-") || node.getItem().equals("+")) //If an operator
            {
                if (node.getLeft() != null)
                {
                    System.out.print("(");
                }
            }

            inorderNode(node.getLeft());
            System.out.print(node.getItem() );
            inorderNode(node.getRight());

            if (node.getItem().equals("*") || node.getItem().equals("/") || node.getItem().equals("-") || node.getItem().equals("+"))
            {
                System.out.print(")");
            }

        }

    }


    //Object class of node in expression tree
    public static class ExpressionTreeNode {

        private ExpressionTreeNode left; //1st child
        private ExpressionTreeNode right; //2nd child
        private String item; //number or operator stored as String

        public ExpressionTreeNode(String item)
        {
            this.item = item;
            this.left = null;
            this.right = null;
        }

        public ExpressionTreeNode getLeft() {
            return left;
        }

        public void setLeft(ExpressionTreeNode left) {
            this.left = left;
        }

        public ExpressionTreeNode getRight() {
            return right;
        }

        public void setRight(ExpressionTreeNode right) {
            this.right = right;
        }

        public String getItem() {
            return this.item;
        }

        public void setItem(String myitem) {
            this.item = myitem;
        }
    }
}
