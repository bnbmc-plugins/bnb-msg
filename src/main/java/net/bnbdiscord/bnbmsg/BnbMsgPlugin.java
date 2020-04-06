package net.bnbdiscord.bnbmsg;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class BnbMsgPlugin extends JavaPlugin {
    Map<CommandSender, CommandSender> replyTargets = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("tell").setExecutor(new TellCommandExecutor(this));
        getCommand("reply").setExecutor(new ReplyCommandExecutor(this));
    }
}
