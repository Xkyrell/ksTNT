package me.xkyrell.kstnt;

import me.xkyrell.kstnt.dynamite.DynamiteIcon;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.attribute.impl.RadiusAttribute;
import me.xkyrell.kstnt.dynamite.impl.SimpleDynamite;
import me.xkyrell.kstnt.util.DataContainers;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static me.xkyrell.kstnt.TNTConstants.DATA_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DynamiteTest {

    private static final String KEY = "test";
    private static final List<Attribute<?>> ATTRIBUTES = List.of(new RadiusAttribute(9.2));

    @Mock
    private Block block;

    @Mock
    private Player player;

    @Mock
    private Chunk chunk;

    @Mock
    private Location location;

    @Mock
    private World world;

    @Mock
    private DynamiteIcon icon;

    private SimpleDynamite dynamite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        lenient().when(block.getLocation()).thenReturn(location);
        lenient().when(location.getWorld()).thenReturn(world);
        lenient().when(block.getChunk()).thenReturn(chunk);

        dynamite = new SimpleDynamite(KEY, icon, ATTRIBUTES);
    }

    @Test
    void testDynamite() {
        assertEquals(dynamite.getName(), KEY);
        assertEquals(dynamite.getAttributes(), ATTRIBUTES);
        assertNotNull(dynamite.getIcon());
    }

    @Test
    void testOnBreakPlayerNotInSurvival() {
        BlockBreakEvent event = mock(BlockBreakEvent.class);
        PersistentDataContainer container = mock(PersistentDataContainer.class);
        when(chunk.getPersistentDataContainer()).thenReturn(container);

        when(event.getBlock()).thenReturn(block);
        when(event.getPlayer()).thenReturn(player);
        when(player.getGameMode()).thenReturn(GameMode.CREATIVE);
        when(container.get(DataContainers.getKeyByBlock(block), PersistentDataType.STRING)).thenReturn(KEY);

        dynamite.onBreak(event);

        verify(container).remove(DataContainers.getKeyByBlock(block));
    }

    @Test
    void testOnBreakPlayerInSurvival() {
        BlockBreakEvent event = mock(BlockBreakEvent.class);
        PersistentDataContainer container = mock(PersistentDataContainer.class);
        when(chunk.getPersistentDataContainer()).thenReturn(container);

        when(event.getBlock()).thenReturn(block);
        when(event.getPlayer()).thenReturn(player);
        when(block.getWorld()).thenReturn(world);
        when(player.getGameMode()).thenReturn(GameMode.SURVIVAL);
        when(container.get(DataContainers.getKeyByBlock(block), PersistentDataType.STRING)).thenReturn(KEY);

        ItemStack itemStack = mock(ItemStack.class);
        when(icon.compose()).thenReturn(itemStack);

        dynamite.onBreak(event);

        verify(world).dropItem(location.toCenterLocation(), itemStack);
        verify(container).remove(DataContainers.getKeyByBlock(block));
    }

    @Test
    public void testOnPlace() {
        BlockPlaceEvent event = mock(BlockPlaceEvent.class);
        ItemStack itemInHand = mock(ItemStack.class);
        ItemMeta itemMeta = mock(ItemMeta.class);
        PersistentDataContainer container = mock(PersistentDataContainer.class);

        when(event.getItemInHand()).thenReturn(itemInHand);
        when(itemInHand.getItemMeta()).thenReturn(itemMeta);
        when(itemMeta.getPersistentDataContainer()).thenReturn(container);
        when(container.get(any(), eq(PersistentDataType.STRING))).thenReturn(KEY);
        when(event.getBlock()).thenReturn(block);
        when(block.getChunk()).thenReturn(chunk);

        PersistentDataContainer blockContainer = mock(PersistentDataContainer.class);
        when(chunk.getPersistentDataContainer()).thenReturn(blockContainer);

        dynamite.onPlace(event);

        verify(blockContainer).set(eq(DataContainers.getKeyByBlock(block)), eq(PersistentDataType.STRING), eq(KEY));
    }

    @Test
    public void testOnIgnite() {
        EntitySpawnEvent event = mock(EntitySpawnEvent.class);
        TNTPrimed tnt = mock(TNTPrimed.class);
        PersistentDataContainer blockContainer = mock(PersistentDataContainer.class);
        PersistentDataContainer entityContainer = mock(PersistentDataContainer.class);

        when(event.getEntity()).thenReturn(tnt);
        when(tnt.getLocation()).thenReturn(mock(Location.class));
        when(tnt.getLocation().toBlockLocation()).thenReturn(mock(Location.class));
        when(tnt.getLocation().toBlockLocation().getBlock()).thenReturn(block);
        when(block.getChunk()).thenReturn(chunk);
        when(chunk.getPersistentDataContainer()).thenReturn(blockContainer);
        when(blockContainer.get(any(), eq(PersistentDataType.STRING))).thenReturn(KEY);
        when(tnt.getPersistentDataContainer()).thenReturn(entityContainer);

        dynamite.onIgnite(event);

        verify(entityContainer).set(eq(DATA_KEY), eq(PersistentDataType.STRING), eq(KEY));
        verify(blockContainer).remove(eq(DataContainers.getKeyByBlock(block)));
    }
}
