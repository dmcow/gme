package com.slm.gme.httpserver.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpServerService
{
    public void execute(HttpServletRequest request, HttpServletResponse response);
}
