package model.value;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * A value object representing an image (a logo, an avatar, etc)
 */
@Value
@Builder
public class Image {
    private final UUID imageId;
    private final String path;
}

