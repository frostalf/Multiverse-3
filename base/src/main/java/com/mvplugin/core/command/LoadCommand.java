package com.mvplugin.core.command;

import com.dumptruckman.minecraft.pluginbase.command.CommandContext;
import com.dumptruckman.minecraft.pluginbase.command.CommandInfo;
import com.dumptruckman.minecraft.pluginbase.messages.Message;
import com.dumptruckman.minecraft.pluginbase.minecraft.BasePlayer;
import com.dumptruckman.minecraft.pluginbase.permission.Perm;
import com.mvplugin.core.exceptions.WorldManagementException;
import com.mvplugin.core.plugin.MultiverseCore;
import com.mvplugin.core.util.Perms;
import org.jetbrains.annotations.NotNull;

import static com.mvplugin.core.util.Language.Command.Load.*;

@CommandInfo(
        primaryAlias = "load",
        desc = "Loads a Multiverse World that has been unloaded.",
        usage = "{NAME}",
        directlyPrefixedAliases = "load",
        min = 1,
        max = 1
)
public class LoadCommand extends MultiverseCommand {
    protected LoadCommand(@NotNull final MultiverseCore plugin) {
        super(plugin);
    }

    @Override
    public Perm getPerm() {
        return Perms.CMD_LOAD;
    }

    @NotNull
    @Override
    public Message getHelp() {
        return HELP;
    }

    @Override
    public boolean runCommand(@NotNull final BasePlayer sender, @NotNull final CommandContext context) {
        final String worldName = context.getString(0);

        try {
            getPlugin().getWorldManager().loadWorld(worldName);
            getMessager().message(sender, SUCCESS, worldName);
        } catch (final WorldManagementException e) {
            e.sendException(getMessager(), sender);
        }

        return true;
    }
}
