package com.example.apt.domain;

import lombok.Data;

public class Apt {
    @Data
    public static class Create{
        private String name;
        private String address;
        private Integer yprice=0;
        private Integer ymin=0;
        private Integer ymax=0;
        private Integer dprice=0;
        private Integer dmin=0;
        private Integer dmax=0;
    }
    @Data
    public static class Update{
        private String name;
        private String address;
        private Integer yprice;
        private Integer dprice;
    }
    @Data
    public static class Simple {
        private Long id;
        private String name;
        private String address;
        private Integer yprice;
        private Integer ymin;
        private Integer ymax;
        private Integer dprice;
        private Integer dmin;
        private Integer dmax;

    }

}
