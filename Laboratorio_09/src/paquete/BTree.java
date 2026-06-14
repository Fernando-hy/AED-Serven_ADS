package paquete; // paquete donde se define la clase BTree

public class BTree<E extends Comparable<E>> { // árbol B genérico donde E debe ser comparable
    private BNode<E> root; // referencia al nodo raíz del árbol
    private final int orden; // orden máximo del árbol B
    private boolean up; // bandera para indicar que una clave debe subir al padre
    private BNode<E> nDes; // nodo derecho que surge al dividir un nodo

    public BTree(int orden) { // constructor que recibe el orden del árbol
        this.orden = orden; // guarda el orden
        this.root = null; // inicializa el árbol vacío
    }

    public boolean isEmpty() { // comprueba si el árbol no tiene raíz
        return this.root == null; // retorna verdadero cuando la raíz es nula
    }
    
    
    public void insert(E cl) { // inserta una clave en el árbol B
        up = false; // inicializa la bandera de subida de clave
        E mediana; // variable para la clave mediana si se divide un nodo
        BNode<E> pnew; // nodo nuevo que puede convertirse en raíz
        mediana = push(this.root, cl); // inserción recursiva en la raíz
        if (up) { // si la raíz se dividió, se crea una nueva raíz
            pnew = new BNode<>(this.orden); // crea un nuevo nodo raíz
            pnew.count = 1; // la nueva raíz tendrá una clave
            pnew.keys.set(0, mediana); // coloca la mediana en la raíz
            pnew.childs.set(0, this.root); // antiguo árbol como hijo izquierdo
            pnew.childs.set(1, nDes); // nodo derecho después de la división
            this.root = pnew; // actualiza la raíz
        }
    }

