package net.dravigen.bu_transform.mixin;

import api.world.BlockPos;
import net.dravigen.bu_transform.api.PacketUtils;
import net.dravigen.bu_transform.api.ToolHelper;
import net.minecraft.src.*;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dravigen.bu_transform.api.ToolHelper.getBlockPlayerIsLooking;
import static net.dravigen.bu_transform.api.ToolHelper.sendEditMsg;

@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin extends EntityPlayer {
	
	@Unique
	boolean pressed = false;
	
	public EntityClientPlayerMPMixin(World par1World, String par2Str) {
		super(par1World, par2Str);
	}
	
	@Inject(method = "onUpdate", at = @At("HEAD"))
	private void update(CallbackInfo ci) {
		MovingObjectPosition block = getBlockPlayerIsLooking(this);
		
		ItemStack heldItem = this.getHeldItem();
		
		if (heldItem == null) return;
		
		if (heldItem.getItem() == Item.axeWood && this.capabilities.isCreativeMode) {
			String pos1 = "commands.pos1";
			String pos2 = "commands.pos2";
			
			BlockPos blockPos = null;
			
			if (block != null) {
				blockPos = new BlockPos(block.blockX, block.blockY, block.blockZ);
			}
			
			if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1) || Mouse.isButtonDown(2)) {
				if (!pressed && Minecraft.getMinecraft().currentScreen == null) {
					if (Mouse.isButtonDown(0)) {
						// left
						if (ToolHelper.pos1 != null && block == null || this.isSneaking()) {
							ToolHelper.pos1 = null;
							sendEditMsg(this, "commands.pos1.reset");
							PacketUtils.sendPosUpdate(3, this, false);
						}
						
						if (block != null) {
							ToolHelper.pos1 = blockPos;
							sendEditMsg(this, pos1, blockPos.x, blockPos.y, blockPos.z);
							PacketUtils.sendPosUpdate(1, this, false);
						}
					}
					else if (Mouse.isButtonDown(1)) {
						// right
						if (ToolHelper.pos2 != null && block == null || this.isSneaking()) {
							ToolHelper.pos2 = null;
							sendEditMsg(this, "commands.pos2.reset");
							PacketUtils.sendPosUpdate(4, this, false);
						}
						
						if (block != null) {
							ToolHelper.pos2 = blockPos;
							sendEditMsg(this, pos2, blockPos.x, blockPos.y, blockPos.z);
							PacketUtils.sendPosUpdate(2, this, false);
						}
					}
					
					
					if (Mouse.isButtonDown(2)) {
						// middle
						blockPos = new BlockPos(MathHelper.floor_double(this.posX),
												MathHelper.floor_double(this.posY),
												MathHelper.floor_double(this.posZ));
						if (this.isUsingSpecialKey()) {
							ToolHelper.pos2 = blockPos;
							sendEditMsg(this, pos2, blockPos.x, blockPos.y, blockPos.z);
							PacketUtils.sendPosUpdate(2, this, false);
						}
						else {
							ToolHelper.pos1 = blockPos;
							sendEditMsg(this, pos1, blockPos.x, blockPos.y, blockPos.z);
							PacketUtils.sendPosUpdate(1, this, false);
						}
					}
				}
				
				pressed = true;
			}
			else {
				pressed = false;
			}
		}
	}
}
