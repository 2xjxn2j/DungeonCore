package org.jacob.spigot.plugins.DungeonCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import  org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jacob.spigot.plugins.DungeonCore.utils.ItemStackBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MuteGUICommand implements CommandExecutor, Listener {

    public static final Map<Player, String> targets = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("mutegui")) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "Only players can execute this command");
                return true;
            }
            Player p = (Player) commandSender;

            if(!p.hasPermission("dc.mute.gui")) {
                p.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            if(strings.length != 1) {
                p.sendMessage(ChatColor.RED + "Usage: /mutegui <player>");
                return true;
            }
            Player t = Bukkit.getPlayerExact(strings[0]);

            if(t == null) {
                p.sendMessage(ChatColor.RED + "That player does not exist!");
                return true;
            }

            targets.put((Player) p, strings[0]);

            Inventory mutegui = Bukkit.createInventory(null, 27, "Mutes");

            mutegui.setItem(10, new ItemStackBuilder(Material.GREEN_STAINED_GLASS_PANE)
                    .name(ChatColor.GREEN + "Severity 1")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "1 hour mute"
                    )).build());

            mutegui.setItem(11, new ItemStackBuilder(Material.YELLOW_STAINED_GLASS_PANE)
                    .name(ChatColor.YELLOW + "Severity 2")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "5 hour mute"
                    )).build());

            mutegui.setItem(12, new ItemStackBuilder(Material.ORANGE_STAINED_GLASS_PANE)
                    .name(ChatColor.GOLD + "Severity 3")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "12 hour mute"
                    )).build());

            mutegui.setItem(13, new ItemStackBuilder(Material.RED_STAINED_GLASS_PANE)
                    .name(ChatColor.RED + "Severity 4")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "48 hour mute"
                    )).build());

            mutegui.setItem(14, new ItemStackBuilder(Material.PURPLE_STAINED_GLASS_PANE)
                    .name(ChatColor.DARK_PURPLE + "Severity 5")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "30 day mute"
                    )).build());

            mutegui.setItem(16, new ItemStackBuilder(Material.BLACK_STAINED_GLASS_PANE)
                    .name(ChatColor.BLACK + "Severity 6")
                    .lore(Arrays.asList(
                            ChatColor.GRAY + "Perma Mute"
                    )).build());

            p.openInventory(mutegui);
        }

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        int slot = e.getSlot();

        Inventory mutereasons = Bukkit.createInventory(null, 9, "Reasons");

        mutereasons.setItem(0, new ItemStackBuilder(Material.SCUTE)
                .name(ChatColor.GREEN + "Spam")
                .build());

        mutereasons.setItem(1, new ItemStackBuilder(Material.BRICK)
                .name(ChatColor.RED + "Verbal Abuse/Harassment")
                .build());

        mutereasons.setItem(2, new ItemStackBuilder(Material.BOWL)
                .name(ChatColor.LIGHT_PURPLE + "Racism/Sexism")
                .build());

        mutereasons.setItem(3, new ItemStackBuilder(Material.BUCKET)
                .name(ChatColor.YELLOW + "Other")
                .build());

        mutereasons.setItem(6, new ItemStackBuilder(Material.BARRIER)
                .name(ChatColor.RED + "Exit")
                .build());

        mutereasons.setItem(5, new ItemStackBuilder(Material.BUCKET)
                .name(ChatColor.RED + "Go Back")
                .build());

        if(e.getView().getTitle().equalsIgnoreCase("Mutes")) {
            e.setCancelled(true);

            String f = targets.get((Player) e.getWhoClicked());
            Player t = Bukkit.getPlayerExact(f);

            Player p = (Player) e.getWhoClicked();

           if(slot == 10) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);

               if(slot == 0) {

                   targets.get((Player) e.getWhoClicked());

                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + t.getName() + " 1h " + "Spamming/Advertising");
                   p.sendMessage(ChatColor.GREEN + "Player " + ChatColor.YELLOW + t.getName() + ChatColor.GREEN +
                           " has been banned: " + ChatColor.YELLOW + "1 hour" + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Spamming/Advertising");
                   return;
               }

               if(slot == 1) {
                   targets.get((Player) e.getWhoClicked());

                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + t.getName() + " 1h " + "Verbal Abuse/Harassment");
                   p.sendMessage(ChatColor.GREEN + "Player " + ChatColor.YELLOW + t.getName() + ChatColor.GREEN +
                           " has been banned: " + ChatColor.YELLOW + "1 hour" + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Verbal Abuse/Harassment");
                   return;
               }

               if(slot == 2) {
                   targets.get((Player) e.getWhoClicked());

                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + t.getName() + " 1h " + "Racism/Sexism");
                   p.sendMessage(ChatColor.GREEN + "Player " + ChatColor.YELLOW + t.getName() + ChatColor.GREEN +
                           " has been banned: " + ChatColor.YELLOW + "1 hour" + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Racism/Sexism");
                   return;
               }

               if(slot == 3) {
                   targets.get((Player) e.getWhoClicked());

                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + t.getName() + " 1h " + "Breaking the Rules");
                   p.sendMessage(ChatColor.GREEN + "Player " + ChatColor.YELLOW + t.getName() + ChatColor.GREEN +
                           " has been banned: " + ChatColor.YELLOW + "1 hour" + ChatColor.GREEN + " for " + ChatColor.YELLOW + "Breaking the Rules");
                   return;
               }

           }

           if(slot == 11) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);
           }

           if(slot == 12) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);
           }

           if(slot == 13) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);
           }

           if(slot == 14) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);
           }

           if(slot == 16) {
               e.setCancelled(true);
               p.closeInventory();

               p.openInventory(mutereasons);
           }

           return;
        }
    }
}
