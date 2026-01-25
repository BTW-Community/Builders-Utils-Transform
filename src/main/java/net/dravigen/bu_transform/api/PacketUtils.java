package net.dravigen.bu_transform.api;

import api.world.BlockPos;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet250CustomPayload;

import java.io.*;

import static net.dravigen.bu_transform.api.ToolHelper.*;

public class PacketUtils {
	public static final String POS_SYNC = "BUT:pos";
	
	public static void sendPosUpdate(int pos, ICommandSender sender, boolean isServer) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			dos.writeInt(pos);
			
			if (pos == 1) {
				if (isServer) {
					BlockPos blockPos = pos1PlayersMap.get(sender);
					dos.writeInt(blockPos.x);
					dos.writeInt(blockPos.y);
					dos.writeInt(blockPos.z);
				}
				else {
					dos.writeInt(pos1.x);
					dos.writeInt(pos1.y);
					dos.writeInt(pos1.z);
				}
			}
			else if (pos == 2) {
				if (isServer) {
					BlockPos blockPos = pos2PlayersMap.get(sender);
					dos.writeInt(blockPos.x);
					dos.writeInt(blockPos.y);
					dos.writeInt(blockPos.z);
				}
				else {
					dos.writeInt(pos2.x);
					dos.writeInt(pos2.y);
					dos.writeInt(pos2.z);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload(POS_SYNC, bos.toByteArray());
		
		Minecraft.getMinecraft().getNetHandler().addToSendQueue(packet);
	}
	
	public static void handlePosUpdateS(Packet250CustomPayload packet, EntityPlayer player) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
			DataInputStream dis = new DataInputStream(bis);
			
			int pos = dis.readInt();
			
			if (pos == 1) {
				BlockPos posNew = new BlockPos(dis.readInt(), dis.readInt(), dis.readInt());
				pos1PlayersMap.put(player, posNew);
			}
			else if (pos == 2) {
				BlockPos posNew = new BlockPos(dis.readInt(), dis.readInt(), dis.readInt());
				pos2PlayersMap.put(player, posNew);
			}
			else if (pos == 3) {
				pos1PlayersMap.put(player, null);
			}
			else if (pos == 4) {
				pos2PlayersMap.put(player, null);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Environment(EnvType.CLIENT)
	public static void handlePosUpdateC(Packet250CustomPayload packet) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
			DataInputStream dis = new DataInputStream(bis);
			
			int pos = dis.readInt();
			BlockPos posNew = new BlockPos(dis.readInt(), dis.readInt(), dis.readInt());
			
			if (pos == 1) {
				pos1 = posNew;
			}
			else if (pos == 2) {
				pos2 = posNew;
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
