/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.parent_child_indiff_opgroup;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.fluent.gencode.parent_child_indiff_opgroup.implementation.Parent_Child_Indiff_OpGroupManager;
import com.fluent.gencode.parent_child_indiff_opgroup.implementation.AccessUriInner;

/**
 * Type representing AccessUri.
 */
public interface AccessUri extends HasInner<AccessUriInner>, HasManager<Parent_Child_Indiff_OpGroupManager> {
    /**
     * @return the accessSAS value.
     */
    String accessSAS();

}