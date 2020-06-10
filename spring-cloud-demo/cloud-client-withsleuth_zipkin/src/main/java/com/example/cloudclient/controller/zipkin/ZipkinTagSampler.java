//package com.example.cloudclient.controller.zipkin;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.sleuth.Sampler;
//import org.springframework.cloud.sleuth.Span;
//
//public class ZipkinTagSampler implements Sampler {
//    private static Logger logger= LoggerFactory.getLogger(ZipkinTagSampler.class);
//
//    private String tag;
//
//    public ZipkinTagSampler(String tag) {
//        this.tag = tag;
//    }
//    @Override
//    public boolean isSampled(Span span) {
//        logger.info("=========================Enter the Sampler:"+ span.tags().get(tag)+"==========================");
//        return span.tags().get(tag)!=null;
//    }
//}
