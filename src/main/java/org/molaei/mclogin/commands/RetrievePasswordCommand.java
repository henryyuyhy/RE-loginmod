package org.molaei.mclogin.commands;

import com.sun.jna.WString;
import org.molaei.mclogin.LoginMod;
import org.molaei.mclogin.PlayerLogin;
import org.molaei.mclogin.RegisteredPlayersJson;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class RetrievePasswordCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("retrievepassword")
                .then(argument("playerName", StringArgumentType.word())
                    .then(argument("newPassword", StringArgumentType.word())
                        .executes(ctx -> {
                            String password = StringArgumentType.getString(ctx, "newPassword");
                            //ServerPlayerEntity player = ctx.getSource().getPlayer();
                            //String username = player.getEntityName();
                            String username = StringArgumentType.getString(ctx, "playerName");
                            if (ctx.getSource().getEntity() != null) {
                                ctx.getSource().sendFeedback(new LiteralText("This command can only be executed on server console"),false);
                                return -1;
                            }
                            RegisteredPlayersJson.retrieve(username,password);

                            return 1;
        }))));
    }
}
