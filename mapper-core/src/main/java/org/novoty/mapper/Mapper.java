package org.novoty.mapper;

public interface Mapper<FROM, TO> {
    void map(FROM from, TO to);
}
