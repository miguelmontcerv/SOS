package rest1;

public class distanciaTesoro implements Comparable<distanciaTesoro> {
	private double distancia;
	private String id;
	
	public distanciaTesoro(double d,String id) {
		this.distancia=d;
		this.id=id;
	}
	public double getDistancia() {
		return distancia;
	}
	public String getId() {
		return id;
	}
	@Override public int compareTo(distanciaTesoro comparestu) {
		int compareage
        = (int) ((distanciaTesoro)comparestu).distancia;
		 return (int) (this.distancia- compareage);
		 }

}
