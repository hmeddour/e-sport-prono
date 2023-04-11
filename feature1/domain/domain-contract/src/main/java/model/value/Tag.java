package model.value;

import lombok.Builder;
import lombok.Value;

/**
 * A value object representing a tag : a key/label pair
 */
@Value
@Builder
public class Tag {
    private final String key;
    private final String label;
}

