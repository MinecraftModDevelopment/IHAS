package com.mcmoddev.ihas.features;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IHASFeature {

    String featureID();

    String name();

    String author() default "";

    String version() default "";

    String description() default "";
}