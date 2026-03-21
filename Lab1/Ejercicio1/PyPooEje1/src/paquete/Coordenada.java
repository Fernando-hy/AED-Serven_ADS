package paquete;

// Clase base con atributos privados para garantizar encapsulamiento.
public class Coordenada{
	private double x;
	private double y;
	
	// Constructor por defecto. Uso de 'this' para referenciar la instancia.
	public Coordenada(){
		this.x=0;
		this.y=0;
	}
	// Constructor parametrizado.
	public Coordenada(double x, double y ){
		this.x=x;
		this.y=y;
	}
	// Constructor copia.
	public Coordenada(Coordenada c ){
		this.x=getX();
		this.y=getY();
	}
	// Métodos mutadores (setters).
	void setX(double x) {
	 this.x=x;
	}
	void setY(double y){
	 this.y=y;
	}
	// Métodos accesores (getters).
	double getX(){
	 return this.x;
	}
	double getY(){
	 return this.y;
	}
	// Método de instancia. Opera con los datos del propio objeto ('this').
	double distancia(Coordenada c){
		double difx=this.x-c.getX();
		double dify=this.y-c.getY();
		double distancia= Math.pow(Math.pow(difx,2)+Math.pow(dify,2),1/2);
		return distancia;
	}
	// Método de clase (estático). No requiere instancia, opera con los parámetros.
	static double distancia(Coordenada c1, Coordenada c2){
		double difx= c1.getX()-c2.getX();
		double dify= c1.getY()-c2.getY();
		double distancia= Math.pow(Math.pow(difx,2)+Math.pow(dify,2),1/2);
		return distancia;
	}
	// Representación en cadena del objeto.
	public String toString(){
		return (x+","+y);
	}
}
