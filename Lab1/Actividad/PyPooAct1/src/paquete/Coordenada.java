package paquete;

// Define un punto 2D aplicando abstracción.
public class Coordenada{
	// Atributos privados para asegurar el encapsulamiento.
	private double x;
	private double y;
	
	// Constructor por defecto. Inicializa atributos propios usando 'this'.
	public Coordenada(){
		this.x=0;
		this.y=0;
	}
	// Constructor con parámetros. Uso de 'this' para evitar ambigüedad con los parámetros.
	public Coordenada(double x, double y ){
		this.x=x;
		this.y=y;
	}
	// Constructor copia. Crea un objeto a partir del estado de otro.
	public Coordenada(Coordenada c ){
		this.x=c.getX();
		this.y=c.getY();
	}
	// Métodos mutadores (setters) para modificar el estado encapsulado.
	void setX(double x) {
		this.x=x;
	}
	void setY(double y){
		this.y=y;
	}
	// Métodos accesores (getters) para consultar el estado.
	double getX(){
	 return this.x;
	}
	double getY(){
	 return this.y;
	}
	// Método de instancia. Opera sobre el objeto actual ('this') y otro recibido.
	double distancia(Coordenada c){
		double difx=this.x-c.getX();
		double dify=this.y-c.getY();
		double distancia= Math.pow(Math.pow(difx,2)+Math.pow(dify,2),1/2);
		return distancia;
	}
	// Método de clase (estático). Pertenece a la clase, no requiere instanciación previa.
	static double distancia(Coordenada c1, Coordenada c2){
		double difx= c1.getX()-c2.getX();
		double dify= c1.getY()-c2.getY();
		double distancia= Math.pow(Math.pow(difx,2)+Math.pow(dify,2),1/2);
		return distancia;
	}
	// Devuelve el estado del objeto en formato texto.
	public String toString(){
		return (x+","+y);
	}
}