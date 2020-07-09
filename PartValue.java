
public class PartValue implements Comparable<PartValue> {

	private String string;
	private int freq;
	private int weight; //Predicted amount it will decrease the code

	public PartValue(int type, String string, int freq) {
		super();
		this.string = string;
		this.freq = freq;

		// how much it improves - how much it takes to put in
		//(part length - low end of how much it replaces) * how often it appears - (length + amount of extra to put in)
		//			(word - $s)														,"word"

		switch (type) {
		case Solution.JAVA :
			this.weight = (string.length() - 2) * freq - (string.length() + 3); //2 or 4
			break;
		case Solution.C_SHARP :
			this.weight = (string.length() - 3) * freq - (string.length() + 3);
			break;
		case Solution.PYTHON3 :
			this.weight = (string.length() - 1) * freq - (string.length() + 3);
			break;
		}
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
