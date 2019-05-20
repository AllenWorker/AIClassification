/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import aiclassification.BTree;
import aiclassification.Node;
import junit.framework.Assert;

/**
 *
 * @author J392018
 */
public class AIClassificationJUnitTest {
    
    public AIClassificationJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    public Node defaultData()
    {
        Node root = new Node("Is it a mammal?");
        
        root.setYes(new Node("Monkey"));
        root.setNo(new Node("Chicken"));
        return root;
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGetRoot()
    {
        BTree testTree = new BTree();
        
        testTree.setRoot(defaultData());
        assertEquals(defaultData().getData(), testTree.getRoot().getData());
    }
    
    @Test
    public void testIsEmpty()
    {
        BTree testTree = new BTree();
        
        assertEquals(true, testTree.isEmpty());
    }
    
    @Test
    public void testIsEmpty2()
    {
        BTree testTree = new BTree();
        testTree.setRoot(defaultData());
        assertEquals(false, testTree.isEmpty());
    }
    
    @Test
    public void testCountNode()
    {
        BTree testTree = new BTree();
        
        testTree.setRoot(defaultData());
        assertEquals(3, testTree.countNode());
    }
    
    @Test
    public void testCountNode2()
    {
        BTree testTree = new BTree();
        testTree.setRoot(defaultData());
        testTree.setCurrent(testTree.getRoot());
        testTree.setCurrent(testTree.getCurrent().getYes());
        String temp = testTree.getCurrent().getData();
        testTree.getCurrent().setData("Does it dig?");
        testTree.getCurrent().setYes(new Node("Fox"));
        testTree.getCurrent().setNo(new Node(temp));
        testTree.setCurrent(testTree.getRoot());
        
        
        assertEquals(5, testTree.countNode());
    }
    
    
    
    
    
}
