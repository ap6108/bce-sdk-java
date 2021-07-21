/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vca.model.AnalyzeResponse;
import com.baidubce.services.vca.model.ImageAnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultRequest;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.AnalyzeRequest;
import com.baidubce.services.vca.model.QuerySubTaskRequest;
import com.baidubce.services.vca.model.QuerySubTaskResponse;
import com.baidubce.services.vca.model.FaceLibCreate;
import com.baidubce.services.vca.model.FaceArtAdd;
import com.baidubce.services.vca.model.FaceLibsResults;
import com.baidubce.services.vca.model.FaceLibRequest;
import com.baidubce.services.vca.model.FaceLibResultItem;
import com.baidubce.services.vca.model.FaceLibImageResults;
import com.baidubce.services.vca.model.FaceLibBriefResults;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.BceErrorResponse;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Vca client.
 *
 * @author houshunwei
 */
public class VcaClient extends AbstractBceClient {

    private static final String VERSION = "v2";
    private static final String MEDIA = "media";
    private static final String IMAGE = "image";

    private static HttpResponseHandler[] vcaHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public VcaClient() {
        this(new BceClientConfiguration());
    }

    public VcaClient(BceClientConfiguration config) {
        super(config, vcaHandlers);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyze(request);
    }

    /**
     * Initiate media analyze for specified source and title.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @param title Media title.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(String source, String title) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        request.setTitle(title);
        return analyze(request);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param request Analyze request, including media source path.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, MEDIA);
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    /**
     * Initiate image analyze for specified source.
     *
     * @param source image source path, supporting BOS, HTTP(S) URL.
     * @return ImageAnalyzeResponse with analyze results.
     */

    public ImageAnalyzeResponse analyzeImage(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyzeImage(request);
    }

    /**
     * Initiate image analyze for specified source and title.
     *
     * @param source image source path, supporting BOS, HTTP(S) URL.
     * @param title image title.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImage(String source, String title) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        request.setTitle(title);
        return analyzeImage(request);
    }

    /**
     * Initiate image analyze for specified AnalyzeRequest and request image sync-interface.
     *
     * @param request Analyze request, including image source path.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImage(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, IMAGE);
        internalRequest.addParameter("sync", "");
        return this.invokeHttpClient(internalRequest, ImageAnalyzeResponse.class);
    }

    /**
     * Query analyze result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return Analyze result.
     */
    public QueryResultResponse queryResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        return queryResult(request);
    }

    /**
     * Query analyze result for specified source.
     *
     * @param request Query request, including media source path.
     * @return Analyze result.
     */
    public QueryResultResponse queryResult(QueryResultRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }

    /**
     * Query sub task result for specified source of directed type.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @param type Sub task type.
     * @return Analyze result of sub task type.
     */
    public QuerySubTaskResponse querySubTask(String source, String type) {
        QuerySubTaskRequest request = new QuerySubTaskRequest();
        request.setSource(source);
        request.setSubTaskType(type);
        return querySubTask(request);
    }

    /**
     * Query sub task result for specified source of directed type.
     *
     * @param request Query request of sub task, including media source path and sub task type.
     * @return Analyze result of sub task type.
     */
    public QuerySubTaskResponse querySubTask(QuerySubTaskRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, MEDIA, request.getSubTaskType());
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QuerySubTaskResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     * ready for callers to populate any additional headers or
     * parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));
        //System.out.println("createRequest =====================" + uri.toString());

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        String strJson = JsonUtils.toJsonString(request);
        //System.out.println("fillRequestPayload =====================" + strJson);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }

    /**
     * Initiate media analyze for specified source and title.
     *
     * @param lib libname.
     * @param description lib description.
     * @return Analyze response.
     */
    public BceErrorResponse faceLibCreate(String lib, String description) {

        FaceLibsResults faceLibs = faceLibsGet();
        List<FaceLibResultItem> libs = faceLibs.getLibs();
        for (FaceLibResultItem item : libs) {
            if(lib.equals(item.getLib()) ){
                BceErrorResponse err = new BceErrorResponse();
                err.setCode("200");
                err.setMessage("lib exist !");
                return err;
            }
        }
        FaceLibCreate request = new FaceLibCreate();
        request.setLib(lib);
        request.setDescription(description);
        return face(request);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param request Analyze request, including media source path.
     * @return Analyze response.
     */
    public BceErrorResponse face(FaceLibCreate request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, "face", "lib");
                
        BceErrorResponse err = new BceErrorResponse();
        try {
            this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
            
           err.setCode("200");
           err.setMessage("create lib success!");

        } catch (Exception e) {
            err.setCode("500");
            err.setMessage("create lib faile!");
        }
        return err;
    }

    /**
     * Initiate media analyze for specified source and title.
     *
     * @param lib libname.
     * @param description lib description.
     * @return Analyze response.
     */
    public AnalyzeResponse faceArtAdd(String lib, String image, String brief) {
        FaceArtAdd request = new FaceArtAdd();
        request.setImage(image);
        request.setBrief(brief);
        return art(request, lib);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param request Analyze request, including media source path.
     * @return Analyze response.
     */
    public AnalyzeResponse art(FaceArtAdd request, String lib) {
        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, "face", "lib", lib);

        
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    
    /**
     * Initiate media analyze for specified source and title.
     *
     * @return FaceLibsResults response.
     */
    public FaceLibsResults faceLibsGet() {
        FaceLibRequest request = new FaceLibRequest();
        return libs(request);
    }

    public FaceLibsResults libs(FaceLibRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, "face", "lib");
        return this.invokeHttpClient(internalRequest, FaceLibsResults.class);
    }

    public FaceLibBriefResults faceLibBriefsGet(String lib){
        FaceLibRequest request = new FaceLibRequest();
        return briefs(request,lib);
    }

    public FaceLibBriefResults briefs(FaceLibRequest request,String libname) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, "face", "lib", libname);
        return this.invokeHttpClient(internalRequest, FaceLibBriefResults.class);
    }

    public FaceLibImageResults faceLibImagesGet(String lib, String brief){
        FaceLibRequest request = new FaceLibRequest();
        return images(request, lib, brief);
    }

    public FaceLibImageResults images(FaceLibRequest request,String libname, String brief) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, "face", "lib", libname);
        internalRequest.addParameter("brief", brief);
        return this.invokeHttpClient(internalRequest, FaceLibImageResults.class);
    }
}
