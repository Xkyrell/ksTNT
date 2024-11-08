package me.xkyrell.kstnt.dynamite.service.impl;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.service.DynamiteResolver;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class SimpleDynamiteService implements DynamiteService {

    private final Map<String, Dynamite> dynamites = new HashMap<>();

    @Override
    public void register(@NonNull String name, @NonNull Dynamite dynamite) {
        Dynamite existing = dynamites.putIfAbsent(name, dynamite);
        Preconditions.checkState((existing == null), "TNT '%s' already exists, so it will not be added.", name);
    }

    @Override
    public void unregister(@NonNull String name) {
        dynamites.remove(name);
    }

    @Override
    public DynamiteResolver getResolver() {
        return new SimpleResolver(dynamites);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class SimpleResolver implements DynamiteResolver {

        private final Map<String, Dynamite> dynamites;

        @Override
        public Optional<Dynamite> resolve(@NonNull String name) {
            return Optional.ofNullable(dynamites.get(name));
        }

        @Override
        public Collection<? extends Dynamite> getDynamites() {
            return dynamites.values();
        }
    }
}
