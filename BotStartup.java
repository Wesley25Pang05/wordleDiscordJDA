import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EventListener;

public class BotStartup {
	public static void main(String[] args) {
		String key = "PLACE KEY HERE";
		JDABuilder wordle = JDABuilder.createDefault(key).enableIntents(GatewayIntent.MESSAGE_CONTENT);
		wordle.setActivity(Activity.watching("people !play wordle")); wordle.setStatus(OnlineStatus.DO_NOT_DISTURB);
		wordle.addEventListeners(new Wordle());
		wordle.build();
	}
}