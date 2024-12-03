package me.xkyrell.kstnt;

import me.xkyrell.kstnt.dynamite.DynamiteIcon;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.attribute.impl.RadiusAttribute;
import me.xkyrell.kstnt.dynamite.impl.SimpleDynamite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DynamiteTest {

    private static final String KEY = "test";
    private static final List<Attribute<?>> ATTRIBUTES = List.of(new RadiusAttribute(9.2));

    private SimpleDynamite simpleDynamite;

    @BeforeEach
    void setUp() {
        simpleDynamite = new SimpleDynamite(KEY, mock(DynamiteIcon.class), ATTRIBUTES);
    }

    @Test
    void testDynamite() {
        assertEquals(simpleDynamite.getName(), KEY);
        List<?> attributes = simpleDynamite.getAttributes();
        if (attributes != null) {
            assertEquals(attributes.size(), 1);
            assertEquals(attributes, ATTRIBUTES);
            assertNotNull(simpleDynamite.getIcon());
        }
    }

    // TODO: other tests
}
