package me.xkyrell.kstnt.dynamite.service;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.Dynamite;

public interface DynamiteService {

    void register(@NonNull String name, @NonNull Dynamite dynamite);

    void unregister(@NonNull String name);

    DynamiteResolver getResolver();

}
