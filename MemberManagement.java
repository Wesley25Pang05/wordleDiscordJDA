import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MemberManagement {
	
	boolean wordle = false; String win = ""; String word = ""; String prev = null; int tries = 0; String user;
	String[] correct = {"?", "?", "?", "?", "?"};
	String[] used = new String[26];
	String[] unused = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	String lastPlayed;

	public MemberManagement(String newWord, String person) {
		wordle = true;
		win = "";
		prev = "";
		tries = 0;
		word = newWord;
		user = person;
	}

	public String getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(MessageReceivedEvent event) {
		if (event.isFromGuild()) {
			lastPlayed = "#" + event.getChannel().getName() + " in " + event.getGuild().getName();
		}
		else {
			lastPlayed = "Direct Messages";
		}
	}
}