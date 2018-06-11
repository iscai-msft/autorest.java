/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.child_in_parent_opgroup;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for SkuNames.
 */
public final class SkuNames extends ExpandableStringEnum<SkuNames> {
    /** Static value Small for SkuNames. */
    public static final SkuNames SMALL = fromString("Small");

    /** Static value Medium for SkuNames. */
    public static final SkuNames MEDIUM = fromString("Medium");

    /** Static value Large for SkuNames. */
    public static final SkuNames LARGE = fromString("Large");

    /**
     * Creates or finds a SkuNames from its string representation.
     * @param name a name to look for
     * @return the corresponding SkuNames
     */
    @JsonCreator
    public static SkuNames fromString(String name) {
        return fromString(name, SkuNames.class);
    }

    /**
     * @return known SkuNames values
     */
    public static Collection<SkuNames> values() {
        return values(SkuNames.class);
    }
}
