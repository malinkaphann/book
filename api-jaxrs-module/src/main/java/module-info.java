module malinka.modularapp.jaxrs.api {
    requires java.ws.rs;
    requires malinka.modularapp.common;
    requires malinka.modularapp.entity;
    requires malinka.modularapp.api;
    requires malinka.modularapp.service;
    requires jetty.server;
    requires jersey.container.jetty.http;
    requires jersey.server;
    requires jersey.common;
    requires malinka.modularapp.dao.hibernate;
    exports malinka.modularapp.jaxrs.api.mapper;
    exports malinka.modularapp.jaxrs.api;
}