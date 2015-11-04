package com.slm.gme.httpserver.domain;

public class ServiceResponse
{
    private ResponseHeadField responseHead;
    
    public ResponseHeadField getResponseHead()
    {
        return responseHead;
    }
    
    public void setResponseHead(ResponseHeadField responseHead)
    {
        this.responseHead = responseHead;
    }
}
