package me.xkyrell.kstnt.dynamite;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import javax.annotation.Nullable;
import java.util.List;

public interface Dynamite {

    @NonNull
    String getName();

    @NonNull
    DynamiteIcon getIcon();

    @Nullable
    List<Attribute<?>> getAttributes();

}
