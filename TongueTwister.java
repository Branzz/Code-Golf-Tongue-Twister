import java.util.ArrayList;
import java.util.Arrays;

public class TongueTwister {

	interface N {
		String c(Void v);
	}

	  static N n = 
v->"".format("S%sells by%s shells she sells are seashells, I'm sure.\nSo if s%1$sells on%2$sn I'm sure s%1$sore shells.","he sells seash"," the seashore,\nThe")
;

	public static void main(String[] args) {

//		Print the code automatically of given replacements:
//		System.out.println(new Solution("he sells ","seash","ells","\nThe", " I'm sure").getSol());

		solutions = new ArrayList<Solution>();

//		solutions.add(new Solution());

		String[] used = new String[0];
		
		//For languages that can't use \n, input the text as an array split by the lines. TEXT.split("\n")
		if (VERSION == Solution.C_SHARP)
			search(used, TEXT.split("\n"));
		else
			search(used, TEXT);

	}

	//Change versions here: "JAVA", "C_SHARP", "PYTHON3"
	private final static int VERSION = Solution.JAVA;

	private final static String TEXT = "She sells seashells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if she sells seashells on the seashore,\nThen I'm sure she sells seashore shells.";

	private static ArrayList<Solution> solutions; //All solutions (Purpose: to check for repeat and not recheck entire branches)

	private static ArrayList<Solution> shortest; //List of the current shortest solution(s)
	private static int minSize = 10000; //Shortest solution(s) size

	public static void search(String[] used, String...text) {

		Solution solution = new Solution(VERSION, used);
		for (Solution s : solutions)
			if (s.compareTo(solution) == 0) {
				return;
			}
		//else: continue

		solutions.add(solution);

		//Check if shortest solution
		if (solution.getSol().length() < minSize) {
			minSize = solution.getSol().length();
			shortest = new ArrayList<Solution>();
			shortest.add(solution);
			System.out.println(minSize + ":\n" + shortest.get(0).getSol());
			System.out.println();
		}
		else if(solution.getSol().length() == minSize) {
			minSize = solution.getSol().length();
			shortest.add(solution);
			System.out.println(minSize + ":");
			for (Solution sol : shortest)
				System.out.println(sol.getSol());
			System.out.println();
		}

		ArrayList<PartValue> bank = getBank(text); //Possible parts to use
		//Go through all possible parts and use them
		for (PartValue pv : bank) {
			String[] usedAdd = new String[used.length + 1];
			int i = 0;
			for (; i < used.length; i++)
				usedAdd[i] = used[i];
			usedAdd[i] = pv.getString();
//			System.out.println(solution.getSol().length() + " " + solution.getSol());
			ArrayList<String> textParts = new ArrayList<String>();
			for (String line : text)
				textParts.addAll(Arrays.asList(line.split(pv.getString())));
			String[] text0 = textParts.toArray(new String[0]);
			search(usedAdd, text0);
		}
	}

	public static ArrayList<PartValue> getBank(String...text) {

		//Find all possible parts of words
		ArrayList<String> parts = new ArrayList<String>();
		for (int line = 0; line < text.length; line++)
			for (int l = 0; l < text[line].length() - 1; l++)
				for (int r = l + 1; r < text[line].length(); r++)
					parts.add(text[line].substring(l, r));

		//Convert the words to PartValue objects
		ArrayList<PartValue> bank = new ArrayList<PartValue>();
		while (parts.size() != 0) {
			String part = parts.remove(0);
			int i = 0, frequency = 1;
			while (i < parts.size()) {
				if (parts.get(i).compareTo(part) == 0) {
					parts.remove(i);
					frequency++;
				}
				else
					i++;
			}
			bank.add(new PartValue(VERSION, part, frequency));
		}

		//To avoid checking paths that make a potentially longer or equal solution
		ArrayList<PartValue> rangedBank = new ArrayList<PartValue>();
		for (PartValue pv : bank)
			if (pv.getString().length() > 2 && pv.getFreq() > 1 && pv.getWeight() > 0) 
				rangedBank.add(pv);

//		QuicksortArray<PartValue> q = new QuicksortArray<PartValue>();
//		q.sort(rangedBank);
		new QuicksortArray<PartValue>().sort(rangedBank);

		return rangedBank;
	}

}
