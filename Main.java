/*Charlotte Van Houtven
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    //Main method - reads in expression from file , sends to process Expr
    public static void main(String[] args) throws FileNotFoundException {
        try
        {
            Scanner input = new Scanner(System.in);
            File myfile = new File("src/exprs");
            input = new Scanner(myfile); //Reading in file
            while (input.hasNextLine())
            {
                String line = input.nextLine();
                processExpr(line);
            }
        }

        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("File Not Found");
        }
    }

    //Takes a String as input and turns the expression into an expression tree
    public static void processExpr(String line)
    {
        Stack<ExpressionTree> treestack = new Stack<ExpressionTree>();  //Stack of expression trees

        String[] expressionarray = line.split("   ");

        for (int i = 0; i < expressionarray.length; i++) { //For all numbers and operators
            if (!expressionarray[i].equals("+") && !expressionarray[i].equals("-") && !expressionarray[i].equals("*") && !expressionarray[i].equals("/")) {
                ExpressionTree.ExpressionTreeNode treenode = new ExpressionTree.ExpressionTreeNode(expressionarray[i]); //if an operator, create a node
                ExpressionTree treeA = new ExpressionTree(treenode);
                treestack.push(treeA); //push tree onto stack
            } else {
                ExpressionTree.ExpressionTreeNode tempA = treestack.pop().getRoot(); //if an operator, pop
                ExpressionTree.ExpressionTreeNode tempB = treestack.pop().getRoot();
                ExpressionTree.ExpressionTreeNode operator = new ExpressionTree.ExpressionTreeNode(expressionarray[i]);
                operator.setLeft(tempB); //set child nodes
                operator.setRight(tempA);
                ExpressionTree newtree = new ExpressionTree(operator);
                treestack.push(newtree); //push this new  tree with children 
            }
        }
        System.out.println("Preorder: ");
        treestack.peek().preorder();
        System.out.println();
        System.out.println("Postorder: ");
        treestack.peek().postorder();
        System.out.println();
        System.out.println("Inorder: ");
        treestack.peek().inorder();
        treestack.peek().postordereval();
        System.out.println();
    }
}
