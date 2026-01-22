package net.dravigen.bu_transform;

import api.AddonHandler;
import api.BTWAddon;
import api.config.AddonConfig;
import net.dravigen.bu_transform.commands.*;

public class BU_Transform extends BTWAddon {
	public static BU_Transform instance;
	
	public static int SPEED;
	
	public BU_Transform() {
		super();
		instance = this;
	}
	
	@Override
	public void initialize() {
		AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		initCommands();
	}
	
	@Override
	public void registerConfigProperties(AddonConfig config) {
		config.registerInt("bu_tr.editSpeed", 100, "Number of blocks placed per tick during an edit.");
	}
	
	@Override
	public void handleConfigProperties(AddonConfig config) {
		SPEED = config.getInt("bu_tr.editSpeed");
	}
	
	private void initCommands() {
		registerAddonCommand(new Copy());
		registerAddonCommand(new Paste());
		registerAddonCommand(new Move());
		registerAddonCommand(new Cut());
		registerAddonCommand(new Stack());
		registerAddonCommand(new Remove());
		registerAddonCommand(new Pos1());
		registerAddonCommand(new Pos2());
		registerAddonCommand(new PosAll());
		registerAddonCommand(new Undo());
		registerAddonCommand(new Redo());
		registerAddonCommand(new Rotate());
		registerAddonCommand(new EditSpeed());
	}
}