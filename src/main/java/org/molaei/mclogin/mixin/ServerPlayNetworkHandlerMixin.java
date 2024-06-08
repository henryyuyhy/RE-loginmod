package org.molaei.mclogin.mixin;

import org.molaei.mclogin.listeners.OnGameMessage;
import org.molaei.mclogin.listeners.OnPlayerAction;
import org.molaei.mclogin.listeners.OnPlayerMove;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onPlayerMove", at = @At("HEAD"), cancellable = true)
    public void onPlayerMove(PlayerMoveC2SPacket packet, CallbackInfo ci) {
        //noinspection ConstantConditions
        if (!OnPlayerMove.canMove((ServerPlayNetworkHandler) (Object) this)) {
            ci.cancel();
        }
    }
    
    @Inject(method = "onPlayerAction", at = @At("HEAD"), cancellable = true)
    public void onPlayerAction(PlayerActionC2SPacket packet, CallbackInfo ci) {
        //noinspection ConstantConditions
        if (!OnPlayerAction.canInteract((ServerPlayNetworkHandler) (Object) this)) {
            ci.cancel(); // TODO: breaking a block desyncs with server
        }
    }

    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    public void onGameMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        //noinspection ConstantConditions
        if (!OnGameMessage.canSendMessage((ServerPlayNetworkHandler) (Object) this, packet)) {
            ci.cancel();
        }
    }
}
