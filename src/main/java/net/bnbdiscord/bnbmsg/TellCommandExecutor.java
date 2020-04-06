package net.bnbdiscord.bnbmsg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TellCommandExecutor implements CommandExecutor {
    BnbMsgPlugin plugin;

    public TellCommandExecutor(BnbMsgPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("" + ChatColor.BOLD + ChatColor.RED + "NOT FOUND!" + ChatColor.RESET + ChatColor.RED +
                    " I can't find that player.");
            return true;
        }

        String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 1,
                args.length)));
        sender.sendMessage(ChatColor.GRAY + "To " + ChatColor.RESET + target.getDisplayName() + ChatColor.GRAY
                + " » " + ChatColor.RESET + message);
        target.sendMessage(ChatColor.GRAY + "From " + ChatColor.RESET + (sender instanceof Player ?
                ((Player) sender).getDisplayName() : sender.getName()) + ChatColor.GRAY + " » " + ChatColor.RESET + message);
        plugin.replyTargets.put(target, sender);
        return true;
    }
}
