package org.jacob.spigot.plugins.DungeonCore.utils;

import com.google.common.collect.Multimap;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;

import java.util.List;
import java.util.Map;

public class ItemStackBuilder {
    private ItemStack stack;

    public ItemStackBuilder(Material material) {
        stack = new ItemStack(material);
    }

    public ItemStackBuilder amount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder name(String name) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder lore(List<String> lore) {
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        stack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemStackBuilder enchant(Map<Enchantment, Integer> enchantments) {
        stack.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemStackBuilder skullOwner(OfflinePlayer player) {
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwningPlayer(player);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder potion(PotionData data, Color color) {
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setBasePotionData(data);
        meta.setColor(color);
        this.stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder attribute(Multimap<Attribute, AttributeModifier> attributes) {
        ItemMeta meta = stack.getItemMeta();
        meta.setAttributeModifiers(attributes);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder attribute(Attribute attribute, AttributeModifier modifier) {
        ItemMeta meta = stack.getItemMeta();
        meta.addAttributeModifier(attribute, modifier);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder hideFlags(ItemFlag... flags) {
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(flags);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder unbreakable(boolean unbreakable) {
        ItemMeta meta = stack.getItemMeta();
        meta.setUnbreakable(unbreakable);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder customModelData(int data) {
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(data);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return stack;
    }
}
