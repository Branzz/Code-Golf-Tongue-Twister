
public class Solution implements Comparable<Solution> {
	
	private String[] parts;
	private int size;
	private String sol;
	private final String TEXT = "She sells seashells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if she sells seashells on the seashore,\nThen I'm sure she sells seashore shells.";

	public Solution(int type, String...parts) {

		for (int i = 0; i < parts.length - 1; i++)
			for (int j = 0; j < parts.length - i - 1; j++)
				if (TEXT.indexOf(parts[j]) > TEXT.indexOf(parts[j + 1])) { 
					String temp = parts[j]; 
					parts[j] = parts[j+1]; 
					parts[j+1] = temp; 
				}
		this.parts = parts;
		String t = TEXT;

		switch (type) {
		case TongueTwister.JAVA : {
			for (int i = 0; i < parts.length; i++)
				for (int j = 0, count = 0; j < t.length() - parts[i].length(); j++)
					if (t.substring(j, parts[i].length() + j).compareTo(parts[i]) == 0)
						t = t.replaceFirst(parts[i], count++ > 0 ? "%" + (i + 1) + "\\$\\x" : "\\%\\x");
			t += "\"";
			for (String part : parts)
				t += ",\"" + part + "\"";
			sol = "v->\"\".format(\"" + t.replaceAll("\n", "\\\\n").replaceAll("x", "s") + ")";
			break;
		}
		case TongueTwister.C_SHARP : {
			for (int i = 0; i < parts.length; i++)
					t = t.replaceAll(parts[i], "{" + i + "}");
			t += "\"";
			for (String part : parts)
				t += ",\"" + part + "\"";
			sol = "Write(@\"" + t + ")";
			break;
		}
		case TongueTwister.PYTHON3 : { //replace them with corresponding symbol
			for (int i = 0; i < parts.length; i++)
				t = t.replaceAll(parts[i], "" + i);
			t += "\".translate([\"";

			if (parts.length > 0)
				t += parts[0] + "\"";
			for (int i = 1; i < parts.length; i++)
				t += ",\"" + parts[i] + "\"";
			sol = "print(\"" + t.replaceAll("\n", "\\\\n").replaceAll("0", "" + parts.length) + "]*2))";
			break;
		}
		case TongueTwister.PYTHON2 : {
			for (int i = 0; i < parts.length; i++)
				t = t.replaceAll(parts[i], "{" + i + "}");
		t += "\".format(\"";

		if (parts.length > 0)
			t += parts[0] + "\"";
		for (int i = 1; i < parts.length; i++)
			t += ",\"" + parts[i] + "\"";
		sol = "print\"" + t.replaceAll("\n", "\\\\n") + ")";
		break;
		}
		}

	}

	public int getSize() {
		return size;
	}

	public String getSol() {
		return sol;
	}

	public String[] getParts() {
		return parts;
	}

	public boolean equals(Solution solution) {
		if (solution.getParts().length != parts.length)
			return false;
		for (int i = 0; i < parts.length; i++)
			if (solution.getParts()[i].compareTo(parts[i]) != 0)
				return false;
		return true;
	}

	@Override
	public int compareTo(Solution solution) {
		String compressed1 = "";
		String compressed2 = "";
		for (String part : parts)
			compressed1 += part;
		for (String part : solution.getParts())
			compressed2 += part;

		return compressed1.compareTo(compressed2);
	}
//
}
