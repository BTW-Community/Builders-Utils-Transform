package net.dravigen.bu_transform.mixin;

import net.dravigen.bu_transform.api.PacketUtils;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin {
	@Inject(method = "handleCustomPayload", at = @At("HEAD"))
	private void onCustomPayloadC2S(Packet250CustomPayload packet, CallbackInfo ci) {
		if (packet.channel.equals(PacketUtils.POS_SYNC)) {
			PacketUtils.handlePosUpdateC(packet);
		}
	}
}

