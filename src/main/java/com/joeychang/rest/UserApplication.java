package com.joeychang.rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class UserApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(UserResource.class);
        return h;
    }
}
