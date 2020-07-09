
public class PartValue implements Comparable<PartValue> {

	private String string;
	private int freq;
	private int weight;

	public PartValue(String string, int freq) {
		super();
		this.string = string;
		this.freq = freq;
		this.weight = (string.length() - 2) * freq - (string.length() + 3);
	}

	public String getString() {
		return string;
	}

	public int getFreq() {
		return freq;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(PartValue o) {
		return ((PartValue) o).getWeight() - weight;
	}

}
