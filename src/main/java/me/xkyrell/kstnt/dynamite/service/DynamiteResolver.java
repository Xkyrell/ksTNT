package me.xkyrell.kstnt.dynamite.service;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface DynamiteResolver {

    Optional<Dynamite> resolve(@NonNull String name);

    Collection<? extends Dynamite> getDynamites();

    default <T extends DynamiteLogic> Stream<? extends T> getDynamitesByLogic(Class<T> clazz) {
        return getDynamites().stream()
                .filter(clazz::isInstance)
                .map(clazz::cast);
    }
}
