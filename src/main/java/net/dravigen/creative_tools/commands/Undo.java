package net.dravigen.creative_tools.commands;

import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.StatCollector;

import java.util.ArrayList;

import static net.dravigen.creative_tools.api.ToolHelper.*;

public class Undo extends CommandBase {
	@Override
	public String getCommandName() {
		return "undo";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender) {
		return "/undo [number]";
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] strings) {
		int num = strings.length == 1 ? Integer.parseInt(strings[0]) : 1;
		
		if (!undoList.isEmpty()) {
			QueueInfo queueInfo = undoList.get(undoList.size() - 1);
			undoList.remove(undoList.size() - 1);
			
			if (num > 1) {
				redoList.add(new QueueInfo("redo",
										   new ArrayList<>(queueInfo.selection()),
										   duplicateSavedList(queueInfo.redoList()),
										   duplicateSavedList(queueInfo.editList()),
										   duplicateSavedList(queueInfo.redoList()),
										   new int[SAVED_NUM],
										   queueInfo.player()));
				
				for (int i = 0; i < num; i++) {
					if (!undoList.isEmpty()) {
						QueueInfo toMerge = undoList.get(undoList.size() - 1);
						mergeQueue(queueInfo, toMerge);
						
						undoList.remove(undoList.size() - 1);
						redoList.add(new QueueInfo("redo",
												   new ArrayList<>(toMerge.selection()),
												   duplicateSavedList(toMerge.redoList()),
												   duplicateSavedList(toMerge.editList()),
												   duplicateSavedList(toMerge.redoList()),
												   new int[SAVED_NUM],
												   toMerge.player()));
						
					}
				}
				
				editList.add(queueInfo);
				
				sendEditMsg(sender,
							StatCollector.translateToLocal("commands.prefix") +
									String.format(StatCollector.translateToLocal("commands.undoMul"), num));
			}
			else {
				editList.add(queueInfo);
				redoList.add(new QueueInfo("redo",
										   new ArrayList<>(queueInfo.selection()),
										   duplicateSavedList(queueInfo.redoList()),
										   duplicateSavedList(queueInfo.editList()),
										   duplicateSavedList(queueInfo.redoList()),
										   new int[SAVED_NUM],
										   queueInfo.player()));
				
				sendEditMsg(sender,
							StatCollector.translateToLocal("commands.prefix") +
									String.format(StatCollector.translateToLocal("commands.undo")));
			}
			
			
		}
		else {
			sendErrorMsg(sender, StatCollector.translateToLocal("commands.error.undo"));
		}
	}
}
