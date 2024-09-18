import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotStartup {
	public static void main(String[] args) throws LoginException {
		String key = "PASTE YOUR DISCORD BOT API KEY HERE";
		JDABuilder wordle = JDABuilder.createDefault(key);
		wordle.setActivity(Activity.watching("people play wordle!")); wordle.setStatus(OnlineStatus.DO_NOT_DISTURB);
		wordle.addEventListeners(new Wordle());
		wordle.build();
	}
}