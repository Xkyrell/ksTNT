package me.xkyrell.kstnt;

import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.impl.SimpleDynamite;
import me.xkyrell.kstnt.dynamite.service.DynamiteResolver;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import me.xkyrell.kstnt.dynamite.service.impl.SimpleDynamiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DynamiteServiceTest {

    private static final String KEY = "test";

    private DynamiteService dynamiteService;
    private Dynamite dynamite;

    @BeforeEach
    void setUp() {
        dynamiteService = new SimpleDynamiteService();
        dynamite = new SimpleDynamite(KEY, null, Collections.emptyList());
    }

    @Test
    void testRegister() {
        dynamiteService.register(KEY, dynamite);

        DynamiteResolver resolver = dynamiteService.getResolver();
        Optional<Dynamite> resolvedDynamite = resolver.resolve(KEY);

        assertTrue(resolvedDynamite.isPresent());
        assertEquals(dynamite, resolvedDynamite.get());
        assertEquals(0, resolver.getDynamites().size());
    }

    @Test
    void testUnregister() {
        dynamiteService.register(KEY, dynamite);
        dynamiteService.unregister(KEY);

        DynamiteResolver resolver = dynamiteService.getResolver();
        Optional<Dynamite> resolvedDynamite = resolver.resolve(KEY);

        assertFalse(resolvedDynamite.isPresent());
    }

    @Test
    void testGetResolver() {
        DynamiteResolver resolver = dynamiteService.getResolver();

        assertNotNull(resolver);
        assertEquals(0, resolver.getDynamites().size());
    }
}
