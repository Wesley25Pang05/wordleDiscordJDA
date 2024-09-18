import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Wordle extends ListenerAdapter {
	public String[] wordList = {"about","above","abuse","actor","acute","admit","adopt","adult","after","again","agent","agree","ahead","alarm","album","alert","alike","alive","allow","alone","along","alter","among","anger","angle","angry","apart","apple","apply","arena","argue","arise","array","aside","asset","audio","audit","avoid","award","aware","badly","baker","bases","basic","basis","beach","began","begin","begun","being","below","bench","billy","birth","black","blame","blind","block","blood","board","boost","booth","bound","brain","brand","bread","break","breed","brief","bring","broad","broke","brown","build","built","buyer","cable","calif","carry","catch","cause","chain","chair","chart","chase","cheap","check","chest","chief","child","china","chose","civil","claim","class","clean","clear","click","clock","close","coach","coast","could","count","court","cover","craft","crash","cream","crime","cross","crowd","crown","curve","cycle","daily","dance","dated","dealt","death","debut","delay","depth","doing","doubt","dozen","draft","drama","drawn","dream","dress","drill","drink","drive","drove","dying","eager","early","earth","eight","elite","empty","enemy","enjoy","enter","entry","equal","error","event","every","exact","exist","extra","faith","false","fault","fiber","field","fifth","fifty","fight","final","first","fixed","flash","fleet","floor","fluid","focus","force","forth","forty","forum","found","frame","frank","fraud","fresh","front","fruit","fully","funny","giant","given","glass","globe","going","grace","grade","grand","grant","grass","great","green","gross","group","grown","guard","guess","guest","guide","happy","harry","heart","heavy","hence","henry","horse","hotel","house","human","ideal","image","index","inner","input","issue","japan","jimmy","joint","jones","judge","known","label","large","laser","later","laugh","layer","learn","lease","least","leave","legal","level","lewis","light","limit","links","lives","local","logic","loose","lower","lucky","lunch","lying","magic","major","maker","march","maria","match","maybe","mayor","meant","media","metal","might","minor","minus","mixed","model","money","month","moral","motor","mount","mouse","mouth","movie","music","needs","never","newly","night","noise","north","noted","novel","nurse","occur","ocean","offer","often","order","other","ought","paint","panel","paper","party","peace","peter","phase","phone","photo","piece","pilot","pitch","place","plain","plane","plant","plate","point","pound","power","press","price","pride","prime","print","prior","prize","proof","proud","prove","queen","quick","quiet","quite","radio","raise","range","rapid","ratio","reach","ready","refer","right","rival","river","robin","roger","roman","rough","round","route","royal","rural","scale","scene","scope","score","sense","serve","seven","shall","shape","share","sharp","sheet","shelf","shell","shift","shirt","shock","shoot","short","shown","sight","since","sixth","sixty","sized","skill","sleep","slide","small","smart","smile","smith","smoke","solid","solve","sorry","sound","south","space","spare","speak","speed","spend","spent","split","spoke","sport","staff","stage","stake","stand","start","state","steam","steel","stick","still","stock","stone","stood","store","storm","story","strip","stuck","study","stuff","style","sugar","suite","super","sweet","table","taken","taste","taxes","teach","teeth","terry","texas","thank","theft","their","theme","there","these","thick","thing","think","third","those","three","threw","throw","tight","times","tired","title","today","topic","total","touch","tough","tower","track","trade","train","treat","trend","trial","tried","tries","truck","truly","trust","truth","twice","under","undue","union","unity","until","upper","upset","urban","usage","usual","valid","value","video","virus","visit","vital","voice","waste","watch","water","wheel","where","which","while","white","whole","whose","woman","women","world","worry","worse","worst","worth","would","wound","write","wrong","wrote","yield","young","youth"};
	ArrayList<MemberManagement> members = new ArrayList<MemberManagement>();
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		System.out.println(event.getMessage().getContentRaw());
		boolean is = false;
		for (int i = 0; i < members.size(); i++) {
			if (event.getMember().getId().equals(members.get(i).user)) {
				wordle(event, i);
				is = true;
				break;
			}
		}
		if (!is && event.getMessage().getContentRaw().toLowerCase().indexOf("play wordle") == 0) {
			String word = wordList[(int) (Math.random() * wordList.length)];
			members.add(new MemberManagement(word, event.getMember().getId()));
			System.out.println(word + " in the server " + event.getGuild().getName() + " in channel " + event.getChannel().getName() + " by the user " + event.getMember().getEffectiveName());
			event.getChannel().sendMessage("Guess a word by saying **guess (word)**").complete();
		}
		else {
		    System.out.println("error");
		}
	}
	
	public void wordle(GuildMessageReceivedEvent event, int user) {
		String message = event.getMessage().getContentRaw().toLowerCase();
		MemberManagement player = new MemebrManagement()
		if (player.wordle && message.length() == 11 && message.indexOf("guess ") == 0) {
			player.tries++;
			player.prev += player.tries + ":  " + check(message.substring(6), player).toUpperCase();
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Wordle");
			embed.setDescription(player.prev + "\n\nWord is: ");
			for (String i : player.correct) {
				embed.setDescription(embed.getDescriptionBuilder() + i);
			}
			embed.setDescription(embed.getDescriptionBuilder() + "\nUnused Letters: ");
			for (String i : player.unused) {
				embed.setDescription(embed.getDescriptionBuilder() + i);
			}
			embed.setFooter("STRIKETHROUGH IS WRONG\nITALIC IS WRONG SPOT\nBOLD IS CORRECT");
			player.prev += "\n";
			event.getChannel().sendMessage(embed.build()).complete();
		}
		if (player.win.length() == 5 || player.tries == 6 || message.equals("end")) {
			EmbedBuilder embed = new EmbedBuilder();
			if (player.win.length() == 5) { embed.setTitle("You win!"); } else { embed.setTitle("You lose!"); }
			embed.setDescription("Word was: " + player.word);;
			event.getChannel().sendMessage(embed.build()).complete();
			members.remove(user);
		}
	}
	
	public String check(String guess, MemberManagement player) {
		String replacement = "";
		player.win = "";
		for (int i = 0; i < 5; i++) {
			if (player.word.charAt(i) == guess.charAt(i)) {
				replacement += "**" + guess.charAt(i) + "** ";
				player.win += guess.charAt(i);
				player.correct[i] = "" + guess.charAt(i);
			}
			else if (player.word.indexOf(guess.charAt(i)) > -1) {
				replacement += "*" + guess.charAt(i) + "* ";
			}
			else {
				replacement += "~~" + guess.charAt(i) + "~~ ";
			}
			for (int u = 0; u < player.unused.length; u++) {
				if (player.unused[u].equals(guess.charAt(i) + "")) {
					player.unused[u] = "";
				}
			}
		}
		return replacement;
	}
}