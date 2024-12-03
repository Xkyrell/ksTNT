package me.xkyrell.kstnt;

import me.xkyrell.kstnt.dynamite.attribute.AbstractAttribute;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeTest {

    private Attribute<Integer> attribute;

    @BeforeEach
    void setUp() {
        attribute = new TestAttribute(1);
    }

    @Test
    void testAttribute() {
        assertEquals(attribute.getName(), "test");
        assertEquals(attribute.getValue(), 1);
    }

    @Test
    void testValueGetterAndSetter() {
        attribute.setValue(3);
        assertEquals(attribute.getValue(), 3);

        attribute.setValue(97);
        assertEquals(attribute.getValue(), 97);
    }

    private static class TestAttribute extends AbstractAttribute<Integer> {

        public TestAttribute(Integer value) {
            super("test", value);
        }
    }
}
