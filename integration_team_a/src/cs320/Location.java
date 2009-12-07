import java.awt.Point;

@SuppressWarnings("serial")
public class Location extends Point{

	public Location() {
		super();
	}
	
	public Location(int X, int Y) {
		super(X, Y);
	}
	
	public Location(Location L) {
		super(L);
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
}