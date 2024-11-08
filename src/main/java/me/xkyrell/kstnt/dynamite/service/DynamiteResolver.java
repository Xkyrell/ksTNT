package me.xkyrell.kstnt.dynamite.service;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.Dynamite;
import java.util.Collection;
import java.util.Optional;

public interface DynamiteResolver {

    Optional<Dynamite> resolve(@NonNull String name);

    Collection<? extends Dynamite> getDynamites();

}
