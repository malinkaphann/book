module malinka.modularapp.dao.hibernate {
    requires static lombok;
    requires java.naming;
    requires malinka.modularapp.common;
    requires malinka.modularapp.entity;
    requires malinka.modularapp.dao;
    requires org.hibernate.orm.core;
    requires java.persistence;
    exports malinka.modularapp.hibernate.dao;
}