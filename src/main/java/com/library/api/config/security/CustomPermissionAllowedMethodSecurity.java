package com.library.api.config.security;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.Jsr250SecurityConfig;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.annotation.security.PermitAll;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomPermissionAllowedMethodSecurity extends AbstractFallbackMethodSecurityMetadataSource {

    public CustomPermissionAllowedMethodSecurity() {
    }

    protected Collection<ConfigAttribute> findAttributes(Method method, Class<?> targetClass) {
        Annotation[] annotations = method.getAnnotations();
        if (annotations != null) {
            int var5 = annotations.length;

            for (Annotation a : annotations) {
                if (a instanceof PreAuthorize || a instanceof PostAuthorize || a instanceof Secured || a instanceof PermitAll) {
                    return null;
                }
            }
        }

        return this.dennyAllAttributesFromTheClass(targetClass);
    }

    private List dennyAllAttributesFromTheClass(Class<?> targetClass) {
        List attributes = new ArrayList();
        if (AnnotationUtils.findAnnotation(targetClass, Controller.class) != null && !targetClass.getName().contains("ApiResourceController") && !targetClass.getName().contains("BasicErrorController")) {
            attributes.add(Jsr250SecurityConfig.DENY_ALL_ATTRIBUTE);
        }

        return attributes;
    }

    protected Collection<ConfigAttribute> findAttributes(Class<?> aClass) {
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
