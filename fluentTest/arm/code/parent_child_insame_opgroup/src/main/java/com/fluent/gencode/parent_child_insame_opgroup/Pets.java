/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.parent_child_insame_opgroup;

import rx.Observable;
import com.fluent.gencode.parent_child_insame_opgroup.Dog;
import rx.Completable;
import com.fluent.gencode.parent_child_insame_opgroup.Puppy;

/**
 * Type representing Pets.
 */
public interface Pets {
    /**
     * Begins definition for a new Dog resource.
     * @param name resource name.
     * @return the first stage of the new Dog definition.
     */
    Dog.DefinitionStages.Blank defineDog(String name);

    /**
     * Begins definition for a new Puppy resource.
     * @param name resource name.
     * @return the first stage of the new Puppy definition.
     */
    Puppy.DefinitionStages.Blank definePuppy(String name);

    /**
     * Gets information about a dog.
     *
     * @param resourceGroupName resource group name
     * @param name resource name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<Dog> getByResourceGroupAsync(String resourceGroupName, String name);

    /**
     * Lists all the dogs under a resource group.
     *
     * @param resourceGroupName resource group name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<Dog> listByResourceGroupAsync(String resourceGroupName);

    /**
     * Lists all the dogs under a subscription.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<Dog> listAsync();

    /**
     * Deletes a dog.
     *
     * @param resourceGroupName resource group name
     * @param name resource name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable deleteDogAsync(String resourceGroupName, String name);

    /**
     * Grants access to a dog.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @param grantAccessData Access data object supplied in the body of the get dog access operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<AccessUri> grantAccessToDogAsync(String resourceGroupName, String dogName, GrantAccessData grantAccessData);

    /**
     * Revokes access to a dog.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable revokeAccessToDogAsync(String resourceGroupName, String dogName);

    /**
     * Gets information about a puppy.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @param puppyName The name of the puppy that is being created. The name can't be changed after the puppy is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The max name length is 80 characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<Puppy> getPuppyAsync(String resourceGroupName, String dogName, String puppyName);

    /**
     * Lists cats under a dog.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<Puppy> listPuppiesByDogAsync(final String resourceGroupName, final String dogName);

    /**
     * Deletes a puppy.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @param puppyName The name of the puppy that is being created. The name can't be changed after the puppy is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The max name length is 80 characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable deletePuppyAsync(String resourceGroupName, String dogName, String puppyName);

    /**
     * Grants access to a puppy.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @param puppyName The name of the puppy that is being created. The name can't be changed after the puppy is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The max name length is 80 characters.
     * @param grantAccessData Access data object supplied in the body of the get puppy access operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<AccessUri> grantAccessToPuppyAsync(String resourceGroupName, String dogName, String puppyName, GrantAccessData grantAccessData);

    /**
     * Revokes access to a puppy.
     *
     * @param resourceGroupName The name of the resource group.
     * @param dogName The name of the managed dog that is being created. The name can't be changed after the dog is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The maximum name length is 80 characters.
     * @param puppyName The name of the puppy that is being created. The name can't be changed after the puppy is created. Supported characters for the name are a-z, A-Z, 0-9 and _. The max name length is 80 characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Completable revokeAccessToPuppyAsync(String resourceGroupName, String dogName, String puppyName);

}
