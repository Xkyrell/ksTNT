package me.xkyrell.kstnt.dynamite;

import lombok.*;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public abstract class AbstractDynamite implements Dynamite {

    private final String name;
    private final DynamiteIcon icon;
    private final List<Attribute<?>> attributes;

    @Setter
    @Getter(AccessLevel.NONE)
    private Predicate<PersistentDataContainer> dynamiteValidator;

    public boolean isCustomDynamite(@NonNull PersistentDataContainer container) {
        return dynamiteValidator.test(container);
    }
}
