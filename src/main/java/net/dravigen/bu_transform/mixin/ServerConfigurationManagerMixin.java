package net.dravigen.bu_transform.mixin;

import net.dravigen.bu_transform.api.ToolHelper;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.LinkedList;

@Mixin(ServerConfigurationManager.class)
public class ServerConfigurationManagerMixin {
	@Inject(method = "playerLoggedIn", at = @At("HEAD"))
	private void createLists(EntityPlayerMP mp, CallbackInfo ci) {
		ToolHelper.redoPlayersMap.put(mp, new ArrayList<>());
		ToolHelper.undoPlayersMap.put(mp, new ArrayList<>());
		ToolHelper.copyBlockPlayersMap.put(mp, new LinkedList<>());
		ToolHelper.copyEntityPlayersMap.put(mp, new ArrayList<>());
		ToolHelper.pos1PlayersMap.put(mp, null);
		ToolHelper.pos2PlayersMap.put(mp, null);
	}
	
	@Inject(method = "playerLoggedOut", at = @At("HEAD"))
	private void resetPosWhenLeaving(EntityPlayerMP mp, CallbackInfo ci) {
		ToolHelper.pos1PlayersMap.put(mp, null);
		ToolHelper.pos2PlayersMap.put(mp, null);
	}
}
