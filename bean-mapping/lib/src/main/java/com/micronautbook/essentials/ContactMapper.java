package com.micronautbook.essentials;

import io.micronaut.context.annotation.Mapper;

public interface ContactMapper {
    @Mapper.Mapping(
            to = "name",
            from = "#{c.firstName} #{c.lastName()}"
    )
    ContactCard contactToContactCard(Contact c);
}
