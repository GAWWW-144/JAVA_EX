package baisc.inheritance.ex1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorPoint extends Point{
	
	
	public ColorPoint(int x, int y, String color) {
		super(x, y, color);
		this.color = color;
	}

	private String color;
	
	public String tostring() {
		return "ColorPoint (" + color +")" + (" + super.toString() + ");
	}
		
	}


