package com.locationsapi.entity;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = PRIVATE, force = true)
public class Event<T> {

    private final UUID id;

    private final OffsetDateTime createdAt;

    @Valid
    @NotNull(message = "content is mandatory")
    private final T content;

    public Event(final T content) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.createdAt = OffsetDateTime.now();
    }
}
