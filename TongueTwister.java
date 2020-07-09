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

		String T = "She sells seashells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if she sells seashells on the seashore,\nThen I'm sure she sells seashore shells.";

//		ArrayList<PartValue> bank = getBank(T);

//		System.out.println(new Solution("he sells ","seash","ells","\nThe", " I'm sure").getSol());

		solutions = new ArrayList<Solution>();

//		solutions.add(new Solution());

		String[] used = new String[0];
		search(used, T);
		
//	    System.out.print(n.c(null));
	}

	private static ArrayList<Solution> solutions;
	private static ArrayList<Solution> shortest;
	private static int minSize = 10000;
	public static void search(String[] used, String...text) {

		Solution solution = new Solution(used);
		//try with just given used, then search all used + extra word that's left (calculate what else you can use) - don't add if it's already in AND don't continue if already in(?)
		for (Solution s : solutions)
			if (s.compareTo(solution) == 0) {
				return;
			}
		//else: continue
//		int low = 0, high = solutions.size() - 1, index = 0;
//		while (low <= high) {
//			int mid = (low + high) / 2;
//			if (solutions.get(mid).compareTo(solution) < 0)
//				low = mid + 1;
//			else if (solutions.get(mid).compareTo(solution) > 0)
//				high = mid - 1;
//			else {
//				index = mid;
//				break;
//			}
//		}
//		solutions.add(index, solution);

		solutions.add(solution);
		if (solution.getSol().length() < minSize) {
			minSize = solution.getSol().length();
			shortest = new ArrayList<Solution>();
			shortest.add(solution);
			System.out.println(minSize + " " + shortest.get(0).getSol());
			System.out.println();
		}
		else if(solution.getSol().length() == minSize) {
			minSize = solution.getSol().length();
			shortest.add(solution);
			for (Solution sol : shortest)
			System.out.println(minSize + " " + sol.getSol());
			System.out.println();
		}
		ArrayList<PartValue> bank = getBank(text);
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
			//		solutions.add(solution.getSol());
	}

	public static ArrayList<PartValue> getBank(String...text) {
		ArrayList<String> parts = new ArrayList<String>();
		for (int line = 0; line < text.length; line++)
			for (int l = 0; l < text[line].length() - 1; l++)
				for (int r = l + 1; r < text[line].length(); r++)
					parts.add(text[line].substring(l, r));
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
			bank.add(new PartValue(part, frequency));
		}

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

}

//String a(){return"She sells seashells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if she sells seashells on the seashore,\nThen I'm sure she sells seashore shells.";}
//String l(){String b=" she sells ";return"She sells seashells by the seashore,\nThe shells"+b+"are seashells, I'm sure.\nSo if"+b+"seashells on the seashore,\nThen I'm sure"+b+"seashore shells.";}
//String o(){String b="he sells seashells ";return"S"+b+"by the seashore,\\nThe shells she sells are seashells, I'm sure.\\nSo if s"+b+"on the seashore,\\nThen I'm sure she sells seashore shells.";}
//String u(){String b=" she sells seash";return"She sells seashells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if"+b+"ells on the seashore,\nThen I'm sure"+b+"ore shells.";}
//String x(){String b="he sells seash";return"S"+b+"ells by the seashore,\nThe shells she sells are seashells, I'm sure.\nSo if s"+b+"ells on the seashore,\nThen I'm sure s"+b+"ore shells.";}
//String p(){String b="he sells sea",c="shells";return"S"+b+c+" by the seashore,\nThe "+c+" she sells are sea"+c+", I'm sure.\nSo if s"+b+c+" on the seashore,\nThen I'm sure s"+b+"shore "+c+".";}
//String f(){String[]b={"he sells sea","shells"};return"S"+b[0]+b[1]+" by the seashore,\nThe "+b[1]+" she sells are sea"+b[1]+", I'm sure.\nSo if s"+b[0]+b[1]+" on the seashore,\nThen I'm sure s"+b[0]+"shore "+b[1]+".";}
//String r(){String[]b={"he sells ","seashells"};return"S"+b[0]+b[1]+" by the seashore,\nThe shells s"+b[0]+"are "+b[1]+", I'm sure.\nSo if s"+b[0]+b[1]+" on the seashore,\nThen I'm sure s"+b[0]+"seashore "+b[1]+".";}
//String h(){String[]b={"he sells ","seashells","seashore"};return"S"+b[0]+b[1]+" by the "+b[2]+",\nThe shells s"+b[0]+"are "+b[1]+", I'm sure.\nSo if s"+b[0]+b[1]+" on the "+b[2]+",\nThen I'm sure s"+b[0]+b[2]+" "+b[1]+".";}
//String t(){String b="shells";return"She sells sea"+b+" by the seashore,\nThe "+b+" she sells are sea"+b+", I'm sure.\nSo if she sells sea"+b+" on the seashore,\nThen I'm sure she sells seashore "+b+".";}
//String m(){String b="shells ";return"She sells sea"+b+" by the seashore,\nThe "+b+" she sells are seashells, I'm sure.\nSo if she sells sea"+b+" on the seashore,\nThen I'm sure she sells seashore shells.";}