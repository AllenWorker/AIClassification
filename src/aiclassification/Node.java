/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiclassification;

/**
 *
 * @author allen
 */
public class Node {
    String data;
    Node left;
    Node right;
    
    public Node(String data)
    {
        this.data = data;
        left = null;
        right = null;
    }
    
    public void setLeft(Node node)
    {
        if (left == null)
            left = node;
    }
    
    public void setRight(Node node)
    {
        if (right == null)
            right = node;
    }
    
    public Node getLeft()
    {
        return left;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public String getData()
    {
        return data;
    }
    
    public void setData(String node)
    {
        this.data = data;
    }
    
}
