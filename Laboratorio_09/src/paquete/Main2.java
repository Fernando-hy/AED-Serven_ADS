package paquete;

public class Main2 {
	public static void main(String[] args) {
	    BNode.resetCounter();
	    BTree<Integer> bt = new BTree<>(4);

	    int[] keys = {10, 15, 20, 25, 30, 35, 40, 45};
	    for (int k : keys)
	        bt.insert(k);

	    System.out.println(bt);
	    System.out.println("--- Pruebas searchRange ---");

	    bt.searchRange(20, 40);   // rango existente   → 20 25 30 35 40
	    bt.searchRange(50, 60);   // rango inexistente → (vacío)
	    bt.searchRange(40, 20);   // rango inválido    → mensaje de error
	}
	
}
