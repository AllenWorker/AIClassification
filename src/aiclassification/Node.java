/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiclassification;
import java.io.Serializable;
/**
 *
 * @author allen
 */
public class Node implements Serializable{
    String data;
    Node left;
    Node right;
    
    public Node(String data)
    {
        this.data = data;
        left = null;
        right = null;
    }
    
    public void setNo(Node node)
    {
        if (left == null)
            left = node;
    }
    
    public void setYes(Node node)
    {
        if (right == null)
            right = node;
    }
    
    public Node getNo()
    {
        return left;
    }
    
    public Node getYes()
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
