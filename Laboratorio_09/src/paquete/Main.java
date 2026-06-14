package paquete;

public class Main{
	public static void main(String[] args) {
	    BNode.resetCounter();
	    BTree<Integer> bt = new BTree<>(4);

	    // árbol de figura 10.14
	    int[] keys = {31, 12, 19, 3, 10, 13, 16, 22, 25, 28, 41, 57, 63,
	                  33, 35, 40, 49, 52, 55, 60, 62, 67, 70, 72};
	    for (int k : keys)
	        bt.insert(k);

	    System.out.println(bt);
	    System.out.println("--- Pruebas search ---");

	    System.out.println(bt.search(52));   // hoja intermedia  → true,  nodo 6 posición 1
	    System.out.println(bt.search(3));    // hoja extremo ini → true,  nodo 1 posición 0
	    System.out.println(bt.search(72));   // hoja extremo fin → true,  nodo 10 posición 2
	    System.out.println(bt.search(31));   // raíz             → true,  nodo 9 posición 0
	    bt.remove(25);  System.out.println(bt);
	    bt.remove(10);  System.out.println(bt);
	    bt.remove(50);  System.out.println(bt);
	    bt.remove(70);  System.out.println(bt);
	    bt.remove(27);  System.out.println(bt);
	    bt.remove(5);   System.out.println(bt);
	    bt.remove(75);  System.out.println(bt);
	}
}
