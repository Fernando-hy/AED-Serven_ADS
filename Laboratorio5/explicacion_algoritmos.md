# Caso Spotify: Clase `ColaReproduccion`

Este sistema utiliza una **Lista Doblemente Enlazada** para permitir la navegación en ambos sentidos (atrás/adelante).

---

## 1. Atributos de la Clase
```java
4 private NodeDoble<T> first = null;  // Inicio de la cola
5 private NodeDoble<T> finall = null; // Fin de la cola
6 private NodeDoble<T> actual = null; // Canción que suena ahora
```
- **first/finall**: Permiten saber dónde empieza y acaba la cola.
- **actual**: Es vital para saber qué canción estamos escuchando.

---

## 2. Agregar Canción (`agregarCancion`)
```java
11 public void agregarCancion(T cancion) {
12     NodeDoble<T> newNode = new NodeDoble(cancion);
13     if (this.first == null) { // Caso 1: Lista vacía
14         this.first = newNode;
15         this.finall = newNode;
16         this.actual = newNode;
17     } else { // Caso 2: Ya hay canciones
18         newNode.setPrev(this.finall); // El nuevo apunta atrás al viejo último
19         this.finall.setNext(newNode); // El viejo último apunta adelante al nuevo
20         this.finall = newNode;        // El nuevo es ahora el último
21     }
22 }
```
- **Doble enlace**: En la línea 18 y 19 se crean los dos puentes entre el nodo viejo y el nuevo para que la conexión sea bidireccional.

---

## 3. Navegación (`reproducirSiguiente` / `reproducirAnterior`)
```java
27 this.actual = this.actual.getNext(); // Mueve el puntero hacia adelante
...
37 this.actual = this.actual.getPrev(); // Mueve el puntero hacia atrás
```
- Gracias a que cada nodo conoce a su anterior (`prev`), podemos retroceder instantáneamente sin tener que volver a empezar desde el principio de la lista.

---

## 4. El Mezclador Shuffle (`mezclar`)
Este método implementa el algoritmo de **Fisher-Yates**.
1. **Líneas 48-50**: Cuenta cuántas canciones hay (n).
2. **Líneas 53-59**: Crea un Array y copia los valores de los nodos en él.
3. **Líneas 63-68 (Fisher-Yates)**: 
   - Recorre el Array de atrás hacia adelante.
   - Intercambia el elemento actual con uno aleatorio anterior. Esto garantiza que la mezcla sea 100% justa y aleatoria.
4. **Líneas 72-75**: Copia los valores ya mezclados de vuelta a los nodos de la lista.

---

## 5. Estadísticas de Tiempo (`duracionTotal`)
```java
102 if (temp.getValue() instanceof Cancion) {
103     total += ((Cancion)temp.getValue()).getDuracionSeg();
104 }
```
- **instanceof**: Comprueba que el objeto dentro del nodo sea realmente una canción.
- Va sumando los segundos de cada una mientras recorre la lista.
- **Línea 111**: Usa matemáticas simples (`/ 60` para minutos y `% 60` para segundos restantes) para mostrar el tiempo como en Spotify (ej: 03:45).

---

## Resumen del Nodo Doble
Cada "perla" (nodo) de esta cadena tiene **dos hilos**:
- Uno que va hacia la derecha (`next`).
- Uno que va hacia la izquierda (`prev`).
Esto es lo que permite que el botón "Anterior" de tu reproductor funcione.
