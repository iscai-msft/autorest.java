/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.bodybyte.implementation;

import fixtures.bodybyte.AutoRestSwaggerBATByteService;
import fixtures.bodybyte.Bytes;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.ServiceClient;
import com.microsoft.rest.RestClient;
import rx.Single;

/**
 * Initializes a new instance of the AutoRestSwaggerBATByteService class.
 */
public class AutoRestSwaggerBATByteServiceImpl extends ServiceClient implements AutoRestSwaggerBATByteService {

    /**
     * The Bytes object to access its operations.
     */
    private Bytes bytes;

    /**
     * Gets the Bytes object to access its operations.
     * @return the Bytes object.
     */
    public Bytes bytes() {
        return this.bytes;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATByteService client.
     *
     * @param baseUrl the base URL of the host
     */
    public AutoRestSwaggerBATByteServiceImpl(String baseUrl) {
        super(baseUrl);
        initialize();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATByteService client.
     *
     */
    public AutoRestSwaggerBATByteServiceImpl() {
        this("http://localhost");
        initialize();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATByteService client.
     *
     * @param restClient the REST client containing pre-configured settings
     */
    public AutoRestSwaggerBATByteServiceImpl(RestClient restClient) {
        super(restClient);
        initialize();
    }

    private void initialize() {
        this.bytes = new BytesImpl(this);
    }
}