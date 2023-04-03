package model.type;

/**
 * Supported PEGI classifications
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Supported PEGI classifications
 */
@Getter
@RequiredArgsConstructor
public enum PegiType {
    /**
     * PEGI 3 classification
     */
    PEGI_3(3),
    /**
     * PEGI 7 classification
     */
    PEGI_7(7),
    /**
     * PEGI 12 classification
     */
    PEGI_12(12),
    /**
     * PEGI 16 classification
     */
    PEGI_16(16),
    /**
     * PEGI 18 classification
     */
    PEGI_18(18);

    private final int minimalAge;
}

