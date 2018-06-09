/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.groupable_resource_inheritance;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The source image used for creating the dog.
 */
public class ImageDogReference {
    /**
     * A relative uri containing either a Platform image Repository or user
     * image reference.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /**
     * If the dog is created from an image's data dog, this is an index that
     * indicates which of the data dogs in the image to use. For OS dogs, this
     * field is null.
     */
    @JsonProperty(value = "lun")
    private Integer lun;

    /**
     * Get the id value.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Set the id value.
     *
     * @param id the id value to set
     * @return the ImageDogReference object itself.
     */
    public ImageDogReference withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the lun value.
     *
     * @return the lun value
     */
    public Integer lun() {
        return this.lun;
    }

    /**
     * Set the lun value.
     *
     * @param lun the lun value to set
     * @return the ImageDogReference object itself.
     */
    public ImageDogReference withLun(Integer lun) {
        this.lun = lun;
        return this;
    }

}
