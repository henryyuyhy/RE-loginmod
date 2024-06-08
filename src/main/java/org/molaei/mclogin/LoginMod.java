package org.molaei.mclogin;

import org.molaei.mclogin.commands.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;

public class LoginMod implements ModInitializer {
    static GetPlayer getPlayer = new GetPlayer();

    @Override
    public void onInitialize() {
        RegisteredPlayersJson.read();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            LoginCommand.register(dispatcher);
            RegisterCommand.register(dispatcher);
            RetrievePasswordCommand.register(dispatcher);
        });
    }

    public static PlayerLogin getPlayer(ServerPlayerEntity player) {
        return getPlayer.get(player);
    }
}
