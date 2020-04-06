package net.bnbdiscord.bnbmsg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommandExecutor implements CommandExecutor {
    BnbMsgPlugin plugin;

    public ReplyCommandExecutor(BnbMsgPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        CommandSender target = plugin.replyTargets.get(sender);
        if (target == null) {
            sender.sendMessage("" + ChatColor.BOLD + ChatColor.RED + "NOT FOUND!" + ChatColor.RESET + ChatColor.RED +
                    " I can't find anyone to reply to");
            return true;
        }

        String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", args));
        sender.sendMessage(ChatColor.GRAY + "To " + ChatColor.RESET + (target instanceof Player ?
                ((Player) target).getDisplayName() : sender.getName()) + ChatColor.GRAY + " » " + ChatColor.RESET + message);
        target.sendMessage(ChatColor.GRAY + "From " + ChatColor.RESET + (sender instanceof Player ?
                ((Player) sender).getDisplayName() : sender.getName()) + ChatColor.GRAY + " » " + ChatColor.RESET + message);
        plugin.replyTargets.put(target, sender);
        return true;
    }
}
