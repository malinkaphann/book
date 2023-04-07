module malinka.modularapp.entity {
    requires static lombok;
    requires static java.persistence;
    requires org.hibernate.orm.core;
    opens malinka.modularapp.entity;
    exports malinka.modularapp.entity;
}
