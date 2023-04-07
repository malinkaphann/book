module malinka.modularapp.service {
    requires malinka.modularapp.entity;
    requires malinka.modularapp.api;
    requires malinka.modularapp.dao.hibernate;
    requires malinka.modularapp.common;
    requires org.hibernate.orm.core;
    requires malinka.modularapp.dao;
    requires java.persistence;
    exports malinka.modularapp.service;
}