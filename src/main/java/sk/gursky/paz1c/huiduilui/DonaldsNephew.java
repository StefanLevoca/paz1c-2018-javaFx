package sk.gursky.paz1c.huiduilui;
import java.util.HashMap;
import java.util.Map;

public enum DonaldsNephew {
	HUI("red"),
	DUI("blue"),
	LUI("green");
	
	private String color;
	private Map<String, String> names;
	
	private DonaldsNephew(String color) {
		this.color = color;
		names = new HashMap<>();
		switch (color) {
		case "red":
			names.put("sk", "Hui");
			names.put("it", "Qui");
			names.put("pg", "Huguinho");
			break;
		case "blue":
			names.put("sk", "Dui");
			names.put("it", "Quo");
			names.put("pg", "Zezinho");
			break;
		case "green":
			names.put("sk", "Lui");
			names.put("it", "Qua");
			names.put("pg", "Luisinho");
			break;
		default:
			break;
		}
	}
	
	public String getName(String language) {
		return names.get(language);
	}
	
	public String getColor() {
		return color;
	}
	
	public static DonaldsNephew getByName(String name) {
		for (DonaldsNephew nephew : values()) {
			if (nephew.names.containsValue(name)) {
				return nephew;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("Lui po portugalsky: " + DonaldsNephew.getByName("Lui").getName("pg"));
	}
}
