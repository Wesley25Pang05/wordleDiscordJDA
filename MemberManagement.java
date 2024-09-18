public class MemberManagement {
	
	boolean wordle = false; String win = ""; String word = ""; String prev = null; int tries = 0; String user;
	String[] correct = {"?", "?", "?", "?", "?"};
	String[] unused = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

	public MemberManagement(String newWord, String person) {
		wordle = true;
		win = "";
		prev = "";
		tries = 0;
		word = newWord;
		user = person;
	}
}