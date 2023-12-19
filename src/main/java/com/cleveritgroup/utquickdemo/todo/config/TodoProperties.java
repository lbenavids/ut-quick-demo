package com.cleveritgroup.utquickdemo.todo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "todo.config")
public record TodoProperties (String owner){
}