    private E push(BNode<E> current, E cl) { // inserta recursivamente en current
        int[] pos = new int[1]; // array de un elemento para devolver la posición
        E mediana; // clave que sube al padre en caso de división
        if (current == null) { // si alcanza una posición vacía
            up = true; // la clave debe subir al padre
            nDes = null; // no hay nodo derecho asociado aún
            return cl; // devuelve la clave para insertar en el padre
        } else {
            boolean found = current.searchNode(cl, pos); // busca la clave en el nodo actual
            if (found) {
                System.out.println("Item duplicado: " + cl); // notifica duplicado
                up = false; // no se inserta nada más arriba
                return null; // retorna null porque no hay mediana
            }
            mediana = push(current.childs.get(pos[0]), cl); // desciende al hijo indicado
            if (up) { // si la inserción provocó una subida
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]); // divide el nodo actual
                else {
                    up = false; // no se propagará más la subida
                    putNode(current, mediana, nDes, pos[0]); // inserta en el nodo actual
                }
            }
            return mediana; // devuelve la mediana si existe
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) { // inserta clave y puntero en current
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i)); // mueve clave a la derecha
            current.childs.set(i + 2, current.childs.get(i + 1)); // mueve hijo derecho a la derecha
        }
        current.keys.set(k, cl); // coloca la nueva clave en la posición k
        current.childs.set(k + 1, rd); // coloca el puntero derecho asociado
        current.count++; // incrementa el número de claves en el nodo
    }

    private E dividedNode(BNode<E> current, E cl, int k) { // divide un nodo lleno y devuelve la mediana
        BNode<E> rd = nDes; // nodo derecho que proviene de la inserción
        int posMdna; // variable de índice
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1; // calcula la posición de la mediana
        nDes = new BNode<>(this.orden); // crea el nodo derecho resultante
        for (int i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i)); // copia clave al nodo derecho
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1)); // copia hijo al nodo derecho
        }
        nDes.count = (this.orden - 1) - posMdna; // establece la cantidad de claves de nDes
        current.count = posMdna; // ajusta la cantidad de claves del nodo actual
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k); // inserta en el lado izquierdo
        else
            putNode(nDes, cl, rd, k - posMdna); // inserta en el lado derecho
        E median = current.keys.get(current.count - 1); // toma la mediana del nodo actual
        nDes.childs.set(0, current.childs.get(current.count)); // ajusta el primer hijo de nDes
        current.count--; // reduce count por la clave mediana retirada
        return median; // devuelve la clave mediana al padre
    }

    //  SEARCH
    public boolean search(E cl) { // busca una clave en el árbol
        return searchRec(this.root, cl); // inicia la búsqueda recursiva desde la raíz
    }

    private boolean searchRec(BNode<E> current, E cl) { // busca recursivamente en el subárbol current
        if (current == null) return false; // si no hay nodo, no encontró la clave
        int[] pos = new int[1]; // posición donde puede estar la clave o el hijo a bajar
        boolean found = current.searchNode(cl, pos); // busca en el nodo actual
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posición " + pos[0]); // informa dónde está la clave
            return true; // retorna verdadero si la clave existía
        }
        return searchRec(current.childs.get(pos[0]), cl); // desciende al hijo correspondiente
    }

    //  SEARCH RANGE
    public void searchRange(E min, E max) { // busca todas las claves en un rango
        if (min.compareTo(max) > 0) { // valida que el rango sea correcto
            System.out.println("Rango inválido: min > max"); // informa error si min > max
            return; // detiene la búsqueda
        }
        System.out.print("Claves en rango [" + min + ", " + max + "]: "); // imprime encabezado
        searchRangeRec(this.root, min, max); // recorre el árbol buscando en orden
        System.out.println(); // nuevo renglón al final
    }

    private void searchRangeRec(BNode<E> current, E min, E max) { // recorre en orden el subárbol current
        if (current == null) return; // si no hay nodo, retorna
        int i = 0; // índice de clave actual dentro del nodo
        while (i < current.count) { // recorre todas las claves del nodo
            if (current.keys.get(i).compareTo(min) > 0)
                searchRangeRec(current.childs.get(i), min, max); // busca en el hijo izquierdo si clave > min
            if (current.keys.get(i).compareTo(min) >= 0
                    && current.keys.get(i).compareTo(max) <= 0)
                System.out.print(current.keys.get(i) + " "); // imprime clave dentro del rango
            if (current.keys.get(i).compareTo(max) > 0)
                return; // si la clave supera max, termina el recorrido
            i++; // siguiente clave
        }
        searchRangeRec(current.childs.get(current.count), min, max); // busca en el último hijo
    }

    //  REMOVE
    public void remove(E cl) { // elimina una clave del árbol B
        if (isEmpty()) { // si el árbol está vacío
            System.out.println("El árbol está vacío."); // muestra mensaje
            return; // sale sin hacer nada
        }
        removeRec(this.root, cl, null, -1); // inicia la eliminación en la raíz
    }

    private void removeRec(BNode<E> current, E cl, BNode<E> parent, int parentIdx) { // elimina recursivamente en el subárbol current
        if (current == null) { // si llega a un nodo nulo
            System.out.println("Clave " + cl + " no encontrada."); // la clave no existe
            return; // retorna
        }
        int[] pos = new int[1]; // posición dentro del nodo o hijo a descender
        boolean found = current.searchNode(cl, pos); // busca la clave en el nodo actual

        if (found) {
            if (isLeaf(current)) { // caso A: la clave está en una hoja
                deleteFromNode(current, pos[0]); // elimina la clave de la hoja
                fix(parent, parentIdx); // corrige posible underflow en el padre
            } else { // caso B: la clave está en un nodo interno
                BNode<E> successorNode = getMinNode(current.childs.get(pos[0] + 1)); // obtiene el sucesor in-order
                E successor = successorNode.keys.get(0); // clave mínima del subárbol derecho
                current.keys.set(pos[0], successor); // reemplaza la clave por el sucesor
                removeRec(current.childs.get(pos[0] + 1),
                          successor, current, pos[0] + 1); // elimina el sucesor en el subárbol derecho
            }
        } else {
            removeRec(current.childs.get(pos[0]), cl, current, pos[0]); // caso C: desciende al hijo correspondiente
        }
    }

    // recibe el padre y el índice del hijo con posible underflow 
    private void fix(BNode<E> parent, int index) { // corrige la falta de claves tras eliminación
        if (parent == null) { // si current era la raíz
            if (this.root.count == 0) { // verifica si la raíz se quedó sin claves
                if (this.root.childs.get(0) != null)
                    this.root = this.root.childs.get(0); // promueve el único hijo como raíz
                else
                    this.root = null; // si la raíz hoja quedó vacía, el árbol queda vacío completamente
            }
            return; // no hay corrección superior
        }

        BNode<E> current = parent.childs.get(index);    // nodo con posible underflow
        int minKeys = (int) Math.ceil(this.orden / 2.0) - 1; // mínimo de claves permitidas

        if (current.count >= minKeys) return;            // si tiene suficientes claves, no hace nada

        if (index > 0) { // intenta tomar prestado del hermano izquierdo
            BNode<E> left = parent.childs.get(index - 1);
            if (left.count > minKeys) {
                borrowFromLeft(parent, index); // redistribuye desde la izquierda
                return;
            }
        }

        if (index < parent.count) { // intenta tomar prestado del hermano derecho
            BNode<E> right = parent.childs.get(index + 1);
            if (right.count > minKeys) {
                borrowFromRight(parent, index); // redistribuye desde la derecha
                return;
            }
        }

        if (index > 0)
            merge(parent, index - 1);                   // fusiona con el hermano izquierdo
        else
            merge(parent, index);                       // fusiona con el hermano derecho

        fix(findParent(this.root, parent),
            findParentIdx(this.root, parent)); // propaga la corrección hacia arriba
    }

    private void borrowFromLeft(BNode<E> parent, int index) { // toma una clave del hermano izquierdo
        BNode<E> left    = parent.childs.get(index - 1); // nodo izquierdo
        BNode<E> current = parent.childs.get(index);     // nodo con underflow

        for (int i = current.count - 1; i >= 0; i--)
            current.keys.set(i + 1, current.keys.get(i)); // desplaza claves de current a la derecha

        current.keys.set(0, parent.keys.get(index - 1)); // baja la clave separadora del padre

        if (left.childs.get(left.count) != null) { // si el hermano izquierdo no es hoja
            for (int i = current.count; i >= 0; i--)
                current.childs.set(i + 1, current.childs.get(i)); // desplaza hijos de current a la derecha
            current.childs.set(0, left.childs.get(left.count)); // mueve el último hijo de left a current
            left.childs.set(left.count, null); // limpia el puntero movido
        }

        parent.keys.set(index - 1, left.keys.get(left.count - 1)); // sube última clave de left al padre
        left.keys.set(left.count - 1, null);                        // elimina la clave cedida
        current.count++; // current gana una clave
        left.count--; // left pierde una clave
    }

    private void borrowFromRight(BNode<E> parent, int index) { // toma una clave del hermano derecho
        BNode<E> current = parent.childs.get(index);     // nodo con underflow
        BNode<E> right   = parent.childs.get(index + 1); // hermano derecho

        current.keys.set(current.count, parent.keys.get(index)); // baja la clave separadora del padre a current

        if (right.childs.get(0) != null) { // si el hermano derecho no es hoja
            current.childs.set(current.count + 1, right.childs.get(0)); // mueve primer hijo de right a current
            for (int i = 0; i < right.count; i++)
                right.childs.set(i, right.childs.get(i + 1)); // desplaza hijos de right a la izquierda
            right.childs.set(right.count, null); // limpia el último puntero
        }

        parent.keys.set(index, right.keys.get(0));       // sube primera clave de right al padre

        for (int i = 0; i < right.count - 1; i++)
            right.keys.set(i, right.keys.get(i + 1)); // desplaza claves de right a la izquierda
        right.keys.set(right.count - 1, null);           // limpia la última posición de right
        current.count++; // current gana una clave
        right.count--; // right pierde una clave
    }

    private void merge(BNode<E> parent, int index) { // fusiona dos hijos adyacentes
        BNode<E> left  = parent.childs.get(index);       // hijo izquierdo
        BNode<E> right = parent.childs.get(index + 1);   // hijo derecho

        left.keys.set(left.count, parent.keys.get(index)); // baja la clave del padre al final de left
        left.count++; // left gana una clave más

        for (int i = 0; i < right.count; i++)
            left.keys.set(left.count + i, right.keys.get(i)); // copia las claves de right a left

        for (int i = 0; i <= right.count; i++)
            left.childs.set(left.count + i, right.childs.get(i)); // copia los hijos de right a left

        left.count += right.count; // suma las claves de right en left

        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1)); // mueve claves del padre a la izquierda
            parent.childs.set(i + 1, parent.childs.get(i + 2)); // mueve punteros del padre a la izquierda
        }
        parent.keys.set(parent.count - 1, null); // limpia la última clave del padre
        parent.childs.set(parent.count, null); // limpia el último puntero del padre
        parent.count--; // reduce el conteo de claves en el padre

        if (parent == this.root && parent.count == 0)
            this.root = left; // si la raíz quedó vacía, promueve left como raíz
    }

    // helpers
    private boolean isLeaf(BNode<E> node) { // comprueba si el nodo es hoja
        return node.childs.get(0) == null; // es hoja cuando no tiene hijo izquierdo
    }

    private BNode<E> getMinNode(BNode<E> node) { // devuelve el nodo con la mínima clave
        while (node.childs.get(0) != null)
            node = node.childs.get(0); // avanza por la rama izquierda
        return node; // retorna el nodo más a la izquierda
    }

    private void deleteFromNode(BNode<E> node, int k) { // elimina la clave en posición k del nodo
        for (int i = k; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1)); // desplaza claves a la izquierda
            node.childs.set(i + 1, node.childs.get(i + 2)); // desplaza hijos a la izquierda
        }
        node.keys.set(node.count - 1, null); // limpia la última posición de clave
        node.childs.set(node.count, null); // limpia la última posición de hijo
        node.count--; // decrementa el número de claves
    }

    private BNode<E> findParent(BNode<E> current, BNode<E> target) { // busca el padre de target en el subárbol current
        if (current == null) return null; // si llegó a nulo, no encontró
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) == target) return current; // padre encontrado
            BNode<E> result = findParent(current.childs.get(i), target); // busca en el hijo
            if (result != null) return result; // retorna si encontró en una rama
        }
        return null; // no encontró el padre
    }

    private int findParentIdx(BNode<E> root, BNode<E> target) { // obtiene el índice del hijo target en su padre
        BNode<E> parent = findParent(root, target); // busca el padre primero
        if (parent == null) return -1; // si no hay padre, retorna -1
        for (int i = 0; i <= parent.count; i++)
            if (parent.childs.get(i) == target) return i; // retorna el índice donde está target
        return -1; // no debería ocurrir, pero retorna -1 si no lo encuentra
    }

    //TOSTRING / WRITETREE
    private static class NodeEntry<E> { // clase auxiliar para construir la impresión por niveles
        BNode<E> node; // nodo actual
        int parentId; // id del nodo padre
        NodeEntry(BNode<E> node, int parentId) { // constructor de NodeEntry
            this.node = node; // asigna el nodo
            this.parentId = parentId; // asigna el id del padre
        }
    }

    @Override
    public String toString() { // devuelve la representación en texto del árbol
        if (isEmpty()) return "BTree is empty..."; // si está vacío, retorna mensaje
        return writeTree(this.root); // escribe el árbol completo a texto
    }

    private String writeTree(BNode<E> current) { // construye la representación en texto nivel por nivel
        StringBuilder sb = new StringBuilder(); // buffer para la salida
        java.util.Queue<NodeEntry<E>> queue = new java.util.LinkedList<>(); // cola para recorrido BFS
        queue.add(new NodeEntry<>(current, -1)); // agrega la raíz con padre inexistente
        sb.append(String.format("%-8s %-20s %-15s %s%n",
                "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos")); // encabezado de tabla
        sb.append("-".repeat(60)).append("\n"); // línea separadora
        while (!queue.isEmpty()) { // recorre todos los nodos en anchura
            NodeEntry<E> entry = queue.poll(); // saca la entrada de la cola
            BNode<E> node = entry.node; // nodo actual
            StringBuilder keysStr = new StringBuilder("("); // construye representación de claves
            for (int i = 0; i < node.count; i++) {
                keysStr.append(node.keys.get(i)); // agrega cada clave
                if (i < node.count - 1) keysStr.append(", "); // separa con coma
            }
            keysStr.append(")"); // cierra la lista de claves
            String parentStr = (entry.parentId == -1) ? "--" : "[" + entry.parentId + "]"; // id del padre o --
            StringBuilder childsStr = new StringBuilder("["); // construye representación de hijos
            boolean hasChild = false; // indica si el nodo tiene hijos
            for (int i = 0; i <= node.count; i++) {
                BNode<E> child = node.childs.get(i); // obtiene cada hijo
                if (child != null) {
                    if (hasChild) childsStr.append(", "); // separa con coma si ya hay otro hijo
                    childsStr.append(child.idNode); // agrega id del hijo
                    hasChild = true; // marca que tiene hijos
                    queue.add(new NodeEntry<>(child, node.idNode)); // encola el hijo para imprimirlo después
                }
            }
            if (!hasChild) childsStr.append("--"); // si no hay hijos, muestra --
            childsStr.append("]"); // cierra la lista de hijos
            sb.append(String.format("%-8d %-20s %-15s %s%n",
                    node.idNode, keysStr, parentStr, childsStr)); // agrega la línea de salida para este nodo
        }
        return sb.toString(); // retorna la representación completa
    }
    // =========================================================================
    //  NUEVOS MÉTODOS REQUERIDOS PARA LA INTERFAZ GRÁFICA (MINIMARKET)
    // =========================================================================

    // Método GET: A diferencia de search (que retorna boolean), este retorna el objeto completo
    public E get(E cl) {
        return getRec(this.root, cl);
    }

    private E getRec(BNode<E> current, E cl) {
        if (current == null) return null; // No lo encontró, retorna null
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        
        if (found) {
            return current.keys.get(pos[0]); // Retorna el objeto completo almacenado en el nodo
        }
        return getRec(current.childs.get(pos[0]), cl); // Sigue buscando en los hijos
    }

    // Método GET ALL: Recorre el árbol (In-Order) y devuelve una lista con todos los elementos
    public java.util.List<E> getAll() {
        java.util.List<E> lista = new java.util.ArrayList<>();
        getAllRec(this.root, lista);
        return lista;
    }

    private void getAllRec(BNode<E> current, java.util.List<E> lista) {
        if (current == null) return;
        
        int i;
        for (i = 0; i < current.count; i++) {
            getAllRec(current.childs.get(i), lista); // Baja por el hijo izquierdo
            lista.add(current.keys.get(i));          // Guarda la clave actual en la lista
        }
        getAllRec(current.childs.get(i), lista);     // Baja por el último hijo (el extremo derecho)
    }
}