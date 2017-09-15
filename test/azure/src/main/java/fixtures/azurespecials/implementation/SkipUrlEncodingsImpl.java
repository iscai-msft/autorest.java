/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.azurespecials.implementation;

import com.microsoft.rest.v2.RestProxy;
import fixtures.azurespecials.SkipUrlEncodings;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.HeaderParam;
import com.microsoft.rest.v2.annotations.Headers;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PathParam;
import com.microsoft.rest.v2.annotations.QueryParam;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.v2.http.HttpClient;
import fixtures.azurespecials.models.ErrorException;
import java.io.IOException;
import rx.functions.Func1;
import rx.Observable;
import rx.Single;

/**
 * An instance of this class provides access to all the operations defined
 * in SkipUrlEncodings.
 */
public class SkipUrlEncodingsImpl implements SkipUrlEncodings {
    /** The RestProxy service to perform REST calls. */
    private SkipUrlEncodingsService service;
    /** The service client containing this operation class. */
    private AutoRestAzureSpecialParametersTestClientImpl client;

    /**
     * Initializes an instance of SkipUrlEncodingsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public SkipUrlEncodingsImpl(AutoRestAzureSpecialParametersTestClientImpl client) {
        this.service = RestProxy.create(SkipUrlEncodingsService.class, client.restClient().baseURL(), client.httpClient(), client.serializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for SkipUrlEncodings to be
     * used by Retrofit to perform actually REST calls.
     */
    interface SkipUrlEncodingsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getMethodPathValid" })
        @GET("azurespecials/skipUrlEncoding/method/path/valid/{unencodedPathParam}")
        Single<Void> getMethodPathValid(@PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getPathPathValid" })
        @GET("azurespecials/skipUrlEncoding/path/path/valid/{unencodedPathParam}")
        Single<Void> getPathPathValid(@PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getSwaggerPathValid" })
        @GET("azurespecials/skipUrlEncoding/swagger/path/valid/{unencodedPathParam}")
        Single<Void> getSwaggerPathValid(@PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getMethodQueryValid" })
        @GET("azurespecials/skipUrlEncoding/method/query/valid")
        Single<Void> getMethodQueryValid(@QueryParam(value = "q1", encoded = true) String q1, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getMethodQueryNull" })
        @GET("azurespecials/skipUrlEncoding/method/query/null")
        Single<Void> getMethodQueryNull(@QueryParam(value = "q1", encoded = true) String q1, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getPathQueryValid" })
        @GET("azurespecials/skipUrlEncoding/path/query/valid")
        Single<Void> getPathQueryValid(@QueryParam(value = "q1", encoded = true) String q1, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: fixtures.azurespecials.SkipUrlEncodings getSwaggerQueryValid" })
        @GET("azurespecials/skipUrlEncoding/swagger/query/valid")
        Single<Void> getSwaggerQueryValid(@QueryParam(value = "q1", encoded = true) String q1, @HeaderParam("accept-language") String acceptLanguage, @HeaderParam("User-Agent") String userAgent);

    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getMethodPathValid(String unencodedPathParam) {
        getMethodPathValidAsync(unencodedPathParam).toBlocking().value();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getMethodPathValidAsync(String unencodedPathParam, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getMethodPathValidAsync(unencodedPathParam), serviceCallback);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getMethodPathValidAsync(String unencodedPathParam) {
        if (unencodedPathParam == null) {
            throw new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null.");
        }
        return service.getMethodPathValid(unencodedPathParam, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getPathPathValid(String unencodedPathParam) {
        getPathPathValidAsync(unencodedPathParam).toBlocking().value();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getPathPathValidAsync(String unencodedPathParam, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getPathPathValidAsync(unencodedPathParam), serviceCallback);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getPathPathValidAsync(String unencodedPathParam) {
        if (unencodedPathParam == null) {
            throw new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null.");
        }
        return service.getPathPathValid(unencodedPathParam, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getSwaggerPathValid() {
        getSwaggerPathValidAsync().toBlocking().value();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getSwaggerPathValidAsync(final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getSwaggerPathValidAsync(), serviceCallback);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getSwaggerPathValidAsync() {
        final String unencodedPathParam = "path1/path2/path3";
        return service.getSwaggerPathValid(unencodedPathParam, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getMethodQueryValid(String q1) {
        getMethodQueryValidAsync(q1).toBlocking().value();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getMethodQueryValidAsync(String q1, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getMethodQueryValidAsync(q1), serviceCallback);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getMethodQueryValidAsync(String q1) {
        if (q1 == null) {
            throw new IllegalArgumentException("Parameter q1 is required and cannot be null.");
        }
        return service.getMethodQueryValid(q1, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded query parameter with value null.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getMethodQueryNull() {
        getMethodQueryNullAsync().toBlocking().value();
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getMethodQueryNullAsync(final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getMethodQueryNullAsync(), serviceCallback);
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link Void} object if successful.
     */
    public Single<Void> getMethodQueryNullAsync() {
        final String q1 = null;
        return service.getMethodQueryNull(q1, this.client.acceptLanguage(), this.client.userAgent());
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getMethodQueryNull(String q1) {
        getMethodQueryNullAsync(q1).toBlocking().value();
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getMethodQueryNullAsync(String q1, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getMethodQueryNullAsync(q1), serviceCallback);
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getMethodQueryNullAsync(String q1) {
        return service.getMethodQueryNull(q1, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getPathQueryValid(String q1) {
        getPathQueryValidAsync(q1).toBlocking().value();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getPathQueryValidAsync(String q1, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getPathQueryValidAsync(q1), serviceCallback);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getPathQueryValidAsync(String q1) {
        if (q1 == null) {
            throw new IllegalArgumentException("Parameter q1 is required and cannot be null.");
        }
        return service.getPathQueryValid(q1, this.client.acceptLanguage(), this.client.userAgent());
    }


    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void getSwaggerQueryValid() {
        getSwaggerQueryValidAsync().toBlocking().value();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getSwaggerQueryValidAsync(final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getSwaggerQueryValidAsync(), serviceCallback);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Single<Void> getSwaggerQueryValidAsync() {
        final String q1 = "value1&q2=value2&q3=value3";
        return service.getSwaggerQueryValid(q1, this.client.acceptLanguage(), this.client.userAgent());
    }


}