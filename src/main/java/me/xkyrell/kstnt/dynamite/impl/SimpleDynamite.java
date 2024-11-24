package me.xkyrell.kstnt.dynamite.impl;

import me.xkyrell.kstnt.dynamite.AbstractDynamite;
import me.xkyrell.kstnt.dynamite.DynamiteIcon;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.logic.BlockLogic;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.List;

import static me.xkyrell.kstnt.util.DataContainers.*;
import static me.xkyrell.kstnt.TNTConstants.DATA_KEY;

public class SimpleDynamite extends AbstractDynamite implements BlockLogic {

    public SimpleDynamite(String name, DynamiteIcon icon, List<Attribute<?>> attributes) {
        super(name, icon, attributes);
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        PersistentDataContainer container = block.getChunk().getPersistentDataContainer();
        if (!matchesBlock(block)) {
            return;
        }

        event.setDropItems(false);
        container.remove(getKeyByBlock(block));

        GameMode gameMode = event.getPlayer().getGameMode();
        if (!gameMode.equals(GameMode.SURVIVAL)) {
            return;
        }

        Location center = block.getLocation().toCenterLocation();
        block.getWorld().dropItem(center, getIcon().compose());
    }

    @Override
    public void onIgnite(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof TNTPrimed tnt)) {
            return;
        }

        Location location = tnt.getLocation().toBlockLocation();
        Block block = location.getBlock();

        PersistentDataContainer container = block.getChunk().getPersistentDataContainer();
        String name = container.get(getKeyByBlock(block), PersistentDataType.STRING);
        if (!getName().equals(name)) {
            return;
        }

        PersistentDataContainer entityContainer = tnt.getPersistentDataContainer();
        entityContainer.set(DATA_KEY, PersistentDataType.STRING, name);
        container.remove(getKeyByBlock(block));
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        ItemStack hand = event.getItemInHand();
        PersistentDataContainer container = hand.getItemMeta().getPersistentDataContainer();
        String name = container.get(DATA_KEY, PersistentDataType.STRING);
        if (!getName().equals(name)) {
            return;
        }

        Chunk chunk = event.getBlock().getChunk();
        PersistentDataContainer blockContainer = chunk.getPersistentDataContainer();
        blockContainer.set(getKeyByBlock(event.getBlock()), PersistentDataType.STRING, name);
    }

    @Override
    public final boolean matchesBlock(Block block) {
        PersistentDataContainer container = block.getChunk().getPersistentDataContainer();
        return hasKeyOrDefault(container, getKeyByBlock(block));
    }
}
