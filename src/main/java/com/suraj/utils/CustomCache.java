package com.suraj.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.suraj.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CustomCache {

    private static final Cache<Object, Object> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterAccess(24, TimeUnit.HOURS)
        .recordStats().build();

    public static long refresh(List<Employee> employeeList) {
        Map<Long, Employee> result1 = employeeList.stream().collect(
            Collectors.toMap(Employee::getId, Employee::self));
        cache.putAll(result1);
        return size();
    }

    public static Collection<Object> loadAll() {
        return cache.asMap().values();
    }

    public static long size() {
        return cache.size();
    }

    public static void save(Employee employee) {
        cache.put(employee.getId(), employee);
    }

    public static Employee load(Long id) {
        return (Employee) cache.getIfPresent(id);
    }
}
