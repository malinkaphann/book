module malinka.modularapp.api {
    requires static lombok;
    requires malinka.modularapp.entity;
    exports malinka.modularapp.api.resource;
    exports malinka.modularapp.api.dto;
    exports malinka.modularapp.api.respone;
}