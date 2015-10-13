package org.novoty.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class ConfigurableMapper<FROM, TO> implements Mapper<FROM, TO> {

    protected Map<String, Mapping<FROM, TO, ?>> mappings = new HashMap<>();

    public <FT> void map(String name, Function<FROM, FT> extractor, BiConsumer<TO, FT> setter) {
        mappings.put(name, new Mapping<>(extractor, setter));
    }

    public void map(FROM from, TO to) {
        for (Mapping<FROM, TO, ?> mapping : mappings.values()) {
            mapping.writeValue(from, to);
        }
    }

    private static class Mapping<FROM, TO, FT>{

        private final Function<FROM, FT> extractor;
        private final BiConsumer<TO, FT> setter;

        public Mapping(Function<FROM, FT> extractor, BiConsumer<TO, FT> setter) {
            this.extractor = extractor;
            this.setter = setter;
        }

        void writeValue(FROM from, TO to) {
            setter.accept(to, extractor.apply(from));
        }
    }
}
