package ninjabrainbot;

import java.util.Locale;

import ninjabrainbot.data.statistics.ApproximatedDensity;
import ninjabrainbot.data.stronghold.StrongholdConstants;
import ninjabrainbot.gui.GUI;
import ninjabrainbot.gui.Splash;
import ninjabrainbot.io.KeyboardListener;
import ninjabrainbot.io.preferences.NinjabrainBotPreferences;
import ninjabrainbot.util.I18n;
import ninjabrainbot.util.Profiler;
import ninjabrainbot.util.Progress;

public class Main {

	public static final String VERSION = "1.4.0";
	public static NinjabrainBotPreferences preferences;

	public static void main(String[] args) {
		Progress.init(new Splash());
		Progress.setTask("Loading language", 0.02f);
		Profiler.start("Initialize language");
		System.out.println("lang info: " + I18n.get("lang"));
		
		Progress.setTask("Loading preferences", 0.03f);
		Profiler.stopAndStart("Initialize preferences");
		preferences = new NinjabrainBotPreferences();
		StrongholdConstants.updateStrongholdChunkCoord();
		
		Progress.setTask("Calculating approximated stronghold density", 0.04f);
		Profiler.stopAndStart("Calculate approximated density");
		ApproximatedDensity.init();
		
		Progress.setTask("Calculating approximated stronghold density", 0.07f);
		Profiler.stopAndStart("Register keyboard listener");
		KeyboardListener.preInit();
		
		Progress.startCompoundTask("", 1f);
		Profiler.stopAndStart("Initialize GUI");
		Locale.setDefault(Locale.US);
		new GUI();
		Progress.endCompoundTask();
		
		Profiler.stop();
		Profiler.print();
	}

}
