/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.report.implementation;

import com.google.common.reflect.TypeToken;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceClient;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.Headers;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.QueryParam;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.v2.http.HttpClient;
import com.microsoft.rest.v2.http.HttpPipeline;
import fixtures.report.AutoRestReportService;
import fixtures.report.models.ErrorException;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.util.Map;

/**
 * Initializes a new instance of the AutoRestReportService class.
 */
public class AutoRestReportServiceImpl extends ServiceClient implements AutoRestReportService {
    /**
     * The proxy service to use to perform REST calls.
     */
    private AutoRestReportServiceService service;

    /**
     * Initializes an instance of AutoRestReportService client.
     */
    public AutoRestReportServiceImpl() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestReportService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestReportServiceImpl(HttpPipeline httpPipeline) {
        super(httpPipeline);
        this.service = RestProxy.create(AutoRestReportServiceService.class, httpPipeline);
    }

    /**
     * The interface defining all the services for AutoRestReportService to be
     * used by Retrofit to perform actually REST calls.
     */
    @Host("http://localhost:3000")
    interface AutoRestReportServiceService {
        @GET("report")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Map<String, Integer>>> getReport(@QueryParam("qualifier") String qualifier);
    }

    /**
     * Get test coverage report.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Map&lt;String, Integer&gt; object if successful.
     */
    public Map<String, Integer> getReport() {
        return getReportAsync().blockingGet();
    }

    /**
     * Get test coverage report.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Map<String, Integer>> getReportAsync(ServiceCallback<Map<String, Integer>> serviceCallback) {
        return ServiceFuture.fromBody(getReportAsync(), serviceCallback);
    }

    /**
     * Get test coverage report.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Map<String, Integer>> object
     */
    public Single<RestResponse<Void, Map<String, Integer>>> getReportWithRestResponseAsync() {
        final String qualifier = null;
        return service.getReport(qualifier);
    }

    /**
     * Get test coverage report.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Map<String, Integer>> object
     */
    public Maybe<Map<String, Integer>> getReportAsync() {
        return getReportWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, Map<String, Integer>>, Maybe<Map<String, Integer>>>() {
                public Maybe<Map<String, Integer>> apply(RestResponse<Void, Map<String, Integer>> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Map&lt;String, Integer&gt; object if successful.
     */
    public Map<String, Integer> getReport(String qualifier) {
        return getReportAsync(qualifier).blockingGet();
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Map<String, Integer>> getReportAsync(String qualifier, ServiceCallback<Map<String, Integer>> serviceCallback) {
        return ServiceFuture.fromBody(getReportAsync(qualifier), serviceCallback);
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Map<String, Integer>> object
     */
    public Single<RestResponse<Void, Map<String, Integer>>> getReportWithRestResponseAsync(String qualifier) {
        return service.getReport(qualifier);
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Map<String, Integer>> object
     */
    public Maybe<Map<String, Integer>> getReportAsync(String qualifier) {
        return getReportWithRestResponseAsync(qualifier)
            .flatMapMaybe(new Function<RestResponse<Void, Map<String, Integer>>, Maybe<Map<String, Integer>>>() {
                public Maybe<Map<String, Integer>> apply(RestResponse<Void, Map<String, Integer>> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }
}
